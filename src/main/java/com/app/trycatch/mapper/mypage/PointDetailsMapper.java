package com.app.trycatch.mapper.mypage;

import com.app.trycatch.domain.mypage.PointDetailsVO;
import com.app.trycatch.dto.mypage.PointDetailsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("mypagePointDetailsMapper")
public interface PointDetailsMapper {
//    목록
    List<PointDetailsDTO> selectAllByMemberId(Long memberId);
//    추가
    void insert(PointDetailsVO pointDetailsVO);
}
