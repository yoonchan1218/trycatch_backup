package com.app.trycatch.controller.skilllog;

import com.app.trycatch.common.search.Search;
import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.dto.member.MemberDTO;
import com.app.trycatch.dto.skilllog.*;
import com.app.trycatch.service.skilllog.SkillLogCommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/skill-log/comments/**")
@RequiredArgsConstructor
@Slf4j
public class SkillLogCommentAPIController {
    private final SkillLogCommentService skillLogCommentService;

//    추가
    @PostMapping("write")
    public void write(SkillLogCommentDTO skillLogCommentDTO, @RequestParam(value = "file", required = false) MultipartFile multipartFile){
        skillLogCommentService.write(skillLogCommentDTO, multipartFile);
    }

//    목록
    @GetMapping("comment-list/{page}")
    public SkillLogCommentWithPagingDTO commentList(@PathVariable int page, Long id, Long memberId) {
        return skillLogCommentService.getListInSkillLog(page, id, memberId);
    }
    @GetMapping("nested-comment-list/{page}")
    public SkillLogNestedCommentWithPagingDTO commentList(@PathVariable int page, Long skillLogId, Long commentId, Long memberId) {
        return skillLogCommentService.getListInSkillLogAndParentComment(page, skillLogId, commentId);
    }

//    수정
    @PutMapping("{id}")
    public void update(SkillLogCommentDTO skillLogCommentDTO, @RequestParam(value = "file", required = false) MultipartFile multipartFile){
        skillLogCommentService.update(skillLogCommentDTO, multipartFile);
    }

//    삭제
    @DeleteMapping("{skillLogCommentId}")
    public void delete(@PathVariable Long skillLogCommentId){
        skillLogCommentService.delete(skillLogCommentId);
    }

//    좋아요
    @GetMapping("like")
    public int like(SkillLogCommentLikesDTO skillLogCommentLikesDTO) {
        return skillLogCommentService.like(skillLogCommentLikesDTO);
    }
}
