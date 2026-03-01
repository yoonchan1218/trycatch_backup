package com.app.trycatch.dto.corporate;

import com.app.trycatch.common.pagination.Criteria;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CorpTeamMemberWithPagingDTO {
    private List<CorpTeamMemberDTO> list;
    private Criteria criteria;
    private boolean hasMore;
}
