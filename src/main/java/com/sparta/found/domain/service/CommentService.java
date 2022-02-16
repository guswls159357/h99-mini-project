package com.sparta.found.domain.service;

import com.sparta.found.domain.entity.Comment;
import com.sparta.found.domain.entity.CommentLike;
import com.sparta.found.domain.entity.Post;
import com.sparta.found.domain.entity.User;
import com.sparta.found.domain.repository.CommentLikeRepository;
import com.sparta.found.domain.repository.CommentRepository;
import com.sparta.found.domain.repository.PostRepository;
import com.sparta.found.domain.repository.UserRepository;
import com.sparta.found.error.ErrorCode;
import com.sparta.found.error.exception.CustomAuthorizationException;
import com.sparta.found.error.exception.CustomFieldException;
import com.sparta.found.security.util.SecurityUtil;
import com.sparta.found.web.dto.comment.CommentCreateRequestDto;
import com.sparta.found.web.dto.comment.CommentIdDto;
import com.sparta.found.web.dto.comment.CommentListResponseDto;
import com.sparta.found.web.dto.comment.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentIdDto create(Integer postId, CommentCreateRequestDto dto){

        String currentLoginUserId = SecurityUtil.getCurrentLoginUserId();

        User user = userRepository.findByUsername(currentLoginUserId).get();

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new CustomFieldException("postId", "게시물이 존재하지 않습니다", ErrorCode.NOT_EXIST_ERROR));

        String commentContents = dto.getCommentContents();

        Comment comment = commentRepository.save(Comment.builder()
                .user(user)
                .post(post)
                .contents(commentContents)
                .build());

        return CommentIdDto.builder().commentId(comment.getId()).build();

    }

    @Transactional
    public void commentLike(Integer commentId){

        String currentLoginUserId = SecurityUtil.getCurrentLoginUserId();
        User user = userRepository.findByUsername(currentLoginUserId).get();

        Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentIdAndUserId(commentId, user.getId());

        if(optionalCommentLike.isPresent()){
            //삭제
            CommentLike commentLike = optionalCommentLike.get();
            user.getCommentLikeList().remove(commentLike);
            commentLikeRepository.delete(commentLike);
        }else{
            //추가
            CommentLike commentLike = CommentLike.builder()
                    .user(user)
                    .comment(commentRepository.findById(commentId).get())
                    .build();

            commentLikeRepository.save(commentLike);
        }

    }

    public CommentListResponseDto getAllByPostId(Integer postId) {

        List<Comment> commentList = commentRepository.findAllByPostIdFetchAll();

        List<CommentResponseDto> commentResponseDtos = commentList.stream().map(comment -> comment.toCommentResponseDto(
                        comment.getUser().toUserInfo(),
                        comment.getCommentLikeList().stream().map(commentLike ->
                                commentLike.getUser().toUserInfo()
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());

        return CommentListResponseDto.builder().commentList(commentResponseDtos).build();
    }

    @Transactional
    public void delete(Integer commentId){

        String currentLoginUserId = SecurityUtil.getCurrentLoginUserId();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomFieldException("commentId", "존재하지 않는 댓글입니다", ErrorCode.NOT_EXIST_ERROR));

        if(!comment.getUser().getUsername().equals(currentLoginUserId)){
            throw new CustomAuthorizationException("내가 작성한 댓글만 삭제할 수 있습니다");
        }

        commentLikeRepository.deleteAllByCommentId(commentId);

        commentRepository.delete(comment);
    }
}
