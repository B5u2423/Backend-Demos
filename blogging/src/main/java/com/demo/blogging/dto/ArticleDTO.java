package com.demo.blogging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private String title;
    private String author;
    private Date publishDate;
    private Set<String> tags;
}
