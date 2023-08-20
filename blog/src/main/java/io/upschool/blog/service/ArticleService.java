package io.upschool.blog.service;

import io.upschool.blog.dto.article.ArticleSaveRequest;
import io.upschool.blog.dto.article.ArticleSaveResponse;
import io.upschool.blog.entity.Article;
import io.upschool.blog.entity.Author;
import io.upschool.blog.exception.ArticleAlreadySavedException;
import io.upschool.blog.repository.ArticleRepository;
import io.upschool.blog.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        checkIsArticleAlreadySaved(request);
        Article articleResponse = buildArticleAndSave(request);
        return ArticleSaveResponse.builder()
                .id(articleResponse.getId())
                .title(articleResponse.getTitle())
                .description(articleResponse.getDescription())
                .authorName(articleResponse.getAuthor().getName())
                .build();
    }

    public List<Article> getAllArticle() {
        var response = authorService.getAllAuthors();
        return articleRepository.findAll();
    }

    private Article buildArticleAndSave(ArticleSaveRequest request) {
        Author authorByReference = authorService.getReferenceById(request.getAuthorId());
        var newArticle = AuthorService.bla();
//        Article newArticle = Article.builder()
//                .title(request.getTitle())
//                .content(request.getContent())
//                .description(request.getDescription())
//                .author(authorByReference)
//                .build();
        return articleRepository.save(newArticle);
    }

    private void checkIsArticleAlreadySaved(ArticleSaveRequest request) {
        int articleCountByTitle = articleRepository.findArticleCountByTitle(request.getTitle());
        if (articleCountByTitle > 0) {
            throw new ArticleAlreadySavedException("Bu title'da daha önce article eklenmiş");
        }
    }



}
