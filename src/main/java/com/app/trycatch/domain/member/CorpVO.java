package com.app.trycatch.domain.member;

import lombok.*;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CorpVO {
    private Long id;
    private String corpCompanyName;
    private String corpBusinessNumber;
    private String corpCeoName;
    private Long corpKindId;
    private Long corpKindSmallId;
    private String corpCompanyType;
    private String corpCompanySize;
    private String corpEstablishmentDate;
    private String corpWebsiteUrl;
    private String corpFax;
    private Long corpCapital;
    private String corpTotalSales;
    private String corpMainBusiness;
    private String corpPerformance;
    private String corpVision;

}
