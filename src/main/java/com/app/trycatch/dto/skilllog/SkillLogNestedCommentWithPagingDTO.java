package com.app.trycatch.dto.skilllog;

import com.app.trycatch.common.pagination.Criteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SkillLogNestedCommentWithPagingDTO {
    private List<SkillLogCommentDTO> skillLogNestedComments;
    private Criteria criteria;
}
