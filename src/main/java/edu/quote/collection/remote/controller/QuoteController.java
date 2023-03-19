package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.QuoteMainInfoVO;
import edu.quote.collection.service.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/quotes")
@RestController
@AllArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping("/count")
    public long getAllQuotesCount() {
        return quoteService.getAllQuotesCount();
    }

    @GetMapping("/count/{userId}")
    public long getUserQuotesCount(@PathVariable Long userId) {
        return quoteService.getUserQuotesCount(userId);
    }

    @GetMapping("/page")
    public List<QuoteMainInfoVO> getAllQuotesMainInfoPage(@RequestParam(defaultValue = "0") int pageNumber,
                                                          @RequestParam(defaultValue = "10") int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return quoteService.getAllQuotesMainInfoPage(paging);
    }

    @GetMapping("/page/{userId}")
    public List<QuoteMainInfoVO> getUserQuotesMainInfoPage(@PathVariable Long userId,
                                                           @RequestParam(defaultValue = "0") int pageNumber,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return quoteService.getUserQuotesMainInfoPage(userId, paging);
    }
}
