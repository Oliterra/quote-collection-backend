package edu.quote.collection.service;

import edu.quote.collection.converter.QuoteConverter;
import edu.quote.collection.dbaccess.repository.QuoteRepository;
import edu.quote.collection.remote.vo.QuoteMainInfoVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Transactional
@Service
@AllArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final QuoteConverter quoteConverter;

    public List<QuoteMainInfoVO> getAllQuotesMainInfo() {
        return quoteConverter.convertToMainInfoVOList(quoteRepository.findAll());
    }
}
