package com.blockback.init.service;

import com.blockback.init.common.request.VoteCreateReq;
import com.blockback.init.common.response.VoteItemList;
import com.blockback.init.common.response.VoteListRes;
import com.blockback.init.entity.*;
import com.blockback.init.repository.ClubRepository;
import com.blockback.init.repository.VoteListRepository;
import com.blockback.init.repository.VoteRepository;
import com.blockback.init.repository.VoteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    VoteUserRepository voteUserRepository;

    @Override
    public List<VoteListRes> getVoteList(Long clubid) {

        List<Vote> vote = voteRepository.findByClubId(clubid);
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
        vote.setEnd(stringFormatToDate(req.getEnd()));

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

    private Date stringFormatToDate(String date) {
        Date res = new Date();
        try {
            res = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public boolean vote(Long voteid, User user, Long itemId) {
        VoteUser vu = new VoteUser();

        Optional<Vote> vote = voteRepository.findById(voteid);
        if(!vote.isPresent()) {
            return false;
        }

        Optional<VoteList> vl = voteListRepository.findById(itemId);
        if(!vl.isPresent()) {
            return false;
        }

        vu.setVote(vote.get());
        vu.setUser(user);
        vu.setVoteList(vl.get());
        voteUserRepository.save(vu);

        VoteList nvl = vl.get();
        Long newVotes = nvl.getVotes() + 1;
        nvl.setVotes(newVotes);
        voteListRepository.save(nvl);

        return true;
    }

    @Override
    public boolean revote(Long voteid, User user, Long itemId) {
        Optional<Vote> vote = voteRepository.findById(voteid);
        if(!vote.isPresent()) {
            return false;
        }

        Optional<VoteList> nvl = voteListRepository.findById(itemId);
        if(!nvl.isPresent()) {
            return false;
        }

        // 원래 투표한거
        Optional<VoteUser> vu = voteUserRepository.findByVoteIdAndUserId(vote.get().getId(), user.getId());
        if(!vu.isPresent()) {
            return false;
        }

        // 원래 항목 투표수 줄이기기
        Optional<VoteList> vl = voteListRepository.findById(vu.get().getVoteList().getId());
        if(!vl.isPresent()) {
            return false;
        }

        VoteList ovl = vl.get();
        Long newVotes = ovl.getVotes() - 1;
        ovl.setVotes(newVotes);
        voteListRepository.save(ovl);

        // 새로 투표한거 바꾸기
        VoteUser nvu = vu.get();
        nvu.setVoteList(nvl.get());
        voteUserRepository.save(nvu);

        VoteList newvl = nvl.get();
        newVotes = newvl.getVotes() + 1;
        newvl.setVotes(newVotes);
        voteListRepository.save(newvl);

        return true;
    }

    @Override
    public boolean isOwner(User user, Long voteid) {
        Optional<Vote> vote = voteRepository.findById(voteid);
        if(!vote.isPresent()) {
            return false;
        }

        if(vote.get().getUser().getId() == user.getId()) {
            return true;
        }
        return false;
    }

    @Override
    public void voteDelete(Long voteid) {
        voteUserRepository.deleteAllByVoteId(voteid);
        voteListRepository.deleteAllByVoteId(voteid);
        voteRepository.deleteById(voteid);
    }
}
