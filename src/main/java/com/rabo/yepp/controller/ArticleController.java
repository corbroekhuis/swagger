package com.rabo.yepp.controller;

import com.rabo.yepp.model.Article;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name = "Article Api", description = "This API handles all services for storing, deleting and updating articles in the database ")

public class ArticleController {

    List<Article> articles = new ArrayList<>();

    public ArticleController() {
        articles.add(new Article(1000001, "Laptop XXX", 893.0, "Ultra Gamer laptop"));
        articles.add(new Article(1000002, "Espresso 2000", 435.0, "Original Italian coffee maker"));
        articles.add(new Article(1000003, "Laptop", 893.0, "Gamer laptop"));
    }

    @GetMapping(value="/article", produces = MediaType.APPLICATION_JSON_VALUE)
    // http://localhost:port/api/article
    public ResponseEntity<List<Article>> getAllArticles(){

        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping(value="/article/param", produces = MediaType.APPLICATION_JSON_VALUE)
    // http://localhost:port/api/article/param?id=1
    // Two parameters http://localhost:port/api/article/param?id=1&name=Laptop&....=...
    public ResponseEntity<Article> getArticleByParam(@RequestParam long id){

        Optional<Article> article = articles
                .stream()
                .filter( a -> a.getId() == id)
                .findAny();

        if(article.isPresent()){
            return new ResponseEntity<>(article.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/article/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // http://localhost:port/api/article/1
    public ResponseEntity<Article> getArticleByPathVariable(@PathVariable long id){

        Optional<Article> article = articles
                .stream()
                .filter( a -> a.getId() == id)
                .findAny();

        if(article.isPresent()){
            return new ResponseEntity<>(article.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
