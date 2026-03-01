package com.app.trycatch.service.qna;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.qna.QnaLikesVO;
import com.app.trycatch.domain.qna.QnaVO;
import com.app.trycatch.dto.file.FileDTO;
import com.app.trycatch.dto.qna.QnaDTO;
import com.app.trycatch.dto.qna.QnaWithPagingDTO;
import com.app.trycatch.mapper.qna.QnaLikesMapper;
import com.app.trycatch.mapper.qna.QnaMapper;
import com.app.trycatch.repository.file.FileDAO;
import com.app.trycatch.repository.qna.QnaDAO;
import com.app.trycatch.repository.qna.QnaFileDAO;
import com.app.trycatch.service.point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class QnaService {
    private final QnaMapper qnaMapper;
    private final QnaLikesMapper qnaLikesMapper;
    private final QnaDAO qnaDAO;
    private final FileDAO fileDAO;
    private final QnaFileDAO qnaFileDAO;
    private final PointService pointService;

    public void write(QnaVO qnaVO, ArrayList<MultipartFile> files) {
        pointService.usePoint(qnaVO.getIndividualMemberId(), 5);
        qnaDAO.save(qnaVO);
        String todayPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        boolean firstFile = true;
        for (MultipartFile file : files) {
            if (file.getOriginalFilename().isEmpty()) continue;
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileName(UUID.randomUUID() + "_" + file.getOriginalFilename());
            fileDTO.setFileOriginalName(file.getOriginalFilename());
            fileDTO.setFileSize(String.valueOf(file.getSize()));
            fileDTO.setFileContentType(
                file.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER
            );
            fileDAO.save(fileDTO);
            qnaFileDAO.save(fileDTO.getId(), qnaVO.getId());
            if (firstFile) {
                qnaMapper.updateFileId(qnaVO.getId(), fileDTO.getId());
                firstFile = false;
            }
            File dir = new File("C:/file/" + todayPath);
            if (!dir.exists()) dir.mkdirs();
            try {
                file.transferTo(new File(dir, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public QnaWithPagingDTO list(int page, int sort, String keyword) {
        QnaWithPagingDTO result = new QnaWithPagingDTO();
        int total = qnaDAO.findTotal(keyword);
        Criteria criteria = new Criteria(page, total);
        List<QnaDTO> qnas = qnaDAO.findAll(criteria, sort, keyword);
        criteria.setHasMore(qnas.size() > criteria.getRowCount());
        if (criteria.isHasMore()) {
            qnas.remove(qnas.size() - 1);
        }
        result.setQnas(qnas);
        result.setCriteria(criteria);
        result.setTotal(total);
        return result;
    }

    public QnaDTO detail(Long id, Long memberId) {
        qnaMapper.increaseViewCount(id);
        QnaDTO qna = qnaMapper.selectById(id);
        if (qna != null && memberId != null) {
            qna.setLikedByCurrentUser(qnaLikesMapper.existsByMemberAndQna(memberId, id) > 0);
        }
        return qna;
    }

    public int toggleLike(Long memberId, Long qnaId) {
        if (qnaLikesMapper.existsByMemberAndQna(memberId, qnaId) > 0) {
            qnaLikesMapper.deleteByMemberAndQna(memberId, qnaId);
        } else {
            qnaLikesMapper.insert(QnaLikesVO.builder().memberId(memberId).qnaId(qnaId).build());
        }
        return qnaLikesMapper.countByQnaId(qnaId);
    }

    public void update(Long memberId, QnaVO qnaVO, List<Long> deletedFileIds, ArrayList<MultipartFile> files) {
        QnaDTO qna = qnaMapper.selectById(qnaVO.getId());
        if (qna == null || !qna.getIndividualMemberId().equals(memberId)) return;

        // 텍스트 필드 변경 여부 (null과 "" 동일 처리)
        boolean textChanged = !ne(qna.getQnaTitle()).equals(ne(qnaVO.getQnaTitle()))
                || !ne(qna.getQnaContent()).equals(ne(qnaVO.getQnaContent()))
                || !Objects.equals(qna.getJobCategorySmallId(), qnaVO.getJobCategorySmallId())
                || !ne(qna.getJobCategoryName()).equals(ne(qnaVO.getJobCategoryName()))
                || !ne(qna.getCompanyName()).equals(ne(qnaVO.getCompanyName()))
                || !ne(qna.getCollegeFriend()).equals(ne(qnaVO.getCollegeFriend()));

        // 파일 변경 여부 (파일 삭제 요청 or 새 파일 추가)
        boolean fileChanged = !deletedFileIds.isEmpty()
                || files.stream().anyMatch(f -> !f.getOriginalFilename().isEmpty());

        // 변경이 있을 때만 UPDATE 실행 → updated_datetime 갱신
        if (textChanged || fileChanged) {
            qnaDAO.update(qnaVO);
        }

        // 삭제 요청된 기존 파일 매핑 제거
        if (!deletedFileIds.isEmpty()) {
            // FK 제약: tbl_qna.qna_file_id → tbl_qna_file.id 이므로 먼저 null 처리
            qnaMapper.updateFileId(qnaVO.getId(), null);
            for (Long fileId : deletedFileIds) {
                qnaFileDAO.deleteById(fileId, qnaVO.getId());
            }
        }

        // 새 파일 저장
        String todayPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        for (MultipartFile file : files) {
            if (file.getOriginalFilename().isEmpty()) continue;
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileName(UUID.randomUUID() + "_" + file.getOriginalFilename());
            fileDTO.setFileOriginalName(file.getOriginalFilename());
            fileDTO.setFileSize(String.valueOf(file.getSize()));
            fileDTO.setFileContentType(
                file.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER
            );
            fileDAO.save(fileDTO);
            qnaFileDAO.save(fileDTO.getId(), qnaVO.getId());
            File dir = new File("C:/file/" + todayPath);
            if (!dir.exists()) dir.mkdirs();
            try {
                file.transferTo(new File(dir, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete(Long memberId, Long qnaId) {
        QnaDTO qna = qnaMapper.selectById(qnaId);
        if (qna != null && qna.getIndividualMemberId().equals(memberId)) {
            qnaMapper.delete(qnaId);
        }
    }

    public List<QnaDTO> getTopByViewCount(int limit) {
        return qnaDAO.findTopByViewCount(limit);
    }

    public List<QnaDTO> getLatest(int limit) {
        return qnaDAO.findLatest(limit);
    }

    // null과 빈 문자열("")을 동일하게 처리하는 헬퍼
    private String ne(String s) {
        return s == null ? "" : s;
    }
}
