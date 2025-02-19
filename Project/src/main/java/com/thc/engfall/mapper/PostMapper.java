package com.thc.engfall.mapper;

import com.thc.engfall.dto.PostDto;

import java.util.List;

public interface PostMapper {
	PostDto.DetailResDto detail(Long id);
	List<PostDto.DetailResDto> list(PostDto.ListReqDto params);
}