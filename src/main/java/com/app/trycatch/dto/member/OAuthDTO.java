package com.app.trycatch.dto.member;

import com.app.trycatch.common.enumeration.member.Provider;
import com.app.trycatch.domain.member.OAuthVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class OAuthDTO {
    private Long id;
    private Provider provider;

    public OAuthVO toVO() {
        return OAuthVO.builder().id(id).provider(provider).build();
    }
}
