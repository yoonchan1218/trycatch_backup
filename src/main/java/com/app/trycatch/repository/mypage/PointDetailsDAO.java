package com.app.trycatch.repository.mypage;

import com.app.trycatch.domain.mypage.PointDetailsVO;
import com.app.trycatch.dto.mypage.PointDetailsDTO;
import com.app.trycatch.mapper.mypage.PointDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PointDetailsDAO {
    private final PointDetailsMapper pointDetailsMapper;

    public List<PointDetailsDTO> findAllByMemberId(Long memberId) {
        return pointDetailsMapper.selectAllByMemberId(memberId);
    }

    public void save(PointDetailsVO pointDetailsVO) {
        pointDetailsMapper.insert(pointDetailsVO);
    }
}
