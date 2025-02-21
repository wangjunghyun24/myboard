package com.example.myboard.controller;

import com.example.myboard.Service.CommentService;
import com.example.myboard.dto.CommentDto;
import com.example.myboard.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.myboard.dto.ArticleForm;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.myboard.repository.ArticleRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import java.util.List;

@Slf4j
@Controller
public class ArticleController
{
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
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

        //idを照会してデータを取得する
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);

        //モデルにデータを登録する
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos", commentsDtos);

        // ビューページを返す

        return "articles/show";
    }



    @GetMapping("/articles")
    public String index (Model model, @PageableDefault(size = 3) Pageable pageable){
        Page<Article> articleEntityList = articleRepository.findAll(pageable);
        //すべてのデータのインポート
        //List<Article>articleEntityList = articleRepository.findAll();
        //モデルにデータを登録する
        model.addAttribute("articleList",articleEntityList);
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", articleEntityList.hasNext());
        model.addAttribute("hasPrev", articleEntityList.hasPrevious());

        // ビューページを設定する

        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //修正するデータのインポート
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // モデルへのデータのインポート
        model. addAttribute("article",articleEntity);
        //ビューページを設定する
        return "articles/edit";
    }
    @PostMapping("articles/update")
    public String update (ArticleForm form){
        log.info(form.toString());
        //DTOをエンティティに変換する
        Article articleEntity =form.toEntity();
        log.info(articleEntity.toString());
        // エンティティをDBに保存する
        //DBから既存のデータをインポートする
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //既存のデータ値を更新する
        if (target != null){
            articleRepository.save(articleEntity);
        }
        //修正結果をページにリダイレクトする
        return"redirect:/articles/"+articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어왔습니다.");
        //削除するオブジェクトの取得
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 対象エンティティを削除する
        if (target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","DELETE!!");
        }
        // 結果ページへのリダイレクト
        return "redirect:/articles";
    }
}
