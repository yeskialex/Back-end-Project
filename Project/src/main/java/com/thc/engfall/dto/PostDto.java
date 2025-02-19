package com.thc.engfall.dto;

import com.thc.engfall.entity.Post;
import lombok.*;

import java.util.List;

public class PostDto {

    @Getter @Setter @Builder
    public static class CreateReqDto {
        String title;
        String content;
        Long userId;
        List<String> imgs;

        public Post toEntity(){
            return Post.of(title, content, userId);
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long id;
    }

    @Getter @Setter @Builder
    public static class UpdateReqDto {
        Long id;

        String title;
        String content;
        Long userId;
    }

    @Getter @Setter @Builder
    public static class DetailReqDto {
        Long id;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        Long id;

        String title;
        String content;
        Long userId;

        String userUsername;
        String userName;
        String userPhone;

        //List<String> imgs;
        List<PostimgDto.DetailResDto> imgs;
    }


    @Getter @Setter @Builder
    public static class ListReqDto {
        Boolean deleted;
        String title;
        Long userId;
    }
}
