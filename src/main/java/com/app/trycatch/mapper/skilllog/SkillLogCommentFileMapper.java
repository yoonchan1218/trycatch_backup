package com.app.trycatch.mapper.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogCommentFileVO;
import com.app.trycatch.domain.skilllog.SkillLogFileVO;
import com.app.trycatch.dto.file.FileDTO;
import com.app.trycatch.dto.skilllog.SkillLogCommentDTO;
import com.app.trycatch.dto.skilllog.SkillLogCommentFileDTO;
import com.app.trycatch.dto.skilllog.SkillLogFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkillLogCommentFileMapper {
//    추가
    public void insert(SkillLogCommentFileVO skillLogCommentFileVO);

//    목록
    public List<FileDTO> selectFilesBySkillLogId(Long id);
    public List<FileDTO> selectFilesByCommentParentId(Long id);

//    id 찾기
    public Long selectFileIdBySkillLogCommentId(Long id);

//    삭제
    public void delete(Long id);
    public void deleteAllBySkillLogId(Long id);
    public void deleteAllBySkillLogCommentParentId(Long id);
}
