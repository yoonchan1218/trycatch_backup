package com.app.trycatch.dto.member;


import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.common.enumeration.member.Provider;
import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.domain.member.AddressVO;
import com.app.trycatch.domain.member.IndividualMemberVO;
import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.domain.member.OAuthVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class IndividualMemberDTO {
    private Long id;
    private String individualMemberBirth;
    private Gender individualMemberGender;
    private String individualMemberEducation;
    private int individualMemberPoint;
    private int individualMemberLevel;
    private int individualMemberPostCount;
    private int individualMemberQuestionCount;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private Long addressId;
    private String addressZipcode;
    private String addressAddress;
    private String addressDetail;
    private Status memberStatus;
    private boolean memberAgreePrivacy;
    private boolean memberAgreeMarketing;
    private String createdDatetime;
    private String updatedDatetime;
    private Provider provider;
    private String memberProfileImageUrl;

    public MemberVO toMemberVO() {
        return MemberVO.builder()
                .id(id)
                .memberId(memberId)
                .memberPassword(memberPassword)
                .memberName(memberName)
                .memberEmail(memberEmail)
                .memberPhone(memberPhone)
                .addressId(addressId)
                .memberStatus(memberStatus)
                .memberAgreePrivacy(memberAgreePrivacy)
                .memberAgreeMarketing(memberAgreeMarketing)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }

    public IndividualMemberVO toIndividualMemberVO() {
        return IndividualMemberVO.builder()
                .id(id)
                .individualMemberBirth(individualMemberBirth)
                .individualMemberGender(individualMemberGender)
                .individualMemberEducation(individualMemberEducation)
                .individualMemberPoint(individualMemberPoint)
                .individualMemberLevel(individualMemberLevel)
                .individualMemberPostCount(individualMemberPostCount)
                .individualMemberQuestionCount(individualMemberQuestionCount)
                .build();
    }

    public AddressVO toAddressVO() {
        return AddressVO.builder()
                .id(addressId)
                .addressZipcode(addressZipcode)
                .addressAddress(addressAddress)
                .addressDetail(addressDetail)
                .build();
    }

    public OAuthVO toOAuthVO() {
        return OAuthVO.builder().id(id).provider(provider).build();
    }
}
