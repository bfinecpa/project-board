package com.dukss.projectboard.repository;

import com.dukss.projectboard.domain.Article;
import com.dukss.projectboard.domain.ArticleComment;
import com.dukss.projectboard.domain.QArticle;
import com.dukss.projectboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long> ,
        QuerydslPredicateExecutor<ArticleComment>, // 자동으로 검색기능을 넣어주는 것 -> 전부 일치하는 검색어만
        QuerydslBinderCustomizer<QArticleComment> // 검색 기능을 커스터마이즈 하기 위해서 넣어줌
{

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root){
        bindings.excludeUnlistedProperties(true);
        bindings.including( root.createdAt, root.createdBy, root.content);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
