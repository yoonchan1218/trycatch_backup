package com.app.trycatch.repository.alarm;

import com.app.trycatch.dto.alarm.AlramDTO;
import com.app.trycatch.mapper.alarm.IndividualAlramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IndividualAlramDAO {
    private final IndividualAlramMapper individualAlramMapper;

    public List<AlramDTO> findQnaAlramsByMemberId(Long memberId) {
        return individualAlramMapper.selectQnaAlramsByMemberId(memberId);
    }

    public List<AlramDTO> findApplyAlramsByMemberId(Long memberId) {
        return individualAlramMapper.selectApplyAlramsByMemberId(memberId);
    }

    public List<AlramDTO> findSkillLogAlramsByMemberId(Long memberId) {
        return individualAlramMapper.selectSkillLogAlramsByMemberId(memberId);
    }

    public void save(AlramDTO alramDTO) {
        individualAlramMapper.insertNotification(alramDTO);
    }

    public void setReadByMemberId(Long memberId) {
        individualAlramMapper.updateReadByMemberId(memberId);
    }
}
