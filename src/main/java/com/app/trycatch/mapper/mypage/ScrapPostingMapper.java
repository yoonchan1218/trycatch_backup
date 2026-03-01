package com.app.trycatch.mapper.mypage;

import com.app.trycatch.domain.mypage.ScrapPostingVO;
import com.app.trycatch.dto.mypage.ScrapPostingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScrapPostingMapper {
//    목록
    List<ScrapPostingDTO> selectAllByMemberId(Long memberId);
//    추가
    void insert(ScrapPostingVO scrapPostingVO);
//    상태 변경 (스크랩 활성/비활성)
    void updateStatus(ScrapPostingVO scrapPostingVO);
}
