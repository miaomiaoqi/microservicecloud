package com.miaoqi.elastic;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.miaoqi.elastic.bean.Article;
import com.miaoqi.elastic.bean.Book;
import com.miaoqi.elastic.repository.BookRepository;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot09ElasticApplicationTests {

    @Autowired
    private JestClient jestClient;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void test02() {
        //        Book book = new Book();
        //        book.setId(1);
        //        book.setBookName("西游记");
        //        book.setAuthor("吴承恩");
        //        bookRepository.index(book);

        List<Book> list = bookRepository.findByBookNameLike("游记");
        System.out.println(list);
    }

    @Test
    public void contextLoads() {
        // 1. 给es中索引一个文档
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setAuthor("张三");
        article.setContent("Hello World");

        // 构建一个索引功能
        Index index = new Index.Builder(article).index("miaoqi").type("news").build();

        try {
            // 执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试搜搜
    @Test
    public void search() {
        String json = "{\n" + "    \"query\" : {\n" + "        \"match\" : {\n"
                + "            \"content\" : \"Hello\"\n" + "        }\n" + "    }\n" + "}";
        // 构建搜索功能
        Search search = new Search.Builder(json).addIndex("miaoqi").addType("news").build();

        try {
            SearchResult searchResult = jestClient.execute(search);
            System.out.println(searchResult.getJsonString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
