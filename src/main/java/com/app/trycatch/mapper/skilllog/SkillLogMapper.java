package com.app.trycatch.mapper.skilllog;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.common.search.Search;
import com.app.trycatch.domain.skilllog.SkillLogVO;
import com.app.trycatch.dto.skilllog.SkillLogAsideDTO;
import com.app.trycatch.dto.skilllog.SkillLogDTO;
import com.app.trycatch.dto.skilllog.SkillLogDashboardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SkillLogMapper {
//    추가
    public void insert(SkillLogDTO skillLogDTO);
//    aside
    public SkillLogAsideDTO selectProfileByMemberId(Long id);

//    목록
    public List<SkillLogDTO> selectAll(@Param("criteria") Criteria criteria, @Param("search") Search search);
//    목록 개수
    public int selectTotal(@Param("search") Search search);
//    내 글 목록
    public List<SkillLogDTO> selectAllByMemberId(
            @Param("criteria") Criteria criteria,
            @Param("search") Search search,
            @Param("memberId") Long memberId);
//    내 글 목록 개수
    public int selectTotalByMemberId(Long memberId);
//    대시보드
    public SkillLogDashboardDTO selectDashboardByMemberId(Long id);

//    조회
    public Optional<SkillLogDTO> selectById(Long id);
//    조회수 증가
    public void updateSkillLogViewCount(Long id);

//    최근 N개 조회
    public List<SkillLogDTO> selectLatest(int limit);

//    수정
    public void update(SkillLogVO skillLogVO);

//    삭제
    public void updateSkillLogStatus(Long id);
}
