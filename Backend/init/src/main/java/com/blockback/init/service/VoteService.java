package com.blockback.init.service;

import com.blockback.init.common.response.VoteListRes;

import java.util.List;

public interface VoteService {
    List<VoteListRes> getVoteList(Long clubid);
}
