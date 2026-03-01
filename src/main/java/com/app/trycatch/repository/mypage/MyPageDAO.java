package com.app.trycatch.repository.mypage;

import com.app.trycatch.domain.member.IndividualMemberVO;
import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.dto.mypage.MyPageNotificationDTO;
import com.app.trycatch.dto.mypage.MyPageProfileDTO;
import com.app.trycatch.mapper.mypage.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyPageDAO {
    private final MyPageMapper myPageMapper;

    public Optional<MyPageProfileDTO> findProfileByMemberId(Long memberId) {
        return myPageMapper.selectProfileByMemberId(memberId);
    }

    public void updateMember(MemberVO memberVO) {
        myPageMapper.updateMember(memberVO);
    }

    public void updateIndividualMember(IndividualMemberVO individualMemberVO) {
        myPageMapper.updateIndividualMember(individualMemberVO);
    }

    public List<MyPageNotificationDTO> findNotificationsByMemberId(Long memberId) {
        return myPageMapper.selectNotificationsByMemberId(memberId);
    }

    public void deactivateMember(Long memberId) {
        myPageMapper.updateMemberStatusToInactive(memberId);
    }

    public int cancelApply(Long memberId, Long applyId) {
        return myPageMapper.updateApplyStatusToCancelled(memberId, applyId);
    }

    public void updateProfileFileId(Long memberId, Long fileId) {
        myPageMapper.updateProfileFileId(memberId, fileId);
    }
}
