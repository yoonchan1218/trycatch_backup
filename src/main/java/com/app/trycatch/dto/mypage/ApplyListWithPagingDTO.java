package com.app.trycatch.dto.mypage;

import com.app.trycatch.common.pagination.Criteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
@NoArgsConstructor
public class ApplyListWithPagingDTO {
    private List<ApplyListDTO> applies;
    private Criteria criteria;
    private long appliedCount;
    private long documentPassCount;
    private long documentFailCount;
    private long activityDoneCount;
}
