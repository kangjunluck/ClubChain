package com.blockback.init.repository;

import com.blockback.init.entity.Article;
import com.blockback.init.entity.QArticle;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolver;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryImpl{

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QArticle qArticle = QArticle.article;

    public List<Article> findArticlesByClubId(Long clubid) {
//        List<Article> articles = jpaQueryFactory.select(qArticle).from(qArticle)
//                .where(qArticle.clubid.eq(clubid).fetchAll());
        return null;
    }
}
