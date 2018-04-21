package com.mmm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 描述:
 * 文章实体类
 *
 * @outhor ming
 * @create 2018-04-21 19:06
 */
@Document(indexName = "blog", type = "article")
@Data
public class Article {

    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private Integer id;

    @Field(index = FieldIndex.analyzed, analyzer = "ik", store = true, searchAnalyzer = "ik",type =FieldType.String)
    private String title;

    @Field(index = FieldIndex.analyzed, analyzer = "ik", store = true, searchAnalyzer = "ik",type = FieldType.String)
    private String content;


}
