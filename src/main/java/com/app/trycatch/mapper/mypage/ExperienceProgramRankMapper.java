package com.app.trycatch.mapper.mypage;

import com.app.trycatch.dto.mypage.ExperienceProgramRankDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExperienceProgramRankMapper {
    // 조회수 상위 N개 (원픽 공고, TOP100 공통)
    List<ExperienceProgramRankDTO> selectTopByViewCount(@Param("limit") int limit);
    // 공기업 타입 필터 + 조회수 상위 N개
    List<ExperienceProgramRankDTO> selectTopPublicByViewCount(@Param("limit") int limit);
    // 공기업 타입 필터 + 최신순 N개
    List<ExperienceProgramRankDTO> selectTopPublicByLatest(@Param("limit") int limit);
}
