package edu.quote.collection.remote.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookFilterVO {

    private Long bookId;
    private Long authorId;
    private Long userId;
    private List<Long> categoryIds;
}
