package com.example.myboard.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.myboard.entity.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

//データベースと直接コミュニケーションする階層
public interface ArticleRepository extends CrudRepository<Article,Long>{

    @Override
    ArrayList<Article> findAll();
    Page<Article> findAll(Pageable pageable);
}

