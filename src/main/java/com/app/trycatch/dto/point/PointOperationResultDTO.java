package com.app.trycatch.dto.point;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PointOperationResultDTO {
    private boolean success;
    private String message;
    private Integer currentPoint;
}
