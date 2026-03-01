package com.app.trycatch.dto.experience;

import com.app.trycatch.domain.experience.FeedbackVO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class FeedbackDTO {
    private Long id;
    private String feedbackContent;

    public FeedbackVO toVO() {
        return FeedbackVO.builder()
                .id(id)
                .feedbackContent(feedbackContent)
                .build();
    }
}
