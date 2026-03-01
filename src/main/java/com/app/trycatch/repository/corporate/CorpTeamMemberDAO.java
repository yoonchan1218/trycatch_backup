package com.app.trycatch.repository.corporate;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.corporate.CorpTeamMemberVO;
import com.app.trycatch.dto.corporate.CorpTeamMemberDTO;
import com.app.trycatch.mapper.corporate.CorpTeamMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CorpTeamMemberDAO {
    private final CorpTeamMemberMapper corpTeamMemberMapper;

    public void save(CorpTeamMemberVO vo) {
        corpTeamMemberMapper.insert(vo);
    }

    public List<CorpTeamMemberDTO> findByCorpId(Long corpId, Criteria criteria) {
        return corpTeamMemberMapper.selectByCorpId(corpId, criteria);
    }

    public int countByCorpId(Long corpId) {
        return corpTeamMemberMapper.selectCountByCorpId(corpId);
    }

    public void delete(Long id, Long corpId) {
        corpTeamMemberMapper.delete(id, corpId);
    }

    public Optional<CorpTeamMemberDTO> findByInviteCode(String inviteCode) {
        return corpTeamMemberMapper.selectByInviteCode(inviteCode);
    }

    public void updateStatusByInviteCode(String inviteCode, String status) {
        corpTeamMemberMapper.updateStatusByInviteCode(inviteCode, status);
    }
}
