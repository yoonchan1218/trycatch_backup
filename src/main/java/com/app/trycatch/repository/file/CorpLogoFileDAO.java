package com.app.trycatch.repository.file;

import com.app.trycatch.mapper.file.CorpLogoFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CorpLogoFileDAO {
    private final CorpLogoFileMapper corpLogoFileMapper;

    public void save(Long fileId, Long corpId) {
        corpLogoFileMapper.insert(fileId, corpId);
    }

    public void deleteByCorpId(Long corpId) {
        corpLogoFileMapper.deleteByCorpId(corpId);
    }

    public Optional<Map<String, Object>> findByCorpId(Long corpId) {
        return corpLogoFileMapper.selectByCorpId(corpId);
    }
}
