package com.mmm.dao;

import com.mmm.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 描述:
 * 文章dao
 *
 * @outhor ming
 * @create 2018-04-21 19:06
 */
public interface ArticleRepository
        extends ElasticsearchRepository<Article,Integer> {
    List<Article> findByTitle(String title);

    Page<Article> findByTitle(String title, Pageable pageable);
}