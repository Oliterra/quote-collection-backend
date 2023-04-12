package edu.quote.collection.remote.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuoteFilterVO {

    private Long bookId;
    private Long authorId;
    private List<Long> categoryIds;
    private String text;
    private List<Long> tagIds;
}
