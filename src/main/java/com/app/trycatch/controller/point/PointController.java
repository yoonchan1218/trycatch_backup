package com.app.trycatch.controller.point;

import com.app.trycatch.common.exception.UnauthorizedMemberAccessException;
import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.dto.point.PointChargeRequestDTO;
import com.app.trycatch.dto.point.PointDetailsWithPagingDTO;
import com.app.trycatch.dto.point.PointOperationResultDTO;
import com.app.trycatch.service.mypage.MyPageService;
import com.app.trycatch.service.point.PointService;
import com.app.trycatch.service.qna.QnaService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final MyPageService myPageService;
    private final QnaService qnaService;
    private final HttpSession session;
//    포인트 완료
    @GetMapping("point")
    public String goToPoint(@RequestParam(defaultValue = "1") int page, Model model) {
        Long individualMemberId = getSessionMemberId();
        PointDetailsWithPagingDTO result = pointService.getPointDetails(individualMemberId, page);
        model.addAttribute("profile", myPageService.getProfile(individualMemberId));
        model.addAttribute("pointDetails", result.getPointDetails());
        model.addAttribute("criteria", result.getCriteria());
        model.addAttribute("sideTopPosts", qnaService.getTopByViewCount(5));
        model.addAttribute("sideLatestPosts", qnaService.getLatest(5));
        return "point/point";
    }

    @PostMapping("point/charge")
    @ResponseBody
    public PointOperationResultDTO chargePoint(@RequestBody PointChargeRequestDTO requestDTO) {
        Long individualMemberId = getSessionMemberId();
        return pointService.chargePoint(individualMemberId, requestDTO);
    }

    @PostMapping("point/cancel/{pointDetailId}")
    @ResponseBody
    public PointOperationResultDTO cancelPoint(@PathVariable Long pointDetailId) {
        Long individualMemberId = getSessionMemberId();
        return pointService.cancelPointCharge(individualMemberId, pointDetailId);
    }

    private Long getSessionMemberId() {
        Object member = session.getAttribute("member");
        if (member instanceof IndividualMemberDTO individualMemberDTO && individualMemberDTO.getId() != null) {
            return individualMemberDTO.getId();
        }
        throw new UnauthorizedMemberAccessException();
    }
}
