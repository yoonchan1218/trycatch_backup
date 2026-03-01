package com.app.trycatch.mapper.alarm;

import com.app.trycatch.dto.alarm.CorpAlramDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CorporateAlramMapper {
    List<CorpAlramDTO> selectAllByCorpId(Long corpId);
    int selectUnreadCountByCorpId(Long corpId);
    void updateReadByCorpId(Long corpId);
    void insertCorpNotification(CorpAlramDTO corpAlramDTO);
    boolean existsByCorpId(Long corpId);
}
