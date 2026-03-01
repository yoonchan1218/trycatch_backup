package com.app.trycatch.service.Alarm;

import com.app.trycatch.dto.alarm.AlramDTO;
import com.app.trycatch.repository.alarm.IndividualAlramDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class IndividualAlramService {
    private final IndividualAlramDAO individualAlramDAO;


    public List<AlramDTO> findQnaAlrams(Long memberId) {
        return individualAlramDAO.findQnaAlramsByMemberId(memberId);
    }


    public List<AlramDTO> findApplyAlrams(Long memberId) {
        return individualAlramDAO.findApplyAlramsByMemberId(memberId);
    }


    public List<AlramDTO> findSkillLogAlrams(Long memberId) {
        return individualAlramDAO.findSkillLogAlramsByMemberId(memberId);
    }

    public void saveNotification(AlramDTO alramDTO) {
        individualAlramDAO.save(alramDTO);
    }

    public void readAll(Long memberId) {
        individualAlramDAO.setReadByMemberId(memberId);
    }
}
