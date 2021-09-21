package com.blockback.init.service;

import com.blockback.init.common.request.VoteCreateReq;
import com.blockback.init.common.response.VoteItemList;
import com.blockback.init.common.response.VoteListRes;
import com.blockback.init.entity.Club;
import com.blockback.init.entity.User;
import com.blockback.init.entity.Vote;
import com.blockback.init.entity.VoteList;
import com.blockback.init.repository.ClubRepository;
import com.blockback.init.repository.VoteListRepository;
import com.blockback.init.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("VoteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    VoteListRepository voteListRepository;

    @Autowired
    ClubRepository clubRepository;

    @Override
    public List<VoteListRes> getVoteList(Long clubid) {

        List<Vote> vote = voteRepository.findAllById(clubid);
        List<VoteListRes> res = new ArrayList<>();

        for(Vote v : vote) {
           List<VoteList> voteList = voteListRepository.findByVoteId(v.getId());
           List<VoteItemList> vilRes = new ArrayList<>();

           for(VoteList vl : voteList) {
               VoteItemList vil = new VoteItemList();
               vil.setVotes(vl.getVotes());
               vil.setItemid(vl.getId());
               vil.setItems(vl.getItems());

               vilRes.add(vil);
           }

           VoteListRes tmp = new VoteListRes();
           tmp.setClubid(v.getClub().getId());
           tmp.setVote_list(vilRes);
           tmp.setClubname(v.getClub().getName());
           tmp.setVoteid(v.getId());
           tmp.setContent(v.getContent());
           tmp.setUsername(v.getUser().getUsernickname());

           res.add(tmp);
        }

        return res;
    }

    @Override
    public boolean createVote(Long clubid, VoteCreateReq req, User user) {
        Optional<Club> club = clubRepository.findById(clubid);
        if(!club.isPresent()) {
            return false;
        }

        Vote vote = new Vote();
        vote.setContent(req.getContent());
        vote.setClub(club.get());
        vote.setUser(user);
        vote.setEnd(req.getEnd());

        Vote voteSave = voteRepository.save(vote);
        for(String item : req.getVote_list()) {
            VoteList vl = new VoteList();
            vl.setVotes(0L);
            vl.setItems(item);
            vl.setVote(voteSave);
            voteListRepository.save(vl);
        }

        return true;
    }
}
