package com.app.trycatch.dto.qna;

import com.app.trycatch.common.pagination.Criteria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QnaWithPagingDTO {
    private List<QnaDTO> qnas;
    private Criteria criteria;
    private int total;
}
