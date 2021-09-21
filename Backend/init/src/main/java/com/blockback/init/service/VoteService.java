package com.blockback.init.service;

import com.blockback.init.common.request.VoteCreateReq;
import com.blockback.init.common.response.VoteListRes;
import com.blockback.init.entity.User;

import java.util.List;

public interface VoteService {
    List<VoteListRes> getVoteList(Long clubid);
    boolean createVote(Long clubid, VoteCreateReq req, User user);
    boolean vote(Long clubid, Long voteid, User user, Long itemId);
}
