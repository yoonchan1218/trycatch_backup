package com.app.trycatch.repository.experience;

import com.app.trycatch.dto.experience.FeedbackDTO;
import com.app.trycatch.mapper.experience.FeedbackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FeedbackDAO {
    private final FeedbackMapper feedbackMapper;

//    피드백 저장
    public void save(FeedbackDTO feedbackDTO) {
        feedbackMapper.insert(feedbackDTO);
    }
}
