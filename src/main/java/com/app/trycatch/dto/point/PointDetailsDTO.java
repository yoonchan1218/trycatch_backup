package com.app.trycatch.dto.point;

import com.app.trycatch.common.enumeration.point.PointType;
import com.app.trycatch.domain.point.PointDetailsVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class PointDetailsDTO {
    private Long id;
    private Long individualMemberId;
    private PointType pointType;
    private int pointAmount;
    private String chargeMethod;
    private int remainingPointAmount;
    private int paymentAmount;
    private String expireDatetime;
    private String paymentOrderId;
    private String paymentReceiptId;
    private String cancelledDatetime;
    private boolean cancellable;
    private String createdDatetime;
    private String updatedDatetime;

    public String getStatusLabel() {
        if (pointType == null) {
            return "-";
        }

        return switch (pointType) {
            case EARN -> "충전";
            case USE -> "사용";
            case EXPIRE -> "소멸";
            case PURCHASE_CANCEL -> "구매취소";
        };
    }

    public PointDetailsVO toVO() {
        return PointDetailsVO.builder()
                .id(id)
                .individualMemberId(individualMemberId)
                .pointType(pointType)
                .pointAmount(pointAmount)
                .remainingPointAmount(remainingPointAmount)
                .paymentAmount(paymentAmount)
                .expireDatetime(expireDatetime)
                .paymentOrderId(paymentOrderId)
                .paymentReceiptId(paymentReceiptId)
                .cancelledDatetime(cancelledDatetime)
                .build();
    }

    public PointDetailsVO toCancelVO(Long individualMemberId, int remainingPointAmount) {
        return PointDetailsVO.builder()
                .individualMemberId(individualMemberId)
                .pointType(PointType.PURCHASE_CANCEL)
                .pointAmount(-pointAmount)
                .remainingPointAmount(remainingPointAmount)
                .paymentAmount(-paymentAmount)
                .expireDatetime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .paymentOrderId(paymentOrderId)
                .paymentReceiptId(paymentReceiptId)
                .build();
    }
}
