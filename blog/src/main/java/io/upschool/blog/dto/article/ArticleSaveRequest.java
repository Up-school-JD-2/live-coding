package io.upschool.blog.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleSaveRequest {
    private String title;
    private String description;
    private String content;
    private Long authorId;
}
