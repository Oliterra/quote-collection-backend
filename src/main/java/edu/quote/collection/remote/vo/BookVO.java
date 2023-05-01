package edu.quote.collection.remote.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookVO {

    private Long id;
    private String name;
    private Long userId;
    private AuthorVO author;
    private List<CategoryVO> categories;
    private Integer quotesCount;
}
