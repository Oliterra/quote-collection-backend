package edu.quote.collection.remote.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuoteListVO {

    private Long count;
    private List<QuoteMainInfoVO> quotes;
}
