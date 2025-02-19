package com.thc.engfall.service.impl;

import com.thc.engfall.dto.PostDto;
import com.thc.engfall.dto.PostimgDto;
import com.thc.engfall.entity.Post;
import com.thc.engfall.mapper.PostMapper;
import com.thc.engfall.repository.PostRepository;
import com.thc.engfall.service.PostService;
import com.thc.engfall.service.PostimgService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    PostMapper postMapper;
    PostimgService postimgService;
    public PostServiceImpl(
            PostRepository postRepository
            , PostMapper postMapper
            , PostimgService postimgService
    ){
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.postimgService = postimgService;
    }

    @Override
    public PostDto.CreateResDto create(PostDto.CreateReqDto params){
        PostDto.CreateResDto res = postRepository.save(params.toEntity()).toCreateResDto();
        Long postId = res.getId();
        List<String> imgs = params.getImgs();
        for(String each : imgs){
            postimgService.create(PostimgDto.CreateReqDto.builder().url(each).postId(postId).build());
        }
        return res;
    }

    @Override
    public void update(PostDto.UpdateReqDto params){
        Post post = postRepository.findById(params.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if(params.getTitle() != null){
            post.setTitle(params.getTitle());
        }
        if(params.getContent() != null){
            post.setContent(params.getContent());
        }
        postRepository.save(post);
    }

    @Override
    public void delete(PostDto.UpdateReqDto params){
        Post post = postRepository.findById(params.getId()).orElseThrow(() -> new RuntimeException("no data"));
        post.setDeleted(true);
        postRepository.save(post);
    }

    public PostDto.DetailResDto get(Long id){
        PostDto.DetailResDto returnvalue = postMapper.detail(id);
        List<PostimgDto.DetailResDto> list = postimgService.list(PostimgDto.ListReqDto.builder().postId(returnvalue.getId()).build());
        //List<String>

        returnvalue.setImgs(list);

        return returnvalue;
    }
    @Override
    public PostDto.DetailResDto detail(PostDto.DetailReqDto params){
        return get(params.getId());
    }

    @Override
    public List<PostDto.DetailResDto> list(PostDto.ListReqDto params){
        List<PostDto.DetailResDto> list = postMapper.list(params);
        List<PostDto.DetailResDto> newList = new ArrayList<>();
        for(PostDto.DetailResDto each : list){
            newList.add( get(each.getId()) );
        }
        return newList;
    }
}
