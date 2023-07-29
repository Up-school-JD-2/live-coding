package io.upschool.blog.controller;

import io.upschool.blog.dto.AuthorSaveRequest;
import io.upschool.blog.dto.AuthorSaveResponse;
import io.upschool.blog.dto.AuthorUpdateRequest;
import io.upschool.blog.entity.Article;
import io.upschool.blog.entity.Author;
import io.upschool.blog.service.ArticleService;
import io.upschool.blog.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {



    private final AuthorService authorService;



    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthors() {
        var authors = authorService.getAllAuthors();
        HttpHeaders headers = new HttpHeaders();
        headers.add("HEADER-ASIM", "Header value asim");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(authors);
    }

    @PostMapping
    public ResponseEntity<AuthorSaveResponse> createAuthor(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
//        if(author.getId() == null ){
//            return (ResponseEntity<Author>) ResponseEntity.badRequest();
//        }
        var response = authorService.save(authorSaveRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<AuthorSaveResponse> updateAuthor(@RequestBody AuthorUpdateRequest request) {
        return ResponseEntity.ok(authorService.update(request));
    }

    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
    }

}
