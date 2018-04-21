package com.mmm.service.impl;

import com.mmm.entity.Article;
import com.mmm.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //测试创建索引
    @Test
    public void CreateIndex() throws Exception {
        elasticsearchTemplate.createIndex(Article.class);
        elasticsearchTemplate.putMapping(Article.class);
    }



    //测试保存
    @Test
    public void save() throws Exception {

        Article article = new Article();
        article.setId(1);
        article.setTitle("中国");
        article.setContent("中国是世界四大文明古国之一，有着悠久的历史，距今约5000年前，" +
                "以中原地区为中心开始出现聚落组织进而形成国家，后历经多次民族交融和朝代更迭，直至形成多民族国家的大一统局面。" +
                "20世纪初辛亥革命后，君主政体退出历史舞台，共和政体建立。1949年中华人民共和国成立后，在中国大陆建立了人民代表大会制度的政体。");

        articleService.save(article);
    }


    @Test
    public void delete() throws Exception {
        Article article = new Article();

        //articleService.delete(article);
        for (int i = 0; i <= 100; i++) {
             article.setId(i);
             articleService.delete(article);
        }
    }

    @Test
    public void findOne() throws Exception {
        Article article = articleService.findOne(1);
        System.out.println(article);
    }

    @Test
    public void testSaveBatch() {
        for (int i = 1; i <= 100; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle(i + "Spring Data Elasticsearch 1.3.1 昨天发布");
            article.setContent(i
                    + "DATAES-171 - 添加失效查询关键字支持 DATAES-194 - 测试可以清理  data 目录 DATAES-179 - 支持  Attachment 字段类型 DATAES-94 - 更新到最新版本的 elasticsearch 1.7.3 驱动器");

            articleService.save(article);
        }
    }

   	@Test
	// 排序分页查询
	public void testSortAndPaging() {
		Iterable<Article> articles = articleService.findAll();
		for (Article article : articles) {
			System.out.println(article);
		}

		Pageable pageable = new PageRequest(0, 10);
		Page<Article> pageData = articleService.findAll(pageable);
		for (Article article : pageData.getContent()) {
			System.out.println(article);
		}
	}

	@Test
	// 条件查询
	public void testConditionQuery() {
		// 查询 标题中含有 “昨天”
		// List<Article> articles = articleService.findByTitle("昨天");
		// for (Article article : articles) {
		// System.out.println(article);
		// }

		// 查询 标题中含有 “昨天” 1-10条
		Pageable pageable = new PageRequest(0, 10);
		Page<Article> pageData = articleService.findByTitle("昨天", pageable);
		System.out.println("总记录数：" + pageData.getTotalElements());
		for (Article article : pageData.getContent()) {
			System.out.println(article);
		}
	}
}