package com.dukss.projectboard.repository;

import com.dukss.projectboard.config.JpaConfig;
import com.dukss.projectboard.domain.Article;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled("Spring Data REST 통합 테스트는 불필요하므로 제외")
@DisplayName("JPA 연결 테스트")
@DataJpaTest
@Import(JpaConfig.class)
class JpaRepositoryTest {

    @Autowired ArticleRepository articleRepository;
    @Autowired ArticleCommentRepository articleCommentRepository;

    @Test
    @DisplayName("select test")
    void givenTestData_whenSelect_then(){
        // Given

        // When
        List<Article> articles = articleRepository.findAll();
        // Then
        assertThat(articles).isNotNull().hasSize(123);
    }

    @Test
    void givenTestData_whenInserting_then(){
        // Given
        long previousCount = articleRepository.count();
        Article article = Article.of("new Article", "new content", "new hashtag");
        // When
        articleRepository.save(article);

        // Then

        assertThat(articleRepository.count()).isEqualTo(previousCount+1);
    }

    @Test
    void givenTestData_whenUpdating_then(){
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "#Springboot";
        article.setHashtag(updateHashtag);
        // When
        Article saveArticle = articleRepository.save(article);
        articleRepository.flush();
        // Then
        assertThat(saveArticle).hasFieldOrPropertyWithValue("hashtag", updateHashtag);

    }

}