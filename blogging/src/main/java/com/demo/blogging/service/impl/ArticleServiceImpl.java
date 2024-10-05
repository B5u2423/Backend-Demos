package com.demo.blogging.service.impl;

import com.demo.blogging.dto.ArticleDTO;
import com.demo.blogging.entity.Article;
import com.demo.blogging.entity.Tag;
import com.demo.blogging.repository.ArticleRepository;
import com.demo.blogging.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleServiceImpl implements com.demo.blogging.service.ArticleService {
    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    @Override
    public Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(Integer id) {
        return articleRepository.findById(id);
    }

    @Override
    public Iterable<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public boolean deleteArticleById(Integer id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Article addArticle(ArticleDTO articleDTO) {
        Article articleEntity = new Article();
        return this.createAndModifyArticle(articleEntity, articleDTO);
    }

    @Override
    public boolean updateArticleById(Integer id, ArticleDTO articleDTO) {
        Optional<Article> article = articleRepository.findById(id);

        if (article.isPresent()) {
            this.createAndModifyArticle(article.get(), articleDTO);
            return true;
        }
        return false;
    }

    private Article createAndModifyArticle(Article articleEntity, ArticleDTO articleDTO) {
        articleEntity.setTitle(articleDTO.getTitle());
        articleEntity.setAuthor(articleDTO.getAuthor());
        articleEntity.setPublishingDate(Date.from(Instant.now()));

        // Add new tags
        articleDTO.getTags().forEach(tag -> {
            if (!tagRepository.existsByTagName(tag)) {
                Tag tagEntity = new Tag();
                tagEntity.setTagName(tag);

                tagRepository.save(tagEntity);
            }
            articleEntity.getTags().add(tagRepository.findByTagName(tag));
        });

        return articleRepository.save(articleEntity);
    }
}
