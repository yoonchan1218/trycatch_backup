package com.app.trycatch.mapper.member;

import com.app.trycatch.domain.member.AddressVO;
import com.app.trycatch.dto.member.AddressDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
    public void insert(AddressVO  addressVO);

    public void insertForProgram(AddressDTO addressDTO);

    public void update(AddressVO addressVO);

    public void delete(Long id);
}
