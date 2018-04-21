package com.mmm.service;

import com.mmm.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 描述:
 * 文章service
 *
 * @outhor ming
 * @create 2018-04-21 19:06
 */

public interface ArticleService {

    void save(Article article);

    void delete(Article article);

    Article findOne(Integer id);

    Iterable<Article> findAll();

    Page<Article> findAll(Pageable pageable);

    List<Article> findByTitle(String title);

    Page<Article> findByTitle(String title, Pageable pageable);
}
