package io.upschool.blog.service;

import io.upschool.blog.dto.article.ArticleSaveRequest;
import io.upschool.blog.dto.article.ArticleSaveResponse;
import io.upschool.blog.entity.Article;
import io.upschool.blog.entity.Author;
import io.upschool.blog.repository.ArticleRepository;
import io.upschool.blog.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional()
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthorService authorService;


    public List<Article> findArticlesByTitle(String title) {
        return articleRepository.findAllByTitleIs(title);
    }


    public Article save(Article article) {
        var author = authorService.save(article.getAuthor());
        article.setAuthor(author);
        return articleRepository.save(article);
    }

    // isolation = Isolation.READ_UNCOMMITTED
    @Transactional
    public ArticleSaveResponse save(ArticleSaveRequest request) {
        Author authorByReference = authorService.getReferenceById(request.getAuthorId());

        //authorService.save(new Author()); //asım
        var newArticle = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .description(request.getDescription())
                .author(authorByReference)
                .build();
       var articleResponse = articleRepository.save(newArticle);

        return ArticleSaveResponse.builder()
                .id(articleResponse.getId())
                .title(articleResponse.getTitle())
                .description(articleResponse.getDescription())
                .authorName(articleResponse.getAuthor().getName())
                .build();
    }


    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }


}
