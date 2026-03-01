package com.app.trycatch.repository.experience;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.experience.ApplyVO;
import com.app.trycatch.dto.experience.ApplyDTO;
import com.app.trycatch.mapper.experience.ApplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ApplyDAO {
    private final ApplyMapper applyMapper;

    public void save(ApplyVO applyVO) {
        applyMapper.insert(applyVO);
    }

    public boolean existsByProgramIdAndMemberId(Long programId, Long memberId) {
        return applyMapper.selectExistsByProgramIdAndMemberId(programId, memberId) > 0;
    }

//    지원자 목록
    public List<ApplyDTO> findByProgramId(Long programId, String status, String keyword,
                                          String education, String gender, Criteria criteria) {
        return applyMapper.selectByProgramId(programId, status, keyword, education, gender, criteria);
    }

//    지원자 수
    public int countByProgramId(Long programId, String status, String keyword,
                                String education, String gender) {
        return applyMapper.selectCountByProgramId(programId, status, keyword, education, gender);
    }

//    상태별 지원자 수
    public Map<String, Long> countStatusByProgramId(Long programId) {
        return applyMapper.selectStatusCountByProgramId(programId);
    }

//    상태 변경
    public void setStatus(Long id, String applyStatus) {
        applyMapper.updateStatus(id, applyStatus);
    }
}
