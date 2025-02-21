package com.example.myboard.api;

import com.example.myboard.Service.CommentService;
import com.example.myboard.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    // コメントリスト照会
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // サービスに委任
        List<CommentDto> dtos = commentService.comments(articleId);
        // 結果応答
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // コメント作成
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto) {
        // サービスに委任
        CommentDto createdDto = commentService.create(articleId, dto);
        // 結果応答
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

}