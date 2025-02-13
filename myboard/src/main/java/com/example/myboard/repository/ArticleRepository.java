package com.example.myboard.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.myboard.entity.Article;

import java.util.ArrayList;


public interface ArticleRepository extends CrudRepository<Article,Long>{

    @Override
    ArrayList<Article> findAll();
}

