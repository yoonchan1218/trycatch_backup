package com.app.trycatch.controller.qna;

import com.app.trycatch.domain.qna.QnaVO;
import com.app.trycatch.dto.member.CorpMemberDTO;
import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.dto.member.MemberDTO;
import com.app.trycatch.dto.qna.CorpNameKeywordDTO;
import com.app.trycatch.dto.qna.QnaDTO;
import com.app.trycatch.mapper.qna.QnaJobCategoryMapper;
import com.app.trycatch.mapper.qna.QnaMapper;
import com.app.trycatch.service.qna.QnaCommentService;
import com.app.trycatch.service.qna.QnaService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
@Slf4j
public class QnaController {
    private final HttpSession session;
    private final QnaService qnaService;
    private final QnaCommentService qnaCommentService;
    private final QnaJobCategoryMapper qnaJobCategoryMapper;
    private final QnaMapper qnaMapper;

    // ── 공통: 세션에서 memberId 추출 ─────────────────────────────────────
    private Long extractMemberId(Object member) {
        if (member instanceof MemberDTO memberDTO) {
            return memberDTO.getId();
        } else if (member instanceof IndividualMemberDTO individualMemberDTO) {
            return individualMemberDTO.getId();
        } else if (member instanceof CorpMemberDTO corpMemberDTO) {
            return corpMemberDTO.getId();
        }
        return null;
    }

    @GetMapping("/search-company")
    @ResponseBody
    public List<CorpNameKeywordDTO> searchCompany(@RequestParam String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of();
        }
        return qnaMapper.selectCorpByKeyword(keyword.trim());
    }

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "1") int sort,
                       @RequestParam(defaultValue = "") String keyword,
                       Model model) {
        model.addAttribute("qnaWithPaging", qnaService.list(page, sort, keyword));
        model.addAttribute("loginMember", session.getAttribute("member"));
        model.addAttribute("currentSort", sort);
        model.addAttribute("keyword", keyword);
        model.addAttribute("bannerPopularQnas", qnaMapper.selectTopByViewCount(3));
        model.addAttribute("bannerRecentQnas", qnaMapper.selectLatest(5));
        model.addAttribute("popularQnas", qnaMapper.selectTopByViewCount(5));
        model.addAttribute("sideTopPosts", qnaService.getTopByViewCount(5));
        model.addAttribute("sideLatestPosts", qnaService.getLatest(5));
        return "qna/QnA";
    }

    @GetMapping("/detail")
    public String detail(Long id, Model model) {
        Object member = session.getAttribute("member");
        Long memberId = extractMemberId(member);
        model.addAttribute("qna", qnaService.detail(id, memberId));
        model.addAttribute("loginMember", member);
        model.addAttribute("comments", qnaCommentService.list(id));
        model.addAttribute("hasMyComment", memberId != null && qnaCommentService.hasComment(id, memberId));
        model.addAttribute("sideTopPosts", qnaService.getTopByViewCount(5));
        model.addAttribute("sideLatestPosts", qnaService.getLatest(5));
        return "qna/QnA-detail";
    }

    @GetMapping("/write")
    public String goToWriteForm(Model model) {
        Object member = session.getAttribute("member");
        if (member == null) {
            return "redirect:/main/log-in";
        }
        model.addAttribute("loginMember", member);
        model.addAttribute("sideTopPosts", qnaService.getTopByViewCount(5));
        model.addAttribute("sideLatestPosts", qnaService.getLatest(5));
        return "qna/write";
    }

    @PostMapping("/write")
    public RedirectView write(
            @RequestParam(required = false) String qnaTitle,
            @RequestParam(required = false) String qnaContent,
            @RequestParam(required = false) Long jobCategorySmallCode,
            @RequestParam(required = false) String jobCategoryName,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String collegeFriend,
            @RequestParam(value = "file", required = false) ArrayList<MultipartFile> files
    ) {
        Long memberId = extractMemberId(session.getAttribute("member"));
        Long jobCategorySmallId = (jobCategorySmallCode != null)
                ? qnaJobCategoryMapper.selectIdByCode(jobCategorySmallCode) : null;
        QnaVO qnaVO = QnaVO.builder()
                .individualMemberId(memberId)
                .qnaTitle(qnaTitle)
                .qnaContent(qnaContent)
                .jobCategorySmallId(jobCategorySmallId)
                .jobCategoryName(jobCategoryName)
                .companyName(companyName)
                .collegeFriend(collegeFriend)
                .build();
        qnaService.write(qnaVO, files != null ? files : new ArrayList<>());
        return new RedirectView("/qna/detail?id=" + qnaVO.getId());
    }

    @GetMapping("/update")
    public String goToUpdateForm(Long id, Model model) {
        Object member = session.getAttribute("member");
        if (member == null) {
            return "redirect:/main/log-in";
        }
        Long memberId = extractMemberId(member);
        QnaDTO qna = qnaMapper.selectById(id);
        if (qna == null || !qna.getIndividualMemberId().equals(memberId)) {
            return "redirect:/qna/list";
        }
        model.addAttribute("qna", qna);
        model.addAttribute("loginMember", member);
        model.addAttribute("sideTopPosts", qnaService.getTopByViewCount(5));
        model.addAttribute("sideLatestPosts", qnaService.getLatest(5));
        return "qna/update";
    }

    @PostMapping("/update")
    public RedirectView update(
            @RequestParam Long qnaId,
            @RequestParam(required = false) String qnaTitle,
            @RequestParam(required = false) String qnaContent,
            @RequestParam(required = false) Long jobCategorySmallId,
            @RequestParam(required = false) String jobCategoryName,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String collegeFriend,
            @RequestParam(required = false, defaultValue = "") String deletedFileIds,
            @RequestParam(value = "file", required = false) ArrayList<MultipartFile> files
    ) {
        Long memberId = extractMemberId(session.getAttribute("member"));
        List<Long> deletedIds = new ArrayList<>();
        if (deletedFileIds != null && !deletedFileIds.isBlank()) {
            for (String s : deletedFileIds.split(",")) {
                try { deletedIds.add(Long.parseLong(s.trim())); } catch (NumberFormatException ignored) {}
            }
        }
        QnaVO qnaVO = QnaVO.builder()
                .id(qnaId)
                .qnaTitle(qnaTitle)
                .qnaContent(qnaContent)
                .jobCategorySmallId(jobCategorySmallId)
                .jobCategoryName(jobCategoryName)
                .companyName(companyName)
                .collegeFriend(collegeFriend)
                .build();
        qnaService.update(memberId, qnaVO, deletedIds, files != null ? files : new ArrayList<>());
        return new RedirectView("/qna/detail?id=" + qnaId);
    }

    @PostMapping("/like")
    @ResponseBody
    public Map<String, Object> toggleLike(@RequestParam Long qnaId) {
        Long memberId = extractMemberId(session.getAttribute("member"));
        if (memberId == null) {
            return Map.of("success", false, "message", "로그인이 필요합니다.");
        }
        int likeCount = qnaService.toggleLike(memberId, qnaId);
        return Map.of("success", true, "likeCount", likeCount);
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam Long qnaId) {
        Long memberId = extractMemberId(session.getAttribute("member"));
        if (memberId == null) {
            return Map.of("success", false, "message", "로그인이 필요합니다.");
        }
        qnaService.delete(memberId, qnaId);
        return Map.of("success", true);
    }
}
