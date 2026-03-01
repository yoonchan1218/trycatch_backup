package com.app.trycatch.mapper.mypage;

import com.app.trycatch.domain.mypage.LatestWatchPostingVO;
import com.app.trycatch.dto.mypage.LatestWatchPostingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LatestWatchPostingMapper {
//    목록
    List<LatestWatchPostingDTO> selectAllByMemberId(Long memberId);
//    추가 (중복 시 시간 갱신)
    void insert(LatestWatchPostingVO latestWatchPostingVO);
}
