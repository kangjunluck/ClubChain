package com.blockback.init.service;

import com.blockback.init.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getArticlesByClubId(Long clubid);
}
