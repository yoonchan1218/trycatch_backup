package com.app.trycatch.mapper.skilllog;

import com.app.trycatch.domain.skilllog.TagVO;
import com.app.trycatch.dto.skilllog.TagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TagMapper {
//    추가
    public void insert(TagVO tagVO);

//    목록
    public List<TagVO> selectAllBySkillLogId(Long id);
//    전체 태그 (중복X)
    public List<String> selectAll();

//    삭제
    public void delete(Long id);
}
