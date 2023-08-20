package io.upschool.blog.service;

import io.upschool.blog.entity.Author;
import io.upschool.blog.exception.AuthorNotFoundException;
import io.upschool.blog.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Test
    public void blabla() {
        // shouldThrowRuntimeException_whenCustomerDoesntExist(){
        // saveCustomer_throwRuntimeException_when_customerNotExist

    }

    //
//    public List<Author> getAllAuthors() {
    //throw new RuntimeException();
//        return authorRepository.findAll();
//    }
    @Test
    public void shouldReturnAuthors_whenFindAllCalled() {
        //given
        var author = Author.builder().id(1L).name("Asım").build();
        when(authorRepository.findAll()).thenReturn(List.of(author));

        //when
        var response = authorService.getAllAuthors();

        //then
        verify(authorRepository, times(2)).findAll();
        Assertions.assertNotNull(response);
    }

    @Test
    public void shouldThrowRuntimeException_whenAnyAuthorExist(){
       //given
        String message = "Kayıt bulunamadı";
        when(authorRepository.findAll()).thenReturn(new ArrayList<>());

        var ex = Assertions.assertThrows(AuthorNotFoundException.class, () -> authorService.getAllAuthors());
        Assertions.assertEquals(message,ex.getMessage());

    }
}