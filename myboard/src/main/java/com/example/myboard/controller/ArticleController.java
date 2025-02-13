package com.example.myboard.controller;

import com.example.myboard.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import com.example.myboard.dto.ArticleForm;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.myboard.entity.Article;
import com.example.myboard.repository.ArticleRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class ArticleController
{
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping ("/articles/create")
    public String creatArticle(ArticleForm form) {

        Article article = form.toEntity();
        log.info(article.toString());

        //System.out.println(article.toString());
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        //System.out.println(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){//매게변수 id 가져오기
        log.info("id = "+id);

        //id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        // 뷰 페이지 반환하기

        return "articles/show";
    }



    @GetMapping("/articles")
    public String index (Model model){
        // 모든 데이터 가져오기
        List<Article>articleEntityList = articleRepository.findAll();
        //모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        // 뷰페이지 설정하기

        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 가져오기
        model. addAttribute("article",articleEntity);
        //뷰페이지설정하기
        return "articles/edit";
    }
    @PostMapping("articles/update")
    public String update (ArticleForm form){
        log.info(form.toString());
        //DTO를 엔티티로 변환하기
        Article articleEntity =form.toEntity();
        log.info(articleEntity.toString());
        // 엔티티를 DB에 저장하기
        //dB에서 기존의 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //기조의 데이터 값을 갱신하기
        if (target != null){
            articleRepository.save(articleEntity);
        }
        //수정결과를 페이지로 다이렉트하기
        return"redirect:/articles/"+articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어왔습니다.");
        //삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 대상 엔티티 삭제하기
        if (target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","DELETE!!");
        }
        // 결과 페이지로 리다이렉트 하기
        return "redirect:/articles";
    }
}
