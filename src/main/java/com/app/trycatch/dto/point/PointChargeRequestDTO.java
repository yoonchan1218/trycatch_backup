package com.app.trycatch.dto.point;

import com.app.trycatch.common.enumeration.point.PointType;
import com.app.trycatch.domain.point.PointDetailsVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PointChargeRequestDTO {
    private int pointAmount;
    private int paymentAmount;
    private String paymentOrderId;
    private String paymentReceiptId;

    public PointDetailsVO toVO(Long individualMemberId) {
        return PointDetailsVO.builder()
                .individualMemberId(individualMemberId)
                .pointType(PointType.EARN)
                .pointAmount(pointAmount)
                .remainingPointAmount(pointAmount)
                .paymentAmount(paymentAmount)
                .expireDatetime(LocalDateTime.now().plusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .paymentOrderId(paymentOrderId)
                .paymentReceiptId(paymentReceiptId)
                .build();
    }
}
