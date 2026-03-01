package com.app.trycatch.dto.qna;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class CorpNameKeywordDTO {
    private Long id;
    private Long qnaId;
    private Long corpId;
    private String corpName;
}
