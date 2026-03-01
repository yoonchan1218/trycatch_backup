package com.app.trycatch.dto.file;

import com.app.trycatch.common.enumeration.file.FileContentType;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class FileDTO {
    private Long id;
    private String filePath;
    private String fileName;
    private String fileOriginalName;
    private String fileSize;
    private FileContentType fileContentType;
    private String createdDatetime;
    private String updatedDatetime;
}
