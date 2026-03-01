package com.app.trycatch.controller.qna;

import com.app.trycatch.service.qna.QnaLikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qna/likes")
@RequiredArgsConstructor
@Slf4j
public class QnaLikesController {
    private final QnaLikesService qnaLikesService;

    @PostMapping("/toggle")
    public void toggle(Long memberId, Long qnaId) {
        qnaLikesService.toggleLike(memberId, qnaId);
    }
}
