package com.app.trycatch.repository.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogCommentFileVO;
import com.app.trycatch.dto.file.FileDTO;
import com.app.trycatch.dto.skilllog.SkillLogCommentDTO;
import com.app.trycatch.dto.skilllog.SkillLogCommentFileDTO;
import com.app.trycatch.mapper.skilllog.SkillLogCommentFileMapper;
import com.app.trycatch.mapper.skilllog.SkillLogCommentMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SkillLogCommentFileDAO {
    private final SkillLogCommentFileMapper skillLogCommentFileMapper;

//    추가
    public void save(SkillLogCommentFileVO skillLogCommentFileVO) {
        skillLogCommentFileMapper.insert(skillLogCommentFileVO);
    }

//    목록
    public List<FileDTO> findFilesBySkillLogId(Long id) {
        return skillLogCommentFileMapper.selectFilesBySkillLogId(id);
    }
    public List<FileDTO> findFilesByCommentParentId(Long id) {
        return skillLogCommentFileMapper.selectFilesByCommentParentId(id);
    }

//    id 찾기
    public Long findFileIdBySkillLogCommentId(Long id){
        return skillLogCommentFileMapper.selectFileIdBySkillLogCommentId(id);
    }

//    삭제
    public void delete(Long id) {
        skillLogCommentFileMapper.delete(id);
    }
    public void deleteAllBySkillLogId(Long id) {
        skillLogCommentFileMapper.deleteAllBySkillLogId(id);
    }
    public void deleteAllBySkillLogCommentParentId(Long id) {
        skillLogCommentFileMapper.deleteAllBySkillLogCommentParentId(id);
    }

}
