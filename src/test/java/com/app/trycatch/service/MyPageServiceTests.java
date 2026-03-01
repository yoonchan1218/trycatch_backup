package com.app.trycatch.service;

import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.dto.mypage.MyPageNotificationDTO;
import com.app.trycatch.dto.mypage.MyPageProfileDTO;
import com.app.trycatch.dto.mypage.MyPageUpdateDTO;
import com.app.trycatch.service.mypage.MyPageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

@SpringBootTest
@Slf4j
public class MyPageServiceTests {
//    @Autowired
//    private MyPageService myPageService;
//
//    @Test
//    public void testGetProfile() {
//        MyPageProfileDTO profile = myPageService.getProfile(1L);
//        log.info("{}......................", profile);
//    }
//
//    @Test
//    public void testUpdateProfile() {
//        MyPageUpdateDTO myPageUpdateDTO = new MyPageUpdateDTO();
//        myPageUpdateDTO.setMemberName("홍길동");
//        myPageUpdateDTO.setMemberEmail("test@test.com");
//        myPageUpdateDTO.setMemberPhone("010-1234-5678");
//        myPageUpdateDTO.setIndividualMemberBirth("1990-01-01");
//        myPageUpdateDTO.setIndividualMemberGender(Gender.MAN);
//        myPageUpdateDTO.setIndividualMemberEducation("대졸");
//        myPageUpdateDTO.setAddressZipcode("12345");
//        myPageUpdateDTO.setAddressText("서울시 강남구");
//        myPageUpdateDTO.setAddressDetail("101호");
//
//        myPageService.updateProfile(1L, myPageUpdateDTO);
//        log.info("회원정보 수정 완료 - {}", myPageUpdateDTO);
//    }
//
//    @Test
//    public void testDeactivateMember() {
//        myPageService.deactivateMember(1L, "홍길동");
//        log.info("회원탈퇴 처리 완료 - memberId: 1");
//    }
//
//    @Test
//    public void testGetNotifications() {
//        List<MyPageNotificationDTO> notifications = myPageService.getNotifications(1L);
//        notifications.forEach(notification -> log.info("{}......................", notification));
//    }
//
//    @Test
//    public void testReadNotification() {
//        myPageService.readNotification(1L, 1L);
//        log.info("알림 읽음 처리 완료 - memberId: 1, notificationId: 1");
//    }
//
//    @Test
//    public void testUploadProfileImage() {
//        MockMultipartFile mockFile = new MockMultipartFile(
//                "file",
//                "profile.png",
//                "image/png",
//                "테스트 이미지 내용".getBytes()
//        );
//
//        String imageUrl = myPageService.uploadProfileImage(mockFile);
//        log.info("프로필 이미지 업로드 완료 - {}", imageUrl);
//    }
}
