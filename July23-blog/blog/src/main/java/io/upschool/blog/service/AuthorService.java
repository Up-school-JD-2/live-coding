package io.upschool.blog.service;

import io.upschool.blog.dto.AuthorSaveRequest;
import io.upschool.blog.dto.AuthorSaveResponse;
import io.upschool.blog.dto.AuthorUpdateRequest;
import io.upschool.blog.entity.Author;
import io.upschool.blog.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorSaveResponse save(AuthorSaveRequest authorSaveRequest) {
//mapstruct dozer
        var newAuthor = Author
                .builder()
                .name(authorSaveRequest.getName())
                .surname(authorSaveRequest.getSurname())
                .build();
//        Author a  = new Author();
//        a.setName(authorSaveDto.getName());
//        a.setSurname(authorSaveDto.getSurname());
        Author savedAuthor = authorRepository.save(newAuthor);
        return AuthorSaveResponse
                .builder()
                .id(savedAuthor.getId())
                .nameSurname(savedAuthor.getName() + " " + savedAuthor.getSurname())
                .build();
    }

    public AuthorSaveResponse update(AuthorUpdateRequest request) {
        var optionalAuthor = authorRepository.findById(request.getId());
        if (optionalAuthor.isPresent()) {
            var author = optionalAuthor.get();
            author.setName(request.getName());
            author.setSurname(request.getSurname());
            author = authorRepository.save(author);
            return AuthorSaveResponse.builder()
                    .nameSurname(author.getName() + " " + author.getSurname())
                    .id(author.getId())
                    .build();
        }
        throw new RuntimeException("Author not found");
    }


    // select * from authors;
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        var author = authorRepository.findById(id).get();
        author.setActive(false);
        authorRepository.save(author);
        authorRepository.deleteById(id);
    }
}
