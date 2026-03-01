package com.app.trycatch.repository.experience;

import com.app.trycatch.domain.experience.ExperienceProgramFileVO;
import com.app.trycatch.dto.experience.ExperienceProgramFileDTO;
import com.app.trycatch.mapper.experience.ExperienceProgramFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExperienceProgramFileDAO {
    private final ExperienceProgramFileMapper experienceProgramFileMapper;

    public List<ExperienceProgramFileDTO> findAllByExperienceProgramId (Long id) {
        return experienceProgramFileMapper.selectAllByExperienceProgramId(id);
    }

    public void save(ExperienceProgramFileVO vo) {
        experienceProgramFileMapper.insert(vo);
    }

    public void delete(Long id) {
        experienceProgramFileMapper.delete(id);
    }
}
