package com.app.trycatch.repository.corporate;

import com.app.trycatch.domain.corporate.CorpWelfareRelVO;
import com.app.trycatch.mapper.corporate.CorpWelfareRelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CorpWelfareRelDAO {
    private final CorpWelfareRelMapper corpWelfareRelMapper;

    public void saveAll(List<CorpWelfareRelVO> list) {
        if (list != null && !list.isEmpty()) {
            corpWelfareRelMapper.insertAll(list);
        }
    }

    public void deleteByCorpId(Long corpId) {
        corpWelfareRelMapper.deleteByCorpId(corpId);
    }

    public List<CorpWelfareRelVO> findByCorpId(Long corpId) {
        return corpWelfareRelMapper.selectByCorpId(corpId);
    }
}
