package com.blockback.init.service;


import com.blockback.init.common.request.BoardRegisterReq;
import com.blockback.init.common.response.BoardRes;
import com.blockback.init.common.response.PhotoRes;
import com.blockback.init.common.response.Photos;
import com.blockback.init.entity.*;
import com.blockback.init.repository.BoardRepository;
import com.blockback.init.repository.ClubRepository;
import com.blockback.init.repository.FileRepository;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ClubRepository clubRepository;

    @Autowired
    FileRepository fileRepository;

    String BASE_PATH = System.getProperty("user.dir") + "/src/main/resources/image/club/";

    @Override
    public List<BoardRes> getBoardsByClubId(Long clubid) {
        Optional<Club> club = clubRepository.findById(clubid);
        if(!club.isPresent()) {
            return null;
        }

        List<BoardRes> res = new ArrayList<>();
        List<Board> boards = boardRepository.findByClub(club.get());
        for(Board board : boards) {
            BoardRes br = new BoardRes();

            Optional<BoardFile> boardf = fileRepository.findByBoardId(board.getId());
            if(boardf.isPresent()) {
                br.setPhoto_thumbnail("/resources/" + boardf.get().getSave_folder());
            }

            br.setClubid(clubid);
            br.setId(board.getId());
            br.setCreated(dateFormat(board.getCreated()));
            br.setContent(board.getContent());
            br.setSection(board.getSection());
            br.setTitle(board.getTitle());

            br.setUsernickname(board.getUser().getUsernickname());
            br.setUseraccount(board.getUser().getUseraccount());
            br.setUserEmail(board.getUser().getUserEmail());
            br.setUserthumbnail("/resources/" + board.getUser().getUserthumbnail());
            br.setUserid(board.getUser().getId());
            br.setPassword(board.getUser().getPassword());

            br.setView(board.getView());
            br.setClubname(club.get().getName());
            res.add(br);
        }

        return res;
    }

    public String dateFormat(Date date) { // 날짜 형식 변환
        DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sdFormat.format(date);
    }

    @Override
    public Board getBoardByBoardId(Long boardid) {
        Optional<Board> board = boardRepository.findById(boardid);
        if (board.isPresent()) {
            return board.get();
        }
        return null;
    }

    @Override
    public void createBoard(User user, BoardRegisterReq boardinfo, Long clubid, MultipartFile boardThumbnail) {
        Club club = clubRepository.findById(clubid).get();
        Board board = new Board();
        board.setUser(user);
        board.setClub(club);
        board.setTitle(boardinfo.getTitle());
        board.setContent(boardinfo.getContent());
        board.setCreated(new Date());
        board.setUpdated(new Date());
        board.setView(0);
        board.setSection(boardinfo.getSection());

        try {
            Board boardTmp = boardRepository.save(board);

            if(boardThumbnail == null) return;

            String newPath = BASE_PATH + clubid + "/" + boardTmp.getId() + "-" + boardThumbnail.getOriginalFilename();
            File dest = new File(newPath);
            boardThumbnail.transferTo(dest);

            if(!dest.exists()) {
                System.out.println("파일 업로드 실패");
            }else {
                BoardFile boardFile = new BoardFile();
                boardFile.setOrigin_file(boardThumbnail.getOriginalFilename());
                boardFile.setSave_file(boardTmp.getId() + "-" + boardThumbnail.getOriginalFilename());
                boardFile.setSave_folder(getShortFilePath(newPath));
                boardFile.setBoard(boardTmp);

                fileRepository.save(boardFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void putBoard(BoardRegisterReq boardinfo, Long boardid) {
        Board board = boardRepository.findById(boardid).get();
        board.setTitle(boardinfo.getTitle());
        board.setContent(boardinfo.getContent());
        board.setUpdated(new Date());
        board.setSection(boardinfo.getSection());

        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long boardid) {
        Board board = boardRepository.findById(boardid).get();
        boardRepository.delete(board);
    }

    @Override
    public PhotoRes getPhotos(Long clubid) {
        PhotoRes res = new PhotoRes();
        List<Photos> photos = new ArrayList<>();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Board> boards = boardRepository.findByClubId(clubid);

        for(Board board : boards) {
            List<BoardFile> bfs = fileRepository.findByBoard(board);

            for(BoardFile bf : bfs) {
                Photos photo = new Photos();
                photo.setPhoto_address(bf.getSave_folder());
                photo.setTime(transFormat.format(board.getCreated()));
                photo.setUser_name(board.getUser().getUsernickname());
                photo.setUserthumbnail(board.getUser().getUserthumbnail());
                photos.add(photo);
            }
        }

        res.setTotal(photos.size());
        res.setList(photos);
        return res;
    }

    private String getShortFilePath(String path) {
        int idx = path.indexOf("image");
        return path.substring(idx, path.length());
    }
}
