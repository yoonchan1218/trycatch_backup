package com.app.trycatch.domain.point;

import com.app.trycatch.audit.Period;
import com.app.trycatch.common.enumeration.point.PointType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Getter
@ToString(callSuper = true)
@SuperBuilder
public class PointDetailsVO extends Period {
    private Long id;
    private Long individualMemberId;
    private PointType pointType;
    private int pointAmount;
    private int remainingPointAmount;
    private int paymentAmount;
    private String expireDatetime;
    private String paymentOrderId;
    private String paymentReceiptId;
    private String cancelledDatetime;
}
