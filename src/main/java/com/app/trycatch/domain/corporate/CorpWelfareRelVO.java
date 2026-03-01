package com.app.trycatch.domain.corporate;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CorpWelfareRelVO {
    private Long id;
    private Long corpId;
    private String welfareCode;
    private String welfareName;
}
