package com.app.trycatch.domain.member;


import com.app.trycatch.common.enumeration.member.Provider;
import lombok.*;

@Getter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OAuthVO {
    private Long id;
    private Provider provider;
}















