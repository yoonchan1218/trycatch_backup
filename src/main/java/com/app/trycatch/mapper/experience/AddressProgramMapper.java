package com.app.trycatch.mapper.experience;

import com.app.trycatch.domain.experience.AddressProgramVO;
import com.app.trycatch.dto.member.AddressDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AddressProgramMapper {
    public void insert(AddressProgramVO addressProgramVO);

    public Optional<AddressDTO> selectByExperienceProgramId(Long experienceProgramId);

    public void deleteByExperienceProgramId(Long experienceProgramId);
}
