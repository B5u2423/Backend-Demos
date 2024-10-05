package com.demo.blogging.service;

import com.demo.blogging.dto.ArticleDTO;
import com.demo.blogging.entity.Article;
import com.demo.blogging.entity.Tag;

import java.util.Optional;

public interface ArticleService {
    /**
     * Retrieves all articles from the database.
     *
     * @return an iterable collection of all articles.
     */
    Iterable<Article> getAllArticles();

    /**
     * Fetches a specific article by its unique identifier.
     *
     * @param id the unique identifier of the article to retrieve.
     * @return an Optional containing the found article, or empty if no article exists with the given id.
     */
    Optional<Article> getArticleById(Integer id);

    /**
     * Retrieves all tags.
     *
     * @return an iterable collection of all tags.
     */
    Iterable<Tag> getAllTags();

    /**
     * Deletes an article by its unique identifier.
     *
     * @param id the unique identifier of the article to delete.
     * @return true if the article was successfully deleted, false if no article with the given id exists.
     */
    boolean deleteArticleById(Integer id);

    /**
     * Adds a new article based on the provided ArticleDTO.
     *
     * @param articleDTO the data transfer object containing information about the article in Request Body.
     * @return the newly created Article object.
     */
    Article addArticle(ArticleDTO articleDTO);

    /**
     * Updates an existing article identified by its unique identifier.
     *
     * @param id the unique identifier of the article to update.
     * @param articleDTO the data transfer object containing updated information for the article.
     * @return true if the article was successfully updated, false if no article with the given id exists.
     */
    boolean updateArticleById(Integer id, ArticleDTO articleDTO);
}
