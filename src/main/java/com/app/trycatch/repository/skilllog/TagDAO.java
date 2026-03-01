package com.app.trycatch.repository.skilllog;

import com.app.trycatch.domain.skilllog.TagVO;
import com.app.trycatch.dto.skilllog.TagDTO;
import com.app.trycatch.mapper.skilllog.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagDAO {
    private final TagMapper tagMapper;

//    추가
    public void save(TagVO tagVO) {
        tagMapper.insert(tagVO);
    }

//    목록
    public List<TagVO> findAllBySkillLogId(Long id) {
        return tagMapper.selectAllBySkillLogId(id);
    }
//    전체 태그 (중복X)
    public List<String> findAll() {
        return tagMapper.selectAll();
    }

//    삭제
    public void delete(Long id) {
        tagMapper.delete(id);
    }
}
