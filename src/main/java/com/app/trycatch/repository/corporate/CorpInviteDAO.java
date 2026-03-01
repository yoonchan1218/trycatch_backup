package com.app.trycatch.repository.corporate;

import com.app.trycatch.domain.corporate.CorpInviteVO;
import com.app.trycatch.mapper.corporate.CorpInviteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CorpInviteDAO {
    private final CorpInviteMapper corpInviteMapper;

    public void save(CorpInviteVO vo) {
        corpInviteMapper.insert(vo);
    }

    public Optional<CorpInviteVO> findByInviteCode(String inviteCode) {
        return corpInviteMapper.selectByInviteCode(inviteCode);
    }

    public void updateStatus(String inviteCode, String status) {
        corpInviteMapper.updateStatus(inviteCode, status);
    }
}
