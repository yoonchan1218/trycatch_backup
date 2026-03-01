package com.app.trycatch.mapper.experience;

import com.app.trycatch.dto.experience.FeedbackDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedbackMapper {
    void insert(FeedbackDTO feedbackDTO);
}
