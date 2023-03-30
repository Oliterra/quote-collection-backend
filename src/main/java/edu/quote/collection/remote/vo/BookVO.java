package edu.quote.collection.remote.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookVO {

    private Long id;
    private String name;
    private AuthorVO author;
}
