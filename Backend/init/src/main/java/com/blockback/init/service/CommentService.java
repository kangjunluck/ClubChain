package com.blockback.init.service;

import com.blockback.init.common.request.CommentCreateReq;
import com.blockback.init.entity.Board;
import com.blockback.init.entity.Comment;
import com.blockback.init.entity.User;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsList(Long boardid);

    void createComment(CommentCreateReq commentinfo, User user, Board board);

    Comment getCommentByCommentId(Long commentid);

    void putComment(CommentCreateReq commentinfo, Comment comment);
}
