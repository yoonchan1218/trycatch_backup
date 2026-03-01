package com.app.trycatch.mapper.file;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;
import java.util.Map;

@Mapper
public interface CorpLogoFileMapper {
    void insert(@Param("id") Long fileId, @Param("corpId") Long corpId);

    void deleteByCorpId(@Param("corpId") Long corpId);

    Optional<Map<String, Object>> selectByCorpId(@Param("corpId") Long corpId);
}
