package com.app.trycatch.domain.member;

import com.app.trycatch.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of="id")
@Getter
@ToString(callSuper = true)
@SuperBuilder
public class AddressVO extends Period {
    private Long id;
    private String addressZipcode;
    private String addressAddress;
    private String addressDetail;
}
