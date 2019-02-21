package io.anshily.shiro.jwt.controller;

import java.util.List;

import io.anshily.shiro.jwt.dto.ArticleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@RestController
//@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public ResponseEntity<List<ArticleDto>> list(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> read(@PathVariable Long id){
        return null;
    }


}
