package com.app.trycatch.service.skilllog;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.common.exception.ExperienceProgramNotFoundException;
import com.app.trycatch.common.exception.FileNotFoundException;
import com.app.trycatch.common.exception.SkillLogNotFoundException;
import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.common.search.Search;
import com.app.trycatch.domain.experience.ExperienceProgramFileVO;
import com.app.trycatch.domain.file.FileVO;
import com.app.trycatch.domain.skilllog.SkillLogLikesVO;
import com.app.trycatch.domain.skilllog.TagVO;
import com.app.trycatch.dto.experience.ExperienceProgramDTO;
import com.app.trycatch.dto.experience.ExperienceProgramFileDTO;
import com.app.trycatch.dto.file.FileDTO;
import com.app.trycatch.dto.skilllog.*;
import com.app.trycatch.repository.experience.ExperienceProgramDAO;
import com.app.trycatch.repository.experience.ExperienceProgramFileDAO;
import com.app.trycatch.repository.file.FileDAO;
import com.app.trycatch.repository.skilllog.*;
import com.app.trycatch.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SkillLogService {
    private final SkillLogDAO skillLogDAO;
    private final TagDAO tagDAO;
    private final SkillLogLikesDAO skillLogLikesDAO;
    private final SkillLogFileDAO skillLogFileDAO;
    private final FileDAO fileDAO;

    private final ExperienceProgramDAO experienceProgramDAO;
    private final ExperienceProgramFileDAO experienceProgramFileDAO;

    private final SkillLogCommentDAO skillLogCommentDAO;
    private final SkillLogCommentFileDAO skillLogCommentFileDAO;
    private final SkillLogCommentLikesDAO skillLogCommentLikesDAO;

//    작성
    public void write(SkillLogDTO skillLogDTO, List<MultipartFile> multipartFiles) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        FileDTO fileDTO = new FileDTO();
        SkillLogFileDTO skillLogFileDTO = new SkillLogFileDTO();

        skillLogDAO.save(skillLogDTO);

        skillLogDTO.getTags().forEach((tagDTO) -> {
        tagDTO.setSkillLogId(skillLogDTO.getId());
            tagDAO.save(tagDTO.toVO());
        });

        skillLogFileDTO.setSkillLogId(skillLogDTO.getId());
        multipartFiles.forEach(multipartFile -> {
            if(multipartFile.getOriginalFilename().isEmpty()){
                return;
            }
            UUID uuid = UUID.randomUUID();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
            fileDTO.setFileOriginalName(multipartFile.getOriginalFilename());
            fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
            fileDTO.setFileContentType(multipartFile.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER);
            fileDAO.save(fileDTO);

            skillLogFileDTO.setId(fileDTO.getId());
            skillLogFileDAO.save(skillLogFileDTO.toSkillLogFileVO());

            File directory = new File(path);
            if(!directory.exists()){
                directory.mkdirs();
            }

            try {
                multipartFile.transferTo(new File(path, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

//    - 최근 공고
    public ExperienceProgramWithPagingDTO recentExperienceLogs(Long id, int page, Search search) {
        ExperienceProgramWithPagingDTO experienceProgramWithPagingDTO = new ExperienceProgramWithPagingDTO();
        Criteria criteria = new Criteria(page, experienceProgramDAO.findTotalByMemberIdOfChallenger(search, id));

        List<ExperienceProgramDTO> experiencePrograms = experienceProgramDAO.findAllByMemberIdOfChallenger(criteria, search, id);

        criteria.setHasMore(experiencePrograms.size() > criteria.getRowCount());
        experienceProgramWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()) {
            experiencePrograms.remove(experiencePrograms.size() - 1);
        }

        experiencePrograms.forEach((experienceProgramDTO) -> {
            List<ExperienceProgramFileDTO> experienceProgramFiles = experienceProgramFileDAO.findAllByExperienceProgramId(experienceProgramDTO.getId());
            experienceProgramDTO.setExperienceProgramFiles(experienceProgramFiles);
        });
        experienceProgramWithPagingDTO.setExperienceProgramLogs(experiencePrograms);

        return experienceProgramWithPagingDTO;
    }

//    aside
    public SkillLogAsideDTO aside(Long id) {
        return skillLogDAO.findProfileByMemberId(id);
    }

//    목록
    public SkillLogWithPagingDTO list(int page, Search search) {
        SkillLogWithPagingDTO skillLogWithPagingDTO = new SkillLogWithPagingDTO();
        Criteria criteria = new Criteria(page, skillLogDAO.findTotal(search));

        List<SkillLogDTO> skillLogs = skillLogDAO.findAll(criteria, search);

        criteria.setHasMore(skillLogs.size() > criteria.getRowCount());
        skillLogWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()){
            skillLogs.remove(skillLogs.size() - 1);
        }

        skillLogs.forEach((skillLogDTO) -> {
            skillLogDTO.setCreatedDatetime(DateUtils.toRelativeTime(skillLogDTO.getCreatedDatetime()));
            skillLogDTO.setTags(tagDAO.findAllBySkillLogId(skillLogDTO.getId())
                    .stream().map((tagVO) -> toTagDTO(tagVO)).collect(Collectors.toList()));
            skillLogDTO.setSkillLogFiles(skillLogFileDAO.findAllBySkillLogId(skillLogDTO.getId()));
        });
        skillLogWithPagingDTO.setSkillLogs(skillLogs);

        return skillLogWithPagingDTO;
    }

//    내 글 목록
    public SkillLogWithPagingDTO myList(int page, Search search, Long memberId) {
        SkillLogWithPagingDTO skillLogWithPagingDTO = new SkillLogWithPagingDTO();
        int totalCount = skillLogDAO.findTotalByMemberId(memberId);
        Criteria criteria = new Criteria(page, totalCount);

        List<SkillLogDTO> skillLogs = skillLogDAO.findAllByMemberId(criteria, search, memberId);

        criteria.setHasMore(skillLogs.size() > criteria.getRowCount());
        skillLogWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()){
            skillLogs.remove(skillLogs.size() - 1);
        }

        skillLogs.forEach((skillLogDTO) -> {
//            작성일
            skillLogDTO.setCreatedDatetime(DateUtils.toRelativeTime(skillLogDTO.getCreatedDatetime()));
//            태그
            skillLogDTO.setTags(tagDAO.findAllBySkillLogId(skillLogDTO.getId())
                    .stream().map((tagVO) -> toTagDTO(tagVO)).collect(Collectors.toList()));
        });
        skillLogWithPagingDTO.setSkillLogs(skillLogs);

        return skillLogWithPagingDTO;
    }

//    조회
    public SkillLogDTO detail(Long id, Long memberId) {
        Optional<SkillLogDTO> foundSkillLog = null;
        SkillLogDTO skillLogDTO = null;
        SkillLogLikesDTO skillLogLikesDTO = new SkillLogLikesDTO();

//        skillLog
        String formattedDate = null;
        boolean updateCheck = false;

        skillLogDAO.setSkillLogViewCount(id);

        foundSkillLog = skillLogDAO.findById(id);
        skillLogDTO = foundSkillLog.orElseThrow(SkillLogNotFoundException::new);

        updateCheck = !skillLogDTO.getCreatedDatetime().equals(skillLogDTO.getUpdatedDatetime());
        formattedDate = skillLogDTO.getCreatedDatetime().split(" ")[0] + (updateCheck ? " (수정됨)" : "");
        
        skillLogDTO.setCreatedDatetime(formattedDate);
        skillLogDTO.setTags(tagDAO.findAllBySkillLogId(skillLogDTO.getId())
                .stream().map((tagVO) -> toTagDTO(tagVO)).collect(Collectors.toList()));
        skillLogDTO.setSkillLogFiles(skillLogFileDAO.findAllBySkillLogId(skillLogDTO.getId()));

//        likes
        skillLogLikesDTO.setSkillLogId(skillLogDTO.getId());
        skillLogLikesDTO.setMemberId(memberId);
        skillLogDTO.setLikeCount(skillLogLikesDAO.findCountBySkillLogId(skillLogDTO.getId()));
        skillLogDTO.setLiked(skillLogLikesDAO.findBySkillLogIdAndMemberId(skillLogLikesDTO.toVO()).isPresent());

        return skillLogDTO;
    }

//    좋아요
    public int like(SkillLogLikesDTO skillLogLikesDTO) {
        skillLogLikesDAO.findBySkillLogIdAndMemberId(skillLogLikesDTO.toVO()).ifPresentOrElse((skillLog) -> {
            skillLogLikesDAO.delete(skillLog.getId());
        }, () -> {
            skillLogLikesDAO.save(skillLogLikesDTO.toVO());
        });

        return skillLogLikesDAO.findCountBySkillLogId(skillLogLikesDTO.getSkillLogId());
    }

//    수정
    public void update(SkillLogDTO skillLogDTO, List<MultipartFile> multipartFiles) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        FileDTO fileDTO = new FileDTO();
        SkillLogFileDTO skillLogFileDTO = new SkillLogFileDTO();

        skillLogDAO.setSkillLog(skillLogDTO.toSkillLogVO());

        skillLogDTO.getTags().forEach((tagDTO) -> {
            tagDTO.setSkillLogId(skillLogDTO.getId());
            tagDAO.save(tagDTO.toVO());
        });

        skillLogFileDTO.setSkillLogId(skillLogDTO.getId());
        multipartFiles.forEach(multipartFile -> {
            if(multipartFile.getOriginalFilename().isEmpty()){
                return;
            }
            UUID uuid = UUID.randomUUID();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
            fileDTO.setFileOriginalName(multipartFile.getOriginalFilename());
            fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
            fileDTO.setFileContentType(multipartFile.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER);
            fileDAO.save(fileDTO);

            skillLogFileDTO.setId(fileDTO.getId());
            skillLogFileDAO.save(skillLogFileDTO.toSkillLogFileVO());

            File directory = new File(path);
            if(!directory.exists()){
                directory.mkdirs();
            }

            try {
                multipartFile.transferTo(new File(path, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

//        삭제
        if(skillLogDTO.getTagIdsToDelete() != null) {
            Arrays.stream(skillLogDTO.getTagIdsToDelete()).forEach((tagId) -> {
                tagDAO.delete(Long.valueOf(tagId));
            });
        }
        if(skillLogDTO.getFileIdsToDelete() != null) {
            Arrays.stream(skillLogDTO.getFileIdsToDelete()).forEach((fileId) -> {
                FileVO fileVO = fileDAO.findById(Long.valueOf(fileId)).orElseThrow(FileNotFoundException::new);
                File file = new File(rootPath + fileVO.getFilePath(), fileVO.getFileName());
                if(file.exists()) {
                    file.delete();
                }
                skillLogFileDAO.delete(Long.valueOf(fileId));
                fileDAO.delete(Long.valueOf(fileId));
            });
        }
    }

//    삭제
    public void delete(Long id) {
        String rootPath = "C:/file/";

//        태그 삭제
        tagDAO.findAllBySkillLogId(id).forEach((tagVO) -> {
            tagDAO.delete(Long.valueOf(tagVO.getId()));
        });

//        파일 삭제
        skillLogFileDAO.findAllBySkillLogId(id).forEach((skillLogFileDTO) -> {
            FileVO fileVO = fileDAO.findById(Long.valueOf(skillLogFileDTO.getId())).orElseThrow(FileNotFoundException::new);
            File file = new File(rootPath + fileVO.getFilePath(), fileVO.getFileName());
            if(file.exists()) {
                file.delete();
            }
            skillLogFileDAO.delete(Long.valueOf(skillLogFileDTO.getId()));
            fileDAO.delete(Long.valueOf(skillLogFileDTO.getId()));
        });

//        좋아요 삭제
        skillLogCommentLikesDAO.deleteBySkillLogId(id); // 댓글
        skillLogLikesDAO.deleteBySkillLogId(id); // 게시글

//        댓글 삭제
        skillLogCommentFileDAO.deleteAllBySkillLogId(id); // 파일
        skillLogCommentDAO.deleteNestedCommentsBySkillLogId(id); // 대댓글
        skillLogCommentDAO.deleteParentCommentsBySkillLogId(id); // 댓글

//        게시글 삭제
        skillLogDAO.setSkillLogStatus(id);
    }

    public TagDTO toTagDTO(TagVO tagVO) {
        TagDTO tagDTO = new TagDTO();

        tagDTO.setId(tagVO.getId());
        tagDTO.setSkillLogId(tagVO.getSkillLogId());
        tagDTO.setTagName(tagVO.getTagName());

        return tagDTO;
    }

    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
