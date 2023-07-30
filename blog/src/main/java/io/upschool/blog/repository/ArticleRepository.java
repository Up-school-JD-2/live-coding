package io.upschool.blog.repository;

import io.upschool.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // select * from posts p where p.title = ?
    List<Article> findAllByTitleIs(String title);

    @Query(value = "select a from Article a where a.author.id = :id")
    List<Article> findAllByTitleIs2(@Param("id") Long sayi);

    // titleAsımtitle
// querydsl
    @Query(value = "select * from posts p inner join " +
            "authors a on p.id = a.post_id  ",
            nativeQuery = true)
    List<Article> findAllByTitleIs();


    List<Article> findByTitleIs(String title);

    @Query(value = "select count(a) from Article a " +
            "where a.title = :title")
    int findArticleCountByTitle(@Param("title") String title);


}
