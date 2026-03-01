package com.app.trycatch.dto.mypage;

import com.app.trycatch.domain.mypage.PointDetailsVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class PointDetailsDTO {
    private Long id;
    private Long memberId;
    private String pointType;
    private int pointAmount;
    private String createdDatetime;
    private String updatedDatetime;

    public PointDetailsVO toVO() {
        return PointDetailsVO.builder()
                .id(id)
                .memberId(memberId)
                .pointType(pointType)
                .pointAmount(pointAmount)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
