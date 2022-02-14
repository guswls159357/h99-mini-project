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
    public void create(User sessionUser, PostCreateRequestDto dto) {

        User user = userRepository.findById(sessionUser.getId()).get();

        Post post = dto.toEntity(user);
        postRepository.save(post);

        List<String> existTagByContents = tagRepository.findAllByContents(dto.getPostTag());
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
            }
        });

        tagRepository.saveAll(tagList);
        postTagRepository.saveAll(postTagList);
    }

    public PostDto getOne(Integer postId) {


        return null;
    }

    public PostListResponseDto getAll() {




        return null;
    }

    public void update(PostUpdateRequestDto dto, User user, Integer postId) {



    }
}
