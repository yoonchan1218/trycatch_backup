package com.app.trycatch.dto.skilllog;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.dto.experience.ExperienceProgramDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ExperienceProgramWithPagingDTO {
    private List<ExperienceProgramDTO> experienceProgramLogs;
    private Criteria criteria;
}
