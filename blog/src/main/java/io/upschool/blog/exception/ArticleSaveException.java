package io.upschool.blog.exception;

public class ArticleSaveException extends RuntimeException {

    public ArticleSaveException() {

    }

    public ArticleSaveException(String message) {
        super(message);
    }


}
