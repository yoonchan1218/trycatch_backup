package com.app.trycatch.dto.experience;

import com.app.trycatch.domain.experience.AddressProgramVO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AddressProgramDTO {
    private Long id;
    private Long addressId;
    private Long experienceProgramId;
    private String createdDatetime;
    private String updatedDatetime;

    public AddressProgramVO toAddressProgramVO() {
        return AddressProgramVO.builder()
                .id(id)
                .addressId(addressId)
                .experienceProgramId(experienceProgramId)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }

//    toAdressVO
}
