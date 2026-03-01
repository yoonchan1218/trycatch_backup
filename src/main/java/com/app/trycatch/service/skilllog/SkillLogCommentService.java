package com.app.trycatch.service.skilllog;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.common.exception.FileNotFoundException;
import com.app.trycatch.common.exception.SkillLogNotFoundException;
import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.file.FileVO;
import com.app.trycatch.domain.skilllog.SkillLogCommentLikesVO;
import com.app.trycatch.domain.skilllog.SkillLogCommentVO;
import com.app.trycatch.dto.alarm.AlramDTO;
import com.app.trycatch.dto.file.FileDTO;
import com.app.trycatch.dto.skilllog.*;
import com.app.trycatch.mapper.file.FileMapper;
import com.app.trycatch.mapper.skilllog.SkillLogCommentFileMapper;
import com.app.trycatch.mapper.skilllog.SkillLogCommentMapper;
import com.app.trycatch.repository.file.FileDAO;
import com.app.trycatch.repository.skilllog.SkillLogCommentDAO;
import com.app.trycatch.repository.skilllog.SkillLogCommentFileDAO;
import com.app.trycatch.repository.skilllog.SkillLogCommentLikesDAO;
import com.app.trycatch.repository.skilllog.SkillLogDAO;
import com.app.trycatch.service.Alarm.IndividualAlramService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SkillLogCommentService {
    private final SkillLogCommentDAO skillLogCommentDAO;
    private final SkillLogCommentFileDAO skillLogCommentFileDAO;
    private final FileDAO fileDAO;
    private final SkillLogCommentLikesDAO skillLogCommentLikesDAO;
    private final SkillLogDAO skillLogDAO;
    private final IndividualAlramService individualAlramService;

    //    추가
    public void write(SkillLogCommentDTO skillLogCommentDTO, MultipartFile multipartFile){
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        FileDTO fileDTO = new FileDTO();
        SkillLogCommentFileDTO skillLogCommentFileDTO = new SkillLogCommentFileDTO();

        skillLogCommentDAO.save(skillLogCommentDTO);

//        알림 전송
        AlramDTO alramDTO = new AlramDTO();
        alramDTO.setNotificationType("skill_log");
        alramDTO.setSkillLogId(skillLogCommentDTO.getSkillLogId());

        if (skillLogCommentDTO.getSkillLogCommentParentId() == null) {
//            댓글: 기술블로그 작성자에게 알림
            skillLogDAO.findById(skillLogCommentDTO.getSkillLogId()).ifPresent(skillLog -> {
                if (!skillLog.getMemberId().equals(skillLogCommentDTO.getMemberId())) {
                    alramDTO.setMemberId(skillLog.getMemberId());
                    alramDTO.setNotificationTitle("기술블로그에 댓글이 달렸습니다.");
                    alramDTO.setNotificationContent(skillLogCommentDTO.getSkillLogCommentContent());
                    individualAlramService.saveNotification(alramDTO);
                }
            });
        } else {
//            대댓글: 부모 댓글 작성자에게 알림
            Long parentCommentMemberId = skillLogCommentDAO.findMemberIdById(skillLogCommentDTO.getSkillLogCommentParentId());
            if (parentCommentMemberId != null && !parentCommentMemberId.equals(skillLogCommentDTO.getMemberId())) {
                alramDTO.setMemberId(parentCommentMemberId);
                alramDTO.setNotificationTitle("기술블로그 댓글에 답글이 달렸습니다.");
                alramDTO.setNotificationContent(skillLogCommentDTO.getSkillLogCommentContent());
                individualAlramService.saveNotification(alramDTO);
            }
        }

        if(multipartFile != null) {
            UUID uuid = UUID.randomUUID();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
            fileDTO.setFileOriginalName(multipartFile.getOriginalFilename());
            fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
            fileDTO.setFileContentType(multipartFile.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER);
            fileDAO.save(fileDTO);

            skillLogCommentFileDTO.setId(fileDTO.getId());
            skillLogCommentFileDTO.setSkillLogCommentId(skillLogCommentDTO.getId());
            skillLogCommentFileDAO.save(skillLogCommentFileDTO.toSkillLogCommentFileVO());

            File directory = new File(rootPath + "/" + fileDTO.getFilePath());
            if(!directory.exists()){
                directory.mkdirs();
            }
            try {
                multipartFile.transferTo(new File(path, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //    댓글 목록
    public SkillLogCommentWithPagingDTO getListInSkillLog(int page, Long id, Long memberId){
        SkillLogCommentWithPagingDTO skillLogCommentWithPagingDTO = new SkillLogCommentWithPagingDTO();
        int totalCount = skillLogCommentDAO.findCountAllBySkillLogId(id);
        Criteria criteria = new Criteria(page, skillLogCommentDAO.findCountAllBySkillLogId(id));
        List<SkillLogCommentDTO> comments = skillLogCommentDAO.findAllBySkillLogId(criteria, id);
        SkillLogCommentLikesDTO skillLogCommentLikesDTO = new SkillLogCommentLikesDTO();

        comments.forEach((comment) -> {
            boolean updateCheck = !comment.getCreatedDatetime().equals(comment.getUpdatedDatetime());
            String formattedDate = DateUtils.toRelativeTime(comment.getCreatedDatetime()) + (updateCheck ? " (수정됨)" : "");
            comment.setCreatedDatetime(formattedDate);

//            대댓글 수
            comment.setSkillLogCommentChildCount(skillLogCommentDAO.findCountAllByCommentParentIdAndSkillLogId(comment.getSkillLogId(), comment.getId()));

//            likes
            skillLogCommentLikesDTO.setSkillLogCommentId(comment.getId());
            skillLogCommentLikesDTO.setMemberId(memberId);
            comment.setLikeCount(skillLogCommentLikesDAO.findCountBySkillLogCommentId(comment.getId()));
            comment.setLiked(skillLogCommentLikesDAO.findBySkillLogCommentIdAndMemberId(skillLogCommentLikesDTO.toVO()).isPresent());

        });

        skillLogCommentWithPagingDTO.setCriteria(criteria);
        skillLogCommentWithPagingDTO.setSkillLogComments(comments);
        skillLogCommentWithPagingDTO.setTotalCount(totalCount);

        return skillLogCommentWithPagingDTO;
    }
    //    대댓글 목록
    public SkillLogNestedCommentWithPagingDTO getListInSkillLogAndParentComment(int page, Long skillLogId, Long commentId){
        SkillLogNestedCommentWithPagingDTO skillLogNestedCommentWithPagingDTO = new SkillLogNestedCommentWithPagingDTO();
        Criteria criteria = new Criteria(page, skillLogCommentDAO.findCountAllByCommentParentIdAndSkillLogId(skillLogId, commentId));
        List<SkillLogCommentDTO> nestedComments = skillLogCommentDAO.findAllByCommentParentIdAndSkillLogId(criteria, skillLogId, commentId);

        criteria.setHasMore(nestedComments.size() > criteria.getRowCount());
        skillLogNestedCommentWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()){
            nestedComments.remove(nestedComments.size() - 1);
        }

        nestedComments.forEach((comment) -> {
            boolean updateCheck = !comment.getCreatedDatetime().equals(comment.getUpdatedDatetime());
            String formattedDate = DateUtils.toRelativeTime(comment.getCreatedDatetime()) + (updateCheck ? " (수정됨)" : "");
            comment.setCreatedDatetime(formattedDate);
        });

        skillLogNestedCommentWithPagingDTO.setCriteria(criteria);
        skillLogNestedCommentWithPagingDTO.setSkillLogNestedComments(nestedComments);

        return skillLogNestedCommentWithPagingDTO;
    }

    //    수정
    public void update(SkillLogCommentDTO skillLogCommentDTO, MultipartFile multipartFile){
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        FileDTO fileDTO = new FileDTO();
        SkillLogCommentFileDTO skillLogCommentFileDTO = new SkillLogCommentFileDTO();

        skillLogCommentDAO.setSkillLogComment(skillLogCommentDTO.toVO());

        if(multipartFile != null) {
            UUID uuid = UUID.randomUUID();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
            fileDTO.setFileOriginalName(multipartFile.getOriginalFilename());
            fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
            fileDTO.setFileContentType(multipartFile.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER);
            fileDAO.save(fileDTO);

            skillLogCommentFileDTO.setId(fileDTO.getId());
            skillLogCommentFileDTO.setSkillLogCommentId(skillLogCommentDTO.getId());
            skillLogCommentFileDAO.save(skillLogCommentFileDTO.toSkillLogCommentFileVO());

            File directory = new File(rootPath + "/" + fileDTO.getFilePath());
            if(!directory.exists()){
                directory.mkdirs();
            }
            try {
                multipartFile.transferTo(new File(path, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(skillLogCommentDTO.getFileIdToDelete() != null) {
            Long fileId = skillLogCommentDTO.getFileIdToDelete();
            FileVO fileVO = fileDAO.findById(Long.valueOf(fileId)).orElseThrow(FileNotFoundException::new);
            File file = new File(rootPath + fileVO.getFilePath(), fileVO.getFileName());
            if(file.exists()) {
                file.delete();
            }
            skillLogCommentFileDAO.delete(Long.valueOf(fileId));
            fileDAO.delete(Long.valueOf(fileId));
        }
    }

    //    삭제
//    댓글 삭제
    public void delete(Long skillLogCommentId) {
        Long fileId = skillLogCommentFileDAO.findFileIdBySkillLogCommentId(skillLogCommentId);

//        답글 파일 삭제
        List<FileDTO> commentFiles = skillLogCommentFileDAO.findFilesByCommentParentId(skillLogCommentId);
        skillLogCommentFileDAO.deleteAllBySkillLogCommentParentId(skillLogCommentId);

//        답글 삭제
        skillLogCommentDAO.deleteAllByCommentParentId(skillLogCommentId);

//        댓글 파일 삭제
        if (fileId != null) {
            fileDAO.findById(fileId).ifPresent(file -> {
                commentFiles.add(toFileDTO(file));
            });
            skillLogCommentFileDAO.delete(fileId);
            fileDAO.delete(fileId);
        }

//        좋아요 삭제
        skillLogCommentLikesDAO.deleteBySkillLogCommentId(skillLogCommentId);

//        댓글 삭제
        skillLogCommentDAO.delete(skillLogCommentId);

//        파일 삭제 (댓글, 답글)
        commentFiles.forEach(fileDTO -> {
            String rootPath = "C:/file/";

            File file = new File(rootPath + fileDTO.getFilePath(), fileDTO.getFileName());
            if (file.exists()) {
                file.delete();
            }
        });
    }

//    좋아요
    public int like(SkillLogCommentLikesDTO skillLogCommentLikesDTO) {
        skillLogCommentLikesDAO.findBySkillLogCommentIdAndMemberId(skillLogCommentLikesDTO.toVO()).ifPresentOrElse((comment) -> {
            skillLogCommentLikesDAO.delete(comment.getId());
        }, () -> {
            skillLogCommentLikesDAO.save(skillLogCommentLikesDTO.toVO());
        });

        return skillLogCommentLikesDAO.findCountBySkillLogCommentId(skillLogCommentLikesDTO.getSkillLogCommentId());
    }

// 게시글 삭제 시 댓글 전체 삭제
//    public void deleteAllBySkillLogId(Long id) {
//        List<FileDTO> files = skillLogCommentFileDAO.findFilesBySkillLogId(id);
//        skillLogCommentFileDAO.deleteAllBySkillLogId(id);
//        skillLogCommentDAO.deleteNestedCommentsBySkillLogId(id);
//        skillLogCommentDAO.deleteParentCommentsBySkillLogId(id);
//
//        files.forEach(fileDTO -> {
//            String rootPath = "C:/file/";
//
//            File file = new File(rootPath + fileDTO.getFilePath(), fileDTO.getFileName());
//            if (file.exists()) {
//                file.delete();
//            }
//        });
//    }

    public FileDTO toFileDTO(FileVO fileVO) {
        FileDTO fileDTO = new FileDTO();

        fileDTO.setId(fileVO.getId());
        fileDTO.setFileSize(fileVO.getFileSize());
        fileDTO.setFilePath(fileVO.getFilePath());
        fileDTO.setFileName(fileVO.getFileName());
        fileDTO.setFileContentType(fileVO.getFileContentType());
        fileDTO.setFileOriginalName(fileVO.getFileOriginalName());
        fileDTO.setCreatedDatetime(fileVO.getCreatedDatetime());
        fileDTO.setUpdatedDatetime(fileVO.getUpdatedDatetime());

        return fileDTO;
    }

    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
