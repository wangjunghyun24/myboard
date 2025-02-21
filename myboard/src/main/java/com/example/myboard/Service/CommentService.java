package com.example.myboard.Service;

import com.example.myboard.dto.CommentDto;
import com.example.myboard.entity.Article;
import com.example.myboard.entity.Comment;
import com.example.myboard.repository.ArticleRepository;
import com.example.myboard.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    public List<CommentDto> comments(Long articleId) {
        // 返還
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 掲示文の照会及び例外発生
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니."));
        // コメントエンティティ作成
        Comment comment = Comment.createComment(dto, article);
        // コメントエンティティをDBに保存
        Comment created = commentRepository.save(comment);
        // DTOに変更して返還
        return CommentDto.createCommentDto(created);
    }


}
