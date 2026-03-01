package com.app.trycatch.repository.experience;

import com.app.trycatch.domain.experience.AddressProgramVO;
import com.app.trycatch.dto.member.AddressDTO;
import com.app.trycatch.mapper.experience.AddressProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressProgramDAO {
    private final AddressProgramMapper addressProgramMapper;

    public void save(AddressProgramVO addressProgramVO) {
        addressProgramMapper.insert(addressProgramVO);
    }

    public Optional<AddressDTO> findByExperienceProgramId(Long experienceProgramId) {
        return addressProgramMapper.selectByExperienceProgramId(experienceProgramId);
    }

    public void deleteByExperienceProgramId(Long experienceProgramId) {
        addressProgramMapper.deleteByExperienceProgramId(experienceProgramId);
    }
}
