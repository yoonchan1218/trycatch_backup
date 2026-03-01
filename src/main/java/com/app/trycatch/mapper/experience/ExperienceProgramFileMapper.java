package com.app.trycatch.mapper.experience;

import com.app.trycatch.domain.experience.ExperienceProgramFileVO;
import com.app.trycatch.dto.experience.ExperienceProgramFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExperienceProgramFileMapper {
    public List<ExperienceProgramFileDTO> selectAllByExperienceProgramId(Long id);
    public void insert(ExperienceProgramFileVO experienceProgramFileVO);
    public void delete(Long id);
}
