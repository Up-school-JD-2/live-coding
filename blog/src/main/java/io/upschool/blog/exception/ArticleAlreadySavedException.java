package io.upschool.blog.exception;

public class ArticleAlreadySavedException extends RuntimeException {

    public ArticleAlreadySavedException(String message) {
        super(message);
    }

    public ArticleAlreadySavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
