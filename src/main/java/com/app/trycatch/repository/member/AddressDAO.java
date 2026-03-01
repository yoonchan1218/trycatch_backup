package com.app.trycatch.repository.member;

import com.app.trycatch.domain.member.AddressVO;
import com.app.trycatch.dto.member.AddressDTO;
import com.app.trycatch.mapper.member.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AddressDAO {
    private final AddressMapper addressMapper;

    public void save(AddressVO addressVO) {
        addressMapper.insert(addressVO);
    }

    public void saveForProgram(AddressDTO addressDTO) {
        addressMapper.insertForProgram(addressDTO);
    }

    public void update(AddressVO addressVO) {
        addressMapper.update(addressVO);
    }

    public void delete(Long id) {
        addressMapper.delete(id);
    }
}