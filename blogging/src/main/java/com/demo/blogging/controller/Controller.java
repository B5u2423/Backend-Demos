package com.demo.blogging.controller;

import com.demo.blogging.dto.ArticleDTO;
import com.demo.blogging.entity.Article;
import com.demo.blogging.entity.Tag;
import com.demo.blogging.repository.ArticleRepository;
import com.demo.blogging.repository.TagRepository;
import com.demo.blogging.service.impl.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/blogging/")
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    private final ArticleServiceImpl articleService;

    @PostMapping("/articles/add")
    public String addArticle(@RequestBody ArticleDTO article) {
        articleService.addArticle(article);
        return "Add new article successfully!";
    }

    @GetMapping("/articles")
    public Iterable<Article> getArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/articles/{id}")
    public Optional<Article> getOneArticle(@PathVariable Integer id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/tags")
    public Iterable<Tag> getTags() {
        return articleService.getAllTags();
    }

    @DeleteMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Integer id) {
        if (articleService.deleteArticleById(id)) {
            return "Article deleted...";
        }
        return "No article of ID " + id + " was found";
    }

    @PutMapping("/articles/change/{id}")
    public String updateArticle(@PathVariable Integer id, @RequestBody ArticleDTO newArticle) {
        if (articleService.updateArticleById(id, newArticle)) {
            return "Article updated successfully!";
        }
        return "Article of ID " + id + " does not exist";
    }
}
