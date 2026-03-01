package com.app.trycatch.dto.skilllog;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.domain.file.FileVO;
import com.app.trycatch.domain.skilllog.SkillLogCommentFileVO;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SkillLogCommentFileDTO {
    private Long id;
    private Long skillLogCommentId;

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

    public SkillLogCommentFileVO toSkillLogCommentFileVO() {
        return SkillLogCommentFileVO.builder()
                .id(id)
                .skillLogCommentId(skillLogCommentId)
                .build();
    }
}
