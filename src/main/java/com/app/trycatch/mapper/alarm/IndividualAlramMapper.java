package com.app.trycatch.mapper.alarm;

import com.app.trycatch.dto.alarm.AlramDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndividualAlramMapper {
    List<AlramDTO> selectQnaAlramsByMemberId(Long memberId);
    List<AlramDTO> selectApplyAlramsByMemberId(Long memberId);
    List<AlramDTO> selectSkillLogAlramsByMemberId(Long memberId);
    void insertNotification(AlramDTO alramDTO);
    void updateReadByMemberId(Long memberId);
}
