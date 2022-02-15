package com.sparta.found.domain.service;

import com.sparta.found.domain.entity.Post;
import com.sparta.found.domain.entity.PostTag;
import com.sparta.found.domain.entity.Tag;
import com.sparta.found.domain.entity.User;
import com.sparta.found.domain.repository.PostRepository;
import com.sparta.found.domain.repository.PostTagRepository;
import com.sparta.found.domain.repository.TagRepository;
import com.sparta.found.domain.repository.UserRepository;
import com.sparta.found.error.ErrorCode;
import com.sparta.found.error.exception.CustomAuthorizationException;
import com.sparta.found.error.exception.CustomFieldException;
import com.sparta.found.security.util.SecurityUtil;
import com.sparta.found.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    @Transactional
    public Integer create(PostCreateRequestDto dto) {


        User user = userRepository.findByUsername(SecurityUtil.getCurrentLoginUserId()).get();

        Post post = dto.toEntity(user);
        postRepository.save(post);

        List<String> existTagByContents = tagRepository.findAllContentsByContentsList(dto.getPostTag());
        List<Tag> tagList = new ArrayList<>();
        List<PostTag> postTagList = new ArrayList<>();

        dto.getPostTag().stream().forEach(contents->{
            if(!existTagByContents.contains(contents)){
                Tag tag = Tag.builder().contents(contents).build();
                tagList.add(tag);
                PostTag postTag = PostTag.builder()
                        .tag(tag)
                        .post(post)
                        .build();
                postTagList.add(postTag);
            }else{
                Tag tag = tagRepository.findByContents(contents).get();
                PostTag postTag = PostTag.builder()
                        .tag(tag)
                        .post(post)
                        .build();
                postTagList.add(postTag);
            }

        });

        tagRepository.saveAll(tagList);
        postTagRepository.saveAll(postTagList);

        return post.getId();
    }

    public PostDto getOne(Integer postId) {

        Post post = postRepository.findByIdFetchAll(postId).orElseThrow(() ->
                new CustomFieldException("postId", "존재하지 않는 게시물입니다", ErrorCode.NOT_EXIST_ERROR));


        PostDto postDto = post.toPostDto(post.getUser().toUserInfo(),
                post.getPostTagList().stream().map(postTag ->
                        postTag.getTag().getContents()
                ).collect(Collectors.toList()));
        return postDto;
    }

    public PostListResponseDto getAll() {


        List<PostDto> postDtos = postRepository.findAllFetchUser().stream().map(post ->
                        post.toPostDto(post.getUser().toUserInfo(),
                                postTagRepository.findByPostId(post.getId()).stream().map(postTag ->
                                                postTag.getTag().getContents())
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());


        return PostListResponseDto.builder().postList(postDtos).build();
    }

    @Transactional
    public void update(PostUpdateRequestDto dto, Integer postId) {

        Post post = postRepository.findByIdFetchAll(postId).orElseThrow(() ->
                new CustomFieldException("postId", "존재하지 않는 게시물입니다",ErrorCode.NOT_EXIST_ERROR));

        String currentLoginUserId = SecurityUtil.getCurrentLoginUserId();
        if(!post.getUser().getUsername().equals(currentLoginUserId)){
            throw new CustomAuthorizationException("내가 작성한 글만 수정할 수 있습니다");
        }

        post.update(dto);

        postTagRepository.deleteAllByPostId(postId);
        //넣어야할 태그 내용들
        List<String> tagContents = dto.getPostTag();
        //넣어야할 태그 내용들 중 이미 존재하는 태그들
        List<String> existTagContents = tagRepository.findAllContentsByContentsList(tagContents);
        //넣어야할 태그 내용들 중 새로 생성해야할 태그들
        //tagContents.stream().filter()

        List<Tag> newTagList = new ArrayList<>();
        List<PostTag> postTagList = new ArrayList<>();

//        tagContents.stream().forEach(contents->{
//            if(existTagContents.contains())
//        });

    }
}
