package com.app.trycatch.dto.experience;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.domain.experience.ExperienceProgramFileVO;
import com.app.trycatch.domain.file.FileVO;
import lombok.*;

import java.io.File;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ExperienceProgramFileDTO {
    private Long id;
    private Long experienceProgramId;
    private String filePath;
    private String fileName;
    private String fileOriginalName;
    private String fileSize;
    private FileContentType fileContentType;
    private String createdDatetime;
    private String updatedDatetime;

    public FileVO toFileVO() {
        return FileVO.builder()
                .id(id)
                .filePath(filePath)
                .fileName(fileName)
                .fileOriginalName(fileOriginalName)
                .fileSize(fileSize)
                .fileContentType(fileContentType)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }

    public ExperienceProgramFileVO toExperienceProgramFileVO() {
        return ExperienceProgramFileVO.builder()
                .id(id)
                .experienceProgramId(experienceProgramId)
                .build();
    }
}
