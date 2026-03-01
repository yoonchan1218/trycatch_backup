package com.app.trycatch.domain.mypage;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Getter
@ToString
@SuperBuilder
public class LatestWatchPostingVO {
    private Long id;
    private Long memberId;
    private Long experienceProgramId;
    private String createdDatetime;
}
