package com.app.trycatch.mapper.mypage;

import com.app.trycatch.dto.mypage.MyPageNotificationDTO;
import com.app.trycatch.dto.mypage.MyPageProfileDTO;
import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.domain.member.IndividualMemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MyPageMapper {
    Optional<MyPageProfileDTO> selectProfileByMemberId(Long memberId);

    void updateMember(MemberVO memberVO);

    void updateIndividualMember(IndividualMemberVO individualMemberVO);

    List<MyPageNotificationDTO> selectNotificationsByMemberId(Long memberId);

    void updateMemberStatusToInactive(Long memberId);

    int updateApplyStatusToCancelled(@Param("memberId") Long memberId, @Param("applyId") Long applyId);

    void updateProfileFileId(@Param("memberId") Long memberId, @Param("fileId") Long fileId);
}
