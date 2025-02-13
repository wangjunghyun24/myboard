package com.example.myboard.dto;

import com.example.myboard.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Setter
/*エンティティの一部のデータをクライアントに配信するためのdtoです*/

public class ArticleForm
{   private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id,title,content);
    }
}
