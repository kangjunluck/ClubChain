package com.blockback.init.service;


import com.blockback.init.common.request.BoardRegisterReq;
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

    String BASE_PATH = System.getProperty("user.dir") + "/Backend/init/src/main/resources/image/club/";

    @Override
    public List<Board> getBoardsByClubId(Long clubid) {
        Club club = clubRepository.findById(clubid).get();
        List<Board> boardList = boardRepository.findByClub(club);
        return boardList;
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

        if(boardThumbnail == null) return;

        try {
            Board boardTmp = boardRepository.save(board);

            String newPath = BASE_PATH + "/" + clubid + "/" + boardTmp.getId() + "-" + boardThumbnail.getOriginalFilename();
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

    private String getShortFilePath(String path) {
        int idx = path.indexOf("image");
        return path.substring(idx, path.length());
    }
}
