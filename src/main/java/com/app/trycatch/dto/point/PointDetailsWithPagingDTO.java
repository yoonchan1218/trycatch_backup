package com.app.trycatch.dto.point;

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
public class PointDetailsWithPagingDTO {
    private List<PointDetailsDTO> pointDetails;
    private Criteria criteria;
}
