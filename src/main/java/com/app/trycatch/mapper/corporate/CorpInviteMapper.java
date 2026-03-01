package com.app.trycatch.mapper.corporate;

import com.app.trycatch.domain.corporate.CorpInviteVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface CorpInviteMapper {

    // 초대 저장
    void insert(CorpInviteVO vo);

    // 초대 코드로 조회 (pending 상태만)
    Optional<CorpInviteVO> selectByInviteCode(@Param("inviteCode") String inviteCode);

    // 초대 상태 변경
    void updateStatus(@Param("inviteCode") String inviteCode, @Param("status") String status);
}
