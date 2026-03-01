package com.app.trycatch.repository.mypage;

import com.app.trycatch.domain.mypage.ScrapPostingVO;
import com.app.trycatch.dto.mypage.ScrapPostingDTO;
import com.app.trycatch.mapper.mypage.ScrapPostingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScrapPostingDAO {
    private final ScrapPostingMapper scrapPostingMapper;

    public List<ScrapPostingDTO> findAllByMemberId(Long memberId) {
        return scrapPostingMapper.selectAllByMemberId(memberId);
    }

    public void save(ScrapPostingVO scrapPostingVO) {
        scrapPostingMapper.insert(scrapPostingVO);
    }

    public void updateStatus(ScrapPostingVO scrapPostingVO) {
        scrapPostingMapper.updateStatus(scrapPostingVO);
    }
}
