package io.upschool.blog.repository;

import io.upschool.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // select * from posts p where p.title = ?
    List<Article> findAllByTitleIs(String title);
}
