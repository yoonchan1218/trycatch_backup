package com.app.trycatch.mapper.corporate;

import com.app.trycatch.domain.corporate.CorpWelfareRelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CorpWelfareRelMapper {
    public void insertAll(@Param("list") List<CorpWelfareRelVO> list);

    public void deleteByCorpId(Long corpId);

    public List<CorpWelfareRelVO> selectByCorpId(Long corpId);
}
