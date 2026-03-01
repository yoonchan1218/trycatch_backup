package com.app.trycatch.repository.skilllog;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.common.search.Search;
import com.app.trycatch.domain.skilllog.SkillLogCommentVO;
import com.app.trycatch.domain.skilllog.SkillLogVO;
import com.app.trycatch.dto.skilllog.SkillLogAsideDTO;
import com.app.trycatch.dto.skilllog.SkillLogCommentDTO;
import com.app.trycatch.dto.skilllog.SkillLogDTO;
import com.app.trycatch.mapper.skilllog.SkillLogCommentMapper;
import com.app.trycatch.mapper.skilllog.SkillLogMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SkillLogCommentDAO {
    private final SkillLogCommentMapper skillLogCommentMapper;

//    추가
    public void save(SkillLogCommentDTO skillLogCommentDTO) {
        skillLogCommentMapper.insert(skillLogCommentDTO);
    }

//    목록
    //    댓글 목록
    public List<SkillLogCommentDTO> findAllBySkillLogId(Criteria criteria, Long id){
        return skillLogCommentMapper.selectAllBySkillLogId(criteria, id);
    }
    //    댓글 전체 개수
    public int findCountAllBySkillLogId(Long id){
        return skillLogCommentMapper.selectCountAllBySkillLogId(id);
    }
    //    답글 목록
    public List<SkillLogCommentDTO> findAllByCommentParentIdAndSkillLogId(Criteria criteria, Long skillLogId, Long commentParentId){
        return skillLogCommentMapper.selectAllByCommentParentIdAndSkillLogId(criteria, skillLogId, commentParentId);
    }
    //    답글 전체 개수
    public int findCountAllByCommentParentIdAndSkillLogId(Long skillLogId, Long commentParentId){
        return skillLogCommentMapper.selectCountAllByCommentParentIdAndSkillLogId(skillLogId, commentParentId);
    }

//    수정
    public void setSkillLogComment(SkillLogCommentVO skillLogCommentVO) {
        skillLogCommentMapper.update(skillLogCommentVO);
    }

//    삭제
    public void delete(Long id) {
        skillLogCommentMapper.delete(id);
    }
//    public void deleteAllBySkillLogId(Long id) {
//        skillLogCommentMapper.deleteAllBySkillLogId(id);
//    }
    public void deleteAllByCommentParentId(Long id) {
        skillLogCommentMapper.deleteAllByCommentParentId(id);
    }

    public void deleteNestedCommentsBySkillLogId(Long id) {
        skillLogCommentMapper.deleteNestedCommentsBySkillLogId(id);
    }
    public void deleteParentCommentsBySkillLogId(Long id) {
        skillLogCommentMapper.deleteParentCommentsBySkillLogId(id);
    }

//    댓글 작성자 member_id 조회
    public Long findMemberIdById(Long id) {
        return skillLogCommentMapper.selectMemberIdById(id);
    }
}
