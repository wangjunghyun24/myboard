package com.example.myboard.dto;

import com.example.myboard.entity.Article;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
/*エンティティの一部のデータをクライアントに配信するためのdtoです*/

public class ArticleForm
{   private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id,title,content);
    }
}
