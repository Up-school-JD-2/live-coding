package io.upschool.blog.controller;

import io.upschool.blog.dto.BaseResponse;
import io.upschool.blog.dto.article.ArticleSaveRequest;
import io.upschool.blog.dto.article.ArticleSaveResponse;
import io.upschool.blog.entity.Article;
import io.upschool.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getArticles() {
        var articles = articleService.getAllArticle();
        return ResponseEntity.ok(articles);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        return ResponseEntity.ok(articleService.save(article));
    }


    @PostMapping("/article")
    public ResponseEntity<Object> createArticle(@RequestBody ArticleSaveRequest request) {
        var articleSaveResponse = articleService.save(request);
       var response =  BaseResponse.<ArticleSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(articleSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
