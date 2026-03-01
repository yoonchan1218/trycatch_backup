package com.app.trycatch.dto.qna;

import com.app.trycatch.common.enumeration.qna.QnaStatus;
import com.app.trycatch.domain.qna.QnaVO;
import com.app.trycatch.dto.file.FileDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class QnaDTO {
    private Long id;
    private Long individualMemberId;
    private String qnaTitle;
    private String qnaContent;
    private int qnaViewCount;
    private QnaStatus qnaStatus;
    private Long jobCategorySmallId;
    private String jobCategoryName;
    private String companyName;     // 기업선배 기업명
    private String collegeFriend;   // 동문선배 학교명
    private String individualMemberEducation;
    private int individualMemberPoint;
    private int individualMemberLevel;
    private int individualMemberPostCount;
    private int individualMemberQuestionCount;
    private int qnaLikeCount;
    private int qnaCommentCount;
    private String memberName;
    private String memberProfileImageUrl;
    private Long qnaFileId;     // 첨부 파일 ID (tbl_qna.qna_file_id) — 썸네일용
    private String filePath;    // 썸네일 이미지 경로 (목록용)
    private String fileName;    // 썸네일 이미지 파일명 (목록용)
    private List<FileDTO> files = new ArrayList<>(); // 전체 첨부 이미지 목록 (상세용)
    private String createdDatetime;
    private String updatedDatetime;
    private boolean likedByCurrentUser;

    public QnaVO toQnaVO() {
        return QnaVO.builder()
                .id(id)
                .individualMemberId(individualMemberId)
                .qnaTitle(qnaTitle)
                .qnaContent(qnaContent)
                .jobCategorySmallId(jobCategorySmallId)
                .jobCategoryName(jobCategoryName)
                .qnaViewCount(qnaViewCount)
                .qnaStatus(qnaStatus)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }

}
