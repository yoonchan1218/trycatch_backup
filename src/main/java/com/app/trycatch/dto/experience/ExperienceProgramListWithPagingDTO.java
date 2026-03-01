package com.app.trycatch.dto.experience;

import com.app.trycatch.common.pagination.Criteria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExperienceProgramListWithPagingDTO {
    private List<ExperienceProgramDTO> programs;
    private Criteria criteria;
    private int total;
}
