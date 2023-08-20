package io.upschool.blog.service;

import io.upschool.blog.dto.article.ArticleSaveRequest;
import io.upschool.blog.entity.Article;
import io.upschool.blog.entity.Author;
import io.upschool.blog.exception.ArticleAlreadySavedException;
import io.upschool.blog.repository.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private AuthorService authorService;

    @Captor
    private ArgumentCaptor<Article> articleArgumentCaptor;


    @Test
    @DisplayName(value = "Article aynı title ile birden fazla kez eklenemez")
    public void shouldThrowArticleAlreadySavedException_whenArticleAlreadySavedWithTitle() {
        //given
        var request = ArticleSaveRequest.builder().title("title-1")
                .description("description-1")
                .content("content")
                .authorId(1L)
                .build();
        var message = "Bu title'da daha önce article eklenmiş";
        when(articleRepository.findArticleCountByTitle(request.getTitle())).thenReturn(1);
//       Executable executable = () -> articleService.save(request);
//        Assertions.assertThrows(ArticleAlreadySavedException.class,executable);

        //when
        var ex = Assertions.assertThrows(ArticleAlreadySavedException.class, () -> articleService.save(request));

        //then
        Assertions.assertEquals(message, ex.getMessage());
    }

    @Test
    //@DisplayName(value = "Article aynı title ile birden fazla kez eklenemez")
    public void shouldThrowArticleAlreadySavedException_whenArticleAlreadySavedWithTitle2() {
        //given
        var request = ArticleSaveRequest.builder().title("title-1")
                .description("description-1")
                .content("content")
                .authorId(1L)
                .build();
        var author = Author.builder().id(request.getAuthorId()).name("Ahmet").build();
        Article newArticle = Article.builder()
                .id(1L)
                .title(request.getTitle())
                .content(request.getContent())
                .description(request.getDescription())
                .author(author)
                .build();
        when(articleRepository.findArticleCountByTitle(request.getTitle())).thenReturn(0);
        when(authorService.getReferenceById(request.getAuthorId())).thenReturn(author);
        when(articleRepository.save(any(Article.class))).thenReturn(newArticle);

        //when
       var response = articleService.save(request);
 // .com/users/20382/article
        //then
        verify(articleRepository, times(1)).findArticleCountByTitle(request.getTitle());
        verify(authorService,times(1)).getReferenceById(request.getAuthorId());
        verify(articleRepository,times(1)).save(newArticle);

        Assertions.assertEquals("Ahmet",response.getAuthorName());
    }
}