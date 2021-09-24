package com.blockback.init.service;

import com.blockback.init.common.request.CommentCreateReq;
import com.blockback.init.entity.Board;
import com.blockback.init.entity.Comment;
import com.blockback.init.entity.User;
import com.blockback.init.repository.BoardRepository;
import com.blockback.init.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentRepository commentRepository;


    @Override
    public List<Comment> getCommentsList(Long boardid) {
        Board board = boardRepository.findById(boardid).get();
        List<Comment> commentList = commentRepository.findByBoard(board);
        return commentList;
    }

    @Override
    public void createComment(CommentCreateReq commentinfo, User user, Board board) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setBoard(board);
        comment.setContent(commentinfo.getContent());
        comment.setCreated(new Date());

        commentRepository.save(comment);
    }

    @Override
    public Comment getCommentByCommentId(Long commentid) {
        Optional<Comment> comment = commentRepository.findById(commentid);
        if (comment.isPresent()) {
            return comment.get();
        }
        return null;
    }

    @Override
    public void putComment(CommentCreateReq commentinfo, Comment comment) {
        comment.setContent(commentinfo.getContent());
        commentRepository.save(comment);
    }


}
