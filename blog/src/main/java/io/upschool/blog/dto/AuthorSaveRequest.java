package io.upschool.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorSaveRequest {

    @NotBlank
    @Size(min = 2, max = 100,message = "İsim alanı minimum 2 maksimum 100 karater olabilir.")
    private String name;

    // @Builder.Default

    private String surname;

}
