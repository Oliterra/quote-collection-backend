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
    private BookVO book;
    private String text;
    private Double rating;
    private Boolean isPublic;
    private Boolean canBeAddedToGroup;
    private List<GroupVO> groups;
    private List<TagVO> tags;
}
