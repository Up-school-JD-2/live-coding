package io.upschool.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {

    private int status;

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    @Builder.Default
    private String error = "no message available.";

    private T data;

}
