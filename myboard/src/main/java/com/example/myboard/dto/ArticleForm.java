package com.example.myboard.dto;

import com.example.myboard.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ArticleForm
{   private Long id;
    private String title;
    private String content;

    /*public ArticleForm(long id String title, String content){

        this.title=title;
        this.content=content;
    }*/

    /*@Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/

    public Article toEntity() {
        return new Article(id,title,content);
    }
}
