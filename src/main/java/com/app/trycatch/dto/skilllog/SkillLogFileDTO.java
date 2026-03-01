package com.app.trycatch.dto.skilllog;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.domain.file.FileVO;
import com.app.trycatch.domain.skilllog.SkillLogFileVO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SkillLogFileDTO {
    private Long id;
    private String filePath;
    private String fileName;
    private String fileOriginalName;
    private String fileSize;
    private FileContentType fileContentType;
    private Long skillLogId;
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

    public SkillLogFileVO toSkillLogFileVO() {
        return SkillLogFileVO.builder()
                .id(id)
                .skillLogId(skillLogId)
                .build();
    }
}
