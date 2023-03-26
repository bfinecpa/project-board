package com.dukss.projectboard.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Article {

    private Long id;
    private String title;
    private String content;
    private String hashtag;

    private LocalDateTime createdAt;
    private String  createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

}
