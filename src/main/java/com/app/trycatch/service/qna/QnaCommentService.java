package com.app.trycatch.service.qna;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.domain.qna.QnaCommentVO;
import com.app.trycatch.dto.file.FileDTO;
import com.app.trycatch.dto.qna.QnaCommentDTO;
import com.app.trycatch.repository.file.FileDAO;
import com.app.trycatch.dto.alarm.AlramDTO;
import com.app.trycatch.repository.alarm.CorporateAlramDAO;
import com.app.trycatch.repository.qna.QnaCommentDAO;
import com.app.trycatch.repository.qna.QnaCommentFileDAO;
import com.app.trycatch.repository.qna.QnaDAO;
import com.app.trycatch.service.Alarm.CorporateAlramService;
import com.app.trycatch.service.Alarm.IndividualAlramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QnaCommentService {
    private final QnaCommentDAO qnaCommentDAO;
    private final FileDAO fileDAO;
    private final QnaCommentFileDAO qnaCommentFileDAO;
    private final CorporateAlramService corporateAlramService;
    private final CorporateAlramDAO corporateAlramDAO;
    private final IndividualAlramService individualAlramService;
    private final QnaDAO qnaDAO;

    public Map<String, Object> write(Long memberId, Long qnaId, String content, Long parentId, MultipartFile file) {
        // 댓글(부모 없음)은 qna당 1개 제한
        if (parentId == null && qnaCommentDAO.existsByQnaIdAndMemberId(qnaId, memberId)) {
            return Map.of("success", false, "message", "이미 댓글을 작성하셨습니다.");
        }
        QnaCommentVO vo = QnaCommentVO.builder()
                .qnaId(qnaId)
                .memberId(memberId)
                .qnaCommentParent(parentId)
                .qnaCommentContent(content)
                .build();
        qnaCommentDAO.save(vo);

        // 댓글인 경우 → 원글 작성자(개인회원)에게 알림
        if (parentId == null) {
            Long postAuthorId = qnaDAO.findMemberIdById(qnaId);
            if (postAuthorId != null && !postAuthorId.equals(memberId)) {
                AlramDTO alramDTO = new AlramDTO();
                alramDTO.setMemberId(postAuthorId);
                alramDTO.setNotificationType("qna");
                alramDTO.setNotificationTitle("QnA 댓글 알림");
                alramDTO.setNotificationContent("회원님의 QnA 게시글에 새로운 댓글이 달렸습니다.");
                alramDTO.setQnaId(qnaId);
                individualAlramService.saveNotification(alramDTO);
            }
        }

        // 대댓글인 경우 → 부모 댓글 작성자에게 알림 (기업/개인 모두)
        if (parentId != null) {
            Long parentMemberId = qnaCommentDAO.findMemberIdById(parentId);
            if (parentMemberId != null && !parentMemberId.equals(memberId)) {
                if (corporateAlramDAO.existsByCorpId(parentMemberId)) {
                    corporateAlramService.notify(
                            parentMemberId,
                            "qna",
                            "QnA 대댓글 알림",
                            "회원님의 댓글에 새로운 대댓글이 달렸습니다.",
                            qnaId
                    );
                } else {
                    AlramDTO alramDTO = new AlramDTO();
                    alramDTO.setMemberId(parentMemberId);
                    alramDTO.setNotificationType("qna");
                    alramDTO.setNotificationTitle("QnA 대댓글 알림");
                    alramDTO.setNotificationContent("회원님의 댓글에 새로운 대댓글이 달렸습니다.");
                    alramDTO.setQnaId(qnaId);
                    individualAlramService.saveNotification(alramDTO);
                }
            }
        }

        if (file != null && !file.isEmpty()) {
            String todayPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileName(UUID.randomUUID() + "_" + file.getOriginalFilename());
            fileDTO.setFileOriginalName(file.getOriginalFilename());
            fileDTO.setFileSize(String.valueOf(file.getSize()));
            fileDTO.setFileContentType(
                    file.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER
            );
            fileDAO.save(fileDTO);
            qnaCommentFileDAO.save(fileDTO.getId(), vo.getId());

            File dir = new File("C:/file/" + todayPath);
            if (!dir.exists()) dir.mkdirs();
            try {
                file.transferTo(new File(dir, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return Map.of("success", true);
    }

    public List<QnaCommentDTO> list(Long qnaId) {
        List<QnaCommentDTO> comments = qnaCommentDAO.findByQnaId(qnaId);
        comments.stream()
                .filter(c -> c.getQnaCommentParent() == null)
                .forEach(parent -> {
                    int count = (int) comments.stream()
                            .filter(c -> parent.getId().equals(c.getQnaCommentParent()))
                            .count();
                    parent.setReplyCount(count);
                });
        return comments;
    }

    public boolean hasComment(Long qnaId, Long memberId) {
        return qnaCommentDAO.existsByQnaIdAndMemberId(qnaId, memberId);
    }

    public void delete(Long id, Long memberId) {
        // 첨부파일이 있으면 먼저 삭제 (FK 제약)
        Long fileId = qnaCommentFileDAO.findFileIdByCommentId(id);
        if (fileId != null) {
            fileDAO.findById(fileId).ifPresent(oldFile -> {
                new File("C:/file/" + oldFile.getFilePath(), oldFile.getFileName()).delete();
            });
            qnaCommentFileDAO.deleteByCommentId(id);
            fileDAO.delete(fileId);
        }
        qnaCommentDAO.delete(id, memberId);
    }

    public void update(Long id, Long memberId, String content, boolean removeFile, MultipartFile file) {
        QnaCommentVO vo = QnaCommentVO.builder()
                .id(id)
                .memberId(memberId)
                .qnaCommentContent(content)
                .build();
        qnaCommentDAO.update(vo);

        // 기존 파일 삭제
        if (removeFile) {
            Long oldFileId = qnaCommentFileDAO.findFileIdByCommentId(id);
            if (oldFileId != null) {
                fileDAO.findById(oldFileId).ifPresent(oldFile -> {
                    new File("C:/file/" + oldFile.getFilePath(), oldFile.getFileName()).delete();
                });
                qnaCommentFileDAO.deleteByCommentId(id);
                fileDAO.delete(oldFileId);
            }
        }

        // 새 파일 업로드
        if (file != null && !file.isEmpty()) {
            String todayPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileName(UUID.randomUUID() + "_" + file.getOriginalFilename());
            fileDTO.setFileOriginalName(file.getOriginalFilename());
            fileDTO.setFileSize(String.valueOf(file.getSize()));
            fileDTO.setFileContentType(
                    file.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER
            );
            fileDAO.save(fileDTO);
            qnaCommentFileDAO.save(fileDTO.getId(), id);

            File dir = new File("C:/file/" + todayPath);
            if (!dir.exists()) dir.mkdirs();
            try {
                file.transferTo(new File(dir, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
