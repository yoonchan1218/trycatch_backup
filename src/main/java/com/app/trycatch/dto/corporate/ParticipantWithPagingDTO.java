package com.app.trycatch.dto.corporate;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.dto.experience.ChallengerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ParticipantWithPagingDTO {
    private List<ChallengerDTO> list;
    private Criteria criteria;
    private boolean hasMore;
    private Map<String, Long> statusCounts;
}
