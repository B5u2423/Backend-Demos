package com.demo.blogging.controller;

import com.demo.blogging.dto.ArticleDTO;
import com.demo.blogging.entity.Article;
import com.demo.blogging.entity.Tag;
import com.demo.blogging.repository.ArticleRepository;
import com.demo.blogging.repository.TagRepository;
import com.demo.blogging.service.impl.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addArticle(@RequestBody ArticleDTO article) {
        articleService.addArticle(article);
        return ResponseEntity.ok("Add new article successfully!");
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
    public ResponseEntity<String> deleteArticle(@PathVariable Integer id) {
        if (articleService.deleteArticleById(id)) {
            return ResponseEntity.ok("Article deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article of ID " + id + " was not found!");

    }

    @PutMapping("/articles/change/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Integer id, @RequestBody ArticleDTO newArticle) {
        if (articleService.updateArticleById(id, newArticle)) {
            return ResponseEntity.ok("Article updated successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article of ID " + id + " was not found!");
    }
}
