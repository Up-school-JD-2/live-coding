package io.upschool.blog.service;

import io.upschool.blog.entity.Article;
import io.upschool.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;


    public List<Article> findArticlesByTitle(String title) {
        return articleRepository.findAllByTitleIs(title);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

}
