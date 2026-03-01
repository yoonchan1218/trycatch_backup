package com.app.trycatch.dto.member;

import com.app.trycatch.domain.member.AddressVO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AddressDTO {
    private Long id;
    private String addressZipcode;
    private String addressAddress;
    private String addressDetail;

    public AddressVO toVO() {
        return AddressVO.builder()
                .id(id)
                .addressZipcode(addressZipcode)
                .addressAddress(addressAddress)
                .addressDetail(addressDetail)
                .build();
    }
}
