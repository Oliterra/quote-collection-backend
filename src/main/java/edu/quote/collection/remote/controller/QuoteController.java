package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.QuoteMainInfoVO;
import edu.quote.collection.service.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/quotes")
@RestController
@AllArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping
    public List<QuoteMainInfoVO> getAllQuotesMainInfo() {
        return quoteService.getAllQuotesMainInfo();
    }
}
