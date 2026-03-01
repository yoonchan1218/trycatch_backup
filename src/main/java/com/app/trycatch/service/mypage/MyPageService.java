package com.app.trycatch.service.mypage;

import com.app.trycatch.common.exception.InputAllDataException;
import com.app.trycatch.common.exception.MemberNotFoundException;
import com.app.trycatch.common.exception.UnauthorizedMemberAccessException;
import com.app.trycatch.domain.mypage.LatestWatchPostingVO;
import com.app.trycatch.domain.mypage.ScrapPostingVO;
import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.dto.mypage.ApplyListWithPagingDTO;
import com.app.trycatch.dto.mypage.LatestWatchPostingDTO;
import com.app.trycatch.dto.mypage.MyPageNotificationDTO;
import com.app.trycatch.dto.mypage.MyPageProfileDTO;
import com.app.trycatch.dto.mypage.MyPageUpdateDTO;
import com.app.trycatch.dto.mypage.ScrapPostingDTO;
import com.app.trycatch.dto.mypage.ApplyListDTO;
import com.app.trycatch.dto.mypage.ExperienceProgramRankDTO;
import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.dto.file.FileDTO;
import com.app.trycatch.repository.file.FileDAO;
import com.app.trycatch.dto.qna.QnaDTO;
import com.app.trycatch.repository.mypage.ApplyListDAO;
import com.app.trycatch.repository.mypage.ExperienceProgramRankDAO;
import com.app.trycatch.repository.mypage.LatestWatchPostingDAO;
import com.app.trycatch.repository.mypage.MyPageDAO;
import com.app.trycatch.repository.mypage.ScrapPostingDAO;
import com.app.trycatch.repository.qna.QnaDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MyPageService {
    private final MyPageDAO myPageDAO;
    private final FileDAO fileDAO;
    private final ScrapPostingDAO scrapPostingDAO;
    private final LatestWatchPostingDAO latestWatchPostingDAO;
    private final ExperienceProgramRankDAO experienceProgramRankDAO;
    private final ApplyListDAO applyListDAO;
    private final QnaDAO qnaDAO;

    @Transactional(readOnly = true)
    public MyPageProfileDTO getProfile(Long memberId) {
        return myPageDAO.findProfileByMemberId(memberId).orElseThrow(MemberNotFoundException::new);
    }

    public void updateProfile(Long memberId, MyPageUpdateDTO myPageUpdateDTO) {
        if (myPageUpdateDTO.getId() != null && !memberId.equals(myPageUpdateDTO.getId())) {
            throw new UnauthorizedMemberAccessException();
        }

        if (myPageUpdateDTO.getMemberName() == null || myPageUpdateDTO.getMemberName().isBlank() ||
            myPageUpdateDTO.getMemberEmail() == null || myPageUpdateDTO.getMemberEmail().isBlank() ||
            myPageUpdateDTO.getMemberPhone() == null || myPageUpdateDTO.getMemberPhone().isBlank() ||
            myPageUpdateDTO.getIndividualMemberBirth() == null || myPageUpdateDTO.getIndividualMemberBirth().isBlank() ||
            myPageUpdateDTO.getIndividualMemberGender() == null) {
            throw new InputAllDataException();
        }

        myPageUpdateDTO.setId(memberId);
        myPageDAO.updateMember(myPageUpdateDTO.toMemberVO());
        myPageDAO.updateIndividualMember(myPageUpdateDTO.toIndividualMemberVO());
    }

    @Transactional(readOnly = true)
    public List<MyPageNotificationDTO> getNotifications(Long memberId) {
        return myPageDAO.findNotificationsByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    public Map<String, List<MyPageNotificationDTO>> getNotificationsGroupedByDate(Long memberId) {
        return myPageDAO.findNotificationsByMemberId(memberId).stream()
                .collect(Collectors.groupingBy(
                        MyPageNotificationDTO::getCreatedDateLabel,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }

    public String uploadProfileImage(Long memberId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String todayPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String rootPath = "C:/file/";
        String path = rootPath + todayPath;

        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + file.getOriginalFilename();

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            file.transferTo(new File(path, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFilePath(todayPath);
        fileDTO.setFileName(fileName);
        fileDTO.setFileOriginalName(file.getOriginalFilename());
        fileDTO.setFileSize(String.valueOf(file.getSize()));
        fileDTO.setFileContentType(FileContentType.IMAGE);
        fileDAO.save(fileDTO);

        myPageDAO.updateProfileFileId(memberId, fileDTO.getId());

        return "/api/files/display?filePath=" + todayPath + "&fileName=" + fileName;
    }

    public void deactivateMember(Long memberId, String memberName) {
        MyPageProfileDTO profile = myPageDAO.findProfileByMemberId(memberId)
                .orElseThrow(MemberNotFoundException::new);
        if (!profile.getMemberName().equals(memberName)) {
            throw new com.app.trycatch.common.exception.UnsubscribeNameMismatchException();
        }
        myPageDAO.deactivateMember(memberId);
    }

    @Transactional(readOnly = true)
    public List<ScrapPostingDTO> getScrapPostings(Long memberId) {
        return scrapPostingDAO.findAllByMemberId(memberId);
    }

    public void toggleScrap(ScrapPostingDTO scrapPostingDTO) {
        scrapPostingDAO.updateStatus(scrapPostingDTO.toVO());
    }

    public void addScrap(Long memberId, Long experienceProgramId) {
        ScrapPostingVO scrapPostingVO = ScrapPostingVO.builder()
                .memberId(memberId)
                .experienceProgramId(experienceProgramId)
                .build();
        scrapPostingDAO.save(scrapPostingVO);
    }

    @Transactional(readOnly = true)
    public List<LatestWatchPostingDTO> getLatestWatchPostings(Long memberId) {
        return latestWatchPostingDAO.findAllByMemberId(memberId);
    }

    public void addLatestWatchPosting(Long memberId, Long experienceProgramId) {
        LatestWatchPostingVO latestWatchPostingVO = LatestWatchPostingVO.builder()
                .memberId(memberId)
                .experienceProgramId(experienceProgramId)
                .build();
        latestWatchPostingDAO.save(latestWatchPostingVO);
    }

    public boolean cancelApply(Long memberId, Long applyId) {
        return myPageDAO.cancelApply(memberId, applyId) > 0;
    }

    @Transactional(readOnly = true)
    public List<ApplyListDTO> getApplyList(Long memberId) {
        return applyListDAO.findAllByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    public List<ApplyListDTO> getApplyListWithFilter(
            Long memberId, String fromDt, String toDt,
            String programStatus, String applyStatus, String keyword) {
        return applyListDAO.findAllByMemberIdWithFilter(
                memberId, fromDt, toDt, programStatus, applyStatus, keyword);
    }

    @Transactional(readOnly = true)
    public ApplyListWithPagingDTO getApplyListWithPagingAndFilter(
            Long memberId, int page, String fromDt, String toDt,
            String programStatus, String applyStatus, String keyword) {
        int total = applyListDAO.findCountByMemberIdWithFilter(
                memberId, fromDt, toDt, programStatus, applyStatus, keyword);
        Criteria criteria = new Criteria(page, total);
        List<ApplyListDTO> list = applyListDAO.findAllByMemberIdWithFilterAndPaging(
                memberId, fromDt, toDt, programStatus, applyStatus, keyword, criteria);
        criteria.setHasMore(list.size() == criteria.getCount());
        if (criteria.isHasMore()) list.remove(list.size() - 1);

        ApplyListWithPagingDTO statusCounts = applyListDAO.findStatusCountsByMemberIdWithFilter(
                memberId, fromDt, toDt, programStatus, applyStatus, keyword);

        ApplyListWithPagingDTO result = new ApplyListWithPagingDTO();
        result.setApplies(list);
        result.setCriteria(criteria);
        if (statusCounts != null) {
            result.setAppliedCount(statusCounts.getAppliedCount());
            result.setDocumentPassCount(statusCounts.getDocumentPassCount());
            result.setDocumentFailCount(statusCounts.getDocumentFailCount());
            result.setActivityDoneCount(statusCounts.getActivityDoneCount());
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<ExperienceProgramRankDTO> getTopPostings(int limit) {
        return experienceProgramRankDAO.findTopByViewCount(limit);
    }

    @Transactional(readOnly = true)
    public List<ExperienceProgramRankDTO> getTopPublicPostings(int limit) {
        return experienceProgramRankDAO.findTopPublicByViewCount(limit);
    }

    @Transactional(readOnly = true)
    public List<ExperienceProgramRankDTO> getTopPublicPostingsByLatest(int limit) {
        return experienceProgramRankDAO.findTopPublicByLatest(limit);
    }

    @Transactional(readOnly = true)
    public List<QnaDTO> getTopQnas(int limit) {
        return qnaDAO.findTopByViewCount(limit);
    }
}
