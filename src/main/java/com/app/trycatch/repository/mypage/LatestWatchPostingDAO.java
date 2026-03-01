package com.app.trycatch.repository.mypage;

import com.app.trycatch.domain.mypage.LatestWatchPostingVO;
import com.app.trycatch.dto.mypage.LatestWatchPostingDTO;
import com.app.trycatch.mapper.mypage.LatestWatchPostingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LatestWatchPostingDAO {
    private final LatestWatchPostingMapper latestWatchPostingMapper;

    public List<LatestWatchPostingDTO> findAllByMemberId(Long memberId) {
        return latestWatchPostingMapper.selectAllByMemberId(memberId);
    }

    public void save(LatestWatchPostingVO latestWatchPostingVO) {
        latestWatchPostingMapper.insert(latestWatchPostingVO);
    }
}
