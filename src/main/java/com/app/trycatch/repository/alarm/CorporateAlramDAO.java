package com.app.trycatch.repository.alarm;

import com.app.trycatch.dto.alarm.CorpAlramDTO;
import com.app.trycatch.mapper.alarm.CorporateAlramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CorporateAlramDAO {
    private final CorporateAlramMapper corporateAlramMapper;

    public List<CorpAlramDTO> findAllByCorpId(Long corpId) {
        return corporateAlramMapper.selectAllByCorpId(corpId);
    }

    public int findUnreadCountByCorpId(Long corpId) {
        return corporateAlramMapper.selectUnreadCountByCorpId(corpId);
    }

    public void setReadByCorpId(Long corpId) {
        corporateAlramMapper.updateReadByCorpId(corpId);
    }

    public void save(CorpAlramDTO corpAlramDTO) {
        corporateAlramMapper.insertCorpNotification(corpAlramDTO);
    }

    public boolean existsByCorpId(Long corpId) {
        return corporateAlramMapper.existsByCorpId(corpId);
    }
}
