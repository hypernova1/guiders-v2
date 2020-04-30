package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Comment;
import org.guiders.api.domain.Essay;
import org.guiders.api.exception.CommentNotFoundException;
import org.guiders.api.exception.PostNotFoundException;
import org.guiders.api.payload.AccountDto;
import org.guiders.api.payload.CommentDto;
import org.guiders.api.repository.CommentRepository;
import org.guiders.api.repository.EssayRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final EssayRepository essayRepository;

    public Long register(CommentDto.Request commentDto) {
        Essay essay = essayRepository.findById(commentDto.getEssayId())
                .orElseThrow(PostNotFoundException::new);

        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setEssay(essay);

        Comment savedComment = commentRepository.save(comment);

        return savedComment.getId();
    }

    @Transactional
    public void update(Long id, CommentDto.Request commentDto) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);

        comment.update(commentDto);
    }

    public CommentDto.Response get(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);

        AccountDto.InfoResponse accountDto = modelMapper.map(comment.getWriter(), AccountDto.InfoResponse.class);
        CommentDto.Response commentDto = modelMapper.map(comment, CommentDto.Response.class);
        commentDto.setWriter(accountDto);
        return commentDto;
    }
}
