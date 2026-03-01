package com.app.trycatch.controller.mypage;

import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.dto.member.MemberDTO;
import com.app.trycatch.dto.mypage.ApplyListDTO;
import com.app.trycatch.dto.mypage.ApplyListWithPagingDTO;
import com.app.trycatch.dto.mypage.MyPageUpdateDTO;
import com.app.trycatch.dto.mypage.ScrapPostingDTO;
import java.util.List;
import com.app.trycatch.service.mypage.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;
    private final HttpSession session;

    @GetMapping({"", "/"})
    public RedirectView goToMyPageRoot() {
        return new RedirectView("/mypage/mypage");
    }
//    마이페이지 완료
    @GetMapping("mypage")
    public String goToMyPage(Model model) {
        Long memberId = getSessionMemberId();
        model.addAttribute("profile", myPageService.getProfile(memberId));
        model.addAttribute("latestWatchPostings", myPageService.getLatestWatchPostings(memberId));
        model.addAttribute("scrapPostings", myPageService.getScrapPostings(memberId));
        model.addAttribute("topPostings", myPageService.getTopPostings(10));
        model.addAttribute("topPublicPostings", myPageService.getTopPublicPostingsByLatest(5));
        model.addAttribute("top100Postings", myPageService.getTopPostings(5));
        model.addAttribute("topQnas", myPageService.getTopQnas(3));
        return "mypage/mypage";
    }

    @PostMapping("latest-watch")
    @ResponseBody
    public boolean addLatestWatchPosting(Long experienceProgramId) {
        Long memberId = getSessionMemberId();
        myPageService.addLatestWatchPosting(memberId, experienceProgramId);
        return true;
    }

    @PostMapping("scrap")
    @ResponseBody
    public boolean addScrap(Long experienceProgramId) {
        Long memberId = getSessionMemberId();
        myPageService.addScrap(memberId, experienceProgramId);
        return true;
    }

    @PostMapping("scrap/toggle")
    @ResponseBody
    public boolean toggleScrap(Long scrapId, String scrapStatus) {
        ScrapPostingDTO dto = new ScrapPostingDTO();
        dto.setId(scrapId);
        dto.setScrapStatus(Status.getStatus(scrapStatus));
        myPageService.toggleScrap(dto);
        return true;
    }

    @GetMapping("change-my-information")
    public String goToChangeMyInformation(Model model) {
        Long memberId = getSessionMemberId();
        model.addAttribute("profile", myPageService.getProfile(memberId));
        return "mypage/change-my-information";
    }

    @PostMapping("change-my-information")
    public RedirectView changeMyInformation(MyPageUpdateDTO myPageUpdateDTO) {
        Long memberId = getSessionMemberId();
        myPageService.updateProfile(memberId, myPageUpdateDTO);
        return new RedirectView("/mypage/mypage");
    }

    @GetMapping("notification")
    public String goToNotification(Model model) {
        Long memberId = getSessionMemberId();
        model.addAttribute("notificationsByDate", myPageService.getNotificationsGroupedByDate(memberId));
        return "mypage/notification";
    }

    @GetMapping("experience")
    public String goToExperience(@RequestParam(defaultValue = "1") int page, Model model) {
        Long memberId = getSessionMemberId();
        model.addAttribute("profile", myPageService.getProfile(memberId));
        ApplyListWithPagingDTO result = myPageService.getApplyListWithPagingAndFilter(
                memberId, page, null, null, null, null, null);
        model.addAttribute("applies", result.getApplies());
        model.addAttribute("criteria", result.getCriteria());
        model.addAttribute("appliedCount", result.getAppliedCount());
        model.addAttribute("documentPassCount", result.getDocumentPassCount());
        model.addAttribute("documentFailCount", result.getDocumentFailCount());
        model.addAttribute("activityDoneCount", result.getActivityDoneCount());
        return "mypage/experience";
    }

    @GetMapping("unsubscribe")
    public String goToUnsubscribe(Model model) {
        Long memberId = getSessionMemberId();
        model.addAttribute("profile", myPageService.getProfile(memberId));
        return "mypage/unsubscribe";
    }

    @PostMapping("unsubscribe")
    public RedirectView unsubscribe(String memberName) {
        Long memberId = getSessionMemberId();
        myPageService.deactivateMember(memberId, memberName);
        session.invalidate();
        return new RedirectView("/main/log-in");
    }

    @GetMapping("experience/filter")
    @ResponseBody
    public ApplyListWithPagingDTO filterApplyList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String fromDt,
            @RequestParam(required = false) String toDt,
            @RequestParam(required = false) String programStatus,
            @RequestParam(required = false) String applyStatus,
            @RequestParam(required = false) String keyword) {
        Long memberId = getSessionMemberId();
        return myPageService.getApplyListWithPagingAndFilter(
                memberId, page, fromDt, toDt, programStatus, applyStatus, keyword);
    }

    @PostMapping("experience/cancel")
    @ResponseBody
    public boolean cancelApply(Long applyId) {
        Long memberId = getSessionMemberId();
        return myPageService.cancelApply(memberId, applyId);
    }

    @PostMapping("profile-image")
    @ResponseBody
    public String uploadProfileImage(@RequestParam("file") MultipartFile file) {
        Long memberId = getSessionMemberId();
        String imageUrl = myPageService.uploadProfileImage(memberId, file);
        Object member = session.getAttribute("member");
        if (member instanceof IndividualMemberDTO individualMemberDTO) {
            individualMemberDTO.setMemberProfileImageUrl(imageUrl);
        }
        return imageUrl;
    }

    @GetMapping("logout")
    public RedirectView logout() {
        Object member = session.getAttribute("member");
        if (member instanceof IndividualMemberDTO kakaoMember && kakaoMember.getProvider() == com.app.trycatch.common.enumeration.member.Provider.KAKAO) {
            session.invalidate();
            return new RedirectView("https://kauth.kakao.com/oauth/logout?client_id=6c9664c00ac5573fa3d8f1caf80e67f3&logout_redirect_uri=http://localhost:10000/main/log-in");
        }
        session.invalidate();
        return new RedirectView("/main/log-in");
    }

    private Long getSessionMemberId() {
        Object member = session.getAttribute("member");
        if (member instanceof MemberDTO memberDTO && memberDTO.getId() != null) {
            return memberDTO.getId();
        }
        if (member instanceof IndividualMemberDTO individualMemberDTO && individualMemberDTO.getId() != null) {
            return individualMemberDTO.getId();
        }
        throw new com.app.trycatch.common.exception.UnauthorizedMemberAccessException();
    }

}
