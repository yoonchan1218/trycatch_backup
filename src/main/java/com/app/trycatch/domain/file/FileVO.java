package com.app.trycatch.domain.file;

import com.app.trycatch.audit.Period;
import com.app.trycatch.common.enumeration.file.FileContentType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class FileVO extends Period {
    private Long id;
    private String filePath;
    private String fileName;
    private String fileOriginalName;
    private String fileSize;
    private FileContentType fileContentType;
}
