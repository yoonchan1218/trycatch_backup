package com.app.trycatch.mapper.corporate;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.corporate.CorpTeamMemberVO;
import com.app.trycatch.dto.corporate.CorpTeamMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CorpTeamMemberMapper {

    // 팀원 추가 (초대)
    void insert(CorpTeamMemberVO vo);

    // 팀원 목록 조회 (페이징)
    List<CorpTeamMemberDTO> selectByCorpId(
            @Param("corpId") Long corpId,
            @Param("criteria") Criteria criteria);

    // 팀원 수 조회
    int selectCountByCorpId(@Param("corpId") Long corpId);

    // 팀원 제거
    void delete(@Param("id") Long id, @Param("corpId") Long corpId);

    // 초대 코드로 팀원 조회
    Optional<CorpTeamMemberDTO> selectByInviteCode(@Param("inviteCode") String inviteCode);

    // 초대 코드로 상태 변경 (수락 처리)
    void updateStatusByInviteCode(@Param("inviteCode") String inviteCode, @Param("status") String status);
}
