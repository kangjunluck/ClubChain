package com.blockback.init.service;

import com.blockback.init.entity.Article;
import com.blockback.init.repository.ArticleRepository;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getArticlesByClubId(Long clubid) {
        articleRepository.findArticlesByClubId(clubid);
        return null;
    }
}
