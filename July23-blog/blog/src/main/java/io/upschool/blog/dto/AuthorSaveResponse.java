package io.upschool.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorSaveResponse {

    private Long id;
    private String nameSurname;
}

// request -response
// dto
// data