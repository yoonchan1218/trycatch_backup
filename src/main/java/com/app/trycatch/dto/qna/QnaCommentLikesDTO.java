package com.app.trycatch.dto.qna;

import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.domain.qna.QnaCommentLikesVO;
import com.app.trycatch.domain.qna.QnaCommentVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class QnaCommentLikesDTO {
    private Long id;
    private Long qnaId;
    private Long corpId;
    private Long qnaCommentParent;
    private String qnaCommentContent;
    private int qnaCommentLikeCount;
    private Long fkMemberId;
    private String memberId;
    private String memberPassword;
    private Gender memberGender;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private Long addressId;
    private Status memberStatus;
    private boolean memberAgreePrivacy;
    private boolean memberAgreeMarketing;
    private String createdDateTime;
    private String updatedDateTime;

    public QnaCommentLikesVO toQnaCommentLikesVO() {
        return QnaCommentLikesVO.builder()
                .id(id)
                .fkMemberId(fkMemberId)
                .build();
    }

    public QnaCommentVO toQnaCommentVO() {
        return QnaCommentVO.builder()
                .id(id)
                .qnaId(qnaId)
                .qnaCommentParent(qnaCommentParent)
                .qnaCommentContent(qnaCommentContent)
                .build();
    }

    public MemberVO toMemberVO() {
        return MemberVO.builder()
                .id(id)
                .memberId(memberId)
                .memberPassword(memberPassword)
                .memberName(memberName)
                .memberEmail(memberEmail)
                .memberPhone(memberPhone)
                .addressId(addressId)
                .memberStatus(memberStatus)
                .memberAgreePrivacy(memberAgreePrivacy)
                .memberAgreeMarketing(memberAgreeMarketing)
                .createdDatetime(createdDateTime)
                .updatedDatetime(updatedDateTime)
                .build();
    }
}
