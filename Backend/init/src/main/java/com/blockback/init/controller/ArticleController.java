package com.blockback.init.controller;

import com.blockback.init.entity.Article;
import com.blockback.init.service.ArticleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "게시판 API", tags = {"User"})
@RestController
@RequestMapping("/api/{clubid}/board")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    @ApiOperation(value = "게시글 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<Article> index(@PathVariable("clubid") Long clubid)
    {
        List<Article> articleList = articleService.getArticlesByClubId(clubid);
        return articleList;
    }
}
