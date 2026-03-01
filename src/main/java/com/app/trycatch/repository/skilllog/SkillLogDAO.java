package com.app.trycatch.repository.skilllog;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.common.search.Search;
import com.app.trycatch.domain.skilllog.SkillLogVO;
import com.app.trycatch.dto.skilllog.SkillLogAsideDTO;
import com.app.trycatch.dto.skilllog.SkillLogDTO;
import com.app.trycatch.mapper.skilllog.SkillLogMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SkillLogDAO {
    private final SkillLogMapper skillLogMapper;

//    추가
    public void save(SkillLogDTO skillLogDTO) {
        skillLogMapper.insert(skillLogDTO);
    }
//    aside
    public SkillLogAsideDTO findProfileByMemberId(Long id) {
        return skillLogMapper.selectProfileByMemberId(id);
    }

//    목록
    public List<SkillLogDTO> findAll(Criteria criteria, Search search) {
        return skillLogMapper.selectAll(criteria, search);
    }
//    전체 개수
    public int findTotal(Search search) {
        return skillLogMapper.selectTotal(search);
    }
//    내 글 목록
    public List<SkillLogDTO> findAllByMemberId(
            @Param("criteria") Criteria criteria,
            @Param("search") Search search,
            @Param("memberId") Long memberId) {
        return skillLogMapper.selectAllByMemberId(criteria, search, memberId);
    }
//    전체 개수
    public int findTotalByMemberId(Long memberId) {
        return skillLogMapper.selectTotalByMemberId(memberId);
    }


//    최근 N개 조회
    public List<SkillLogDTO> findLatest(int limit) {
        return skillLogMapper.selectLatest(limit);
    }

//    조회
    public Optional<SkillLogDTO> findById(Long id) {
        return skillLogMapper.selectById(id);
    }
//    조회수 증가
    public void setSkillLogViewCount(Long id) {
        skillLogMapper.updateSkillLogViewCount(id);
    }

//    수정
    public void setSkillLog(SkillLogVO skillLogVO) {
        skillLogMapper.update(skillLogVO);
    }

//    삭제
    public void setSkillLogStatus(Long id) {
        skillLogMapper.updateSkillLogStatus(id);
    }
}
