package edu.quote.collection.remote.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuoteMainInfoVO {

    private Long id;
    private Long userId;
    private String username;
    private String authorName;
    private String bookName;
    private String text;
    private Double rating;
    private Boolean isPublic;
    private Boolean canBeAddedToGroup;
    private List<String> groupNames;
    private List<TagVO> tags;
}
