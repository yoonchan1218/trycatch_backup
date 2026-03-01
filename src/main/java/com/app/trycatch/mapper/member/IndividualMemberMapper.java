package com.app.trycatch.mapper.member;

import com.app.trycatch.domain.member.IndividualMemberVO;
import com.app.trycatch.dto.member.IndividualMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface IndividualMemberMapper {
    void insert(IndividualMemberVO individualMemberVO);
    Optional<IndividualMemberDTO> selectById(Long id);
}
