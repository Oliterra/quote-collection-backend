package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.QuoteFilterVO;
import edu.quote.collection.remote.vo.QuoteListVO;
import edu.quote.collection.remote.vo.QuoteMainInfoVO;
import edu.quote.collection.service.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/quotes")
@RestController
@AllArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping("/page")
    public QuoteListVO getAllQuotes(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return quoteService.getAllQuotesMainInfoPage(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/page/{userId}")
    public QuoteListVO getUserQuotes(@PathVariable Long userId, @RequestParam(defaultValue = "0") int pageNumber,
                                     @RequestParam(defaultValue = "10") int pageSize) {
        return quoteService.getUserQuotesMainInfoPage(userId, PageRequest.of(pageNumber, pageSize));
    }

    @PostMapping("/filter")
    public QuoteListVO getFilteredQuotes(@RequestBody QuoteFilterVO quoteFilter) {
        return quoteService.getFilteredQuotes(quoteFilter);
    }

    @PostMapping("/create")
    public void createQuote(@RequestBody QuoteMainInfoVO quoteMainInfoVO) {
        quoteService.createQuote(quoteMainInfoVO);
    }
}
