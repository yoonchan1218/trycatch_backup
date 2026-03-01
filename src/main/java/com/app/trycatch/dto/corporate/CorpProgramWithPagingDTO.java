package com.app.trycatch.dto.corporate;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.dto.experience.ExperienceProgramDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CorpProgramWithPagingDTO {
    private List<ExperienceProgramDTO> list;
    private Criteria criteria;
    private boolean hasMore;
}
