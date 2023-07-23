package io.upschool.blog;

import io.upschool.blog.entity.Article;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		var article = Article.builder().title("asda").content("adsa").id(1L).build();
		Article a = new Article();
		a.setTitle("asdas");
		a.setContent("12321");
		var articl2e = Article.builder().content("adsa").id(1L).build();
		SpringApplication.run(BlogApplication.class, args);
	}

}
