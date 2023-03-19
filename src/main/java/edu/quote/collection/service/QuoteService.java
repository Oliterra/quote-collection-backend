package edu.quote.collection.service;

import edu.quote.collection.converter.QuoteConverter;
import edu.quote.collection.dbaccess.entity.UserEntity;
import edu.quote.collection.dbaccess.repository.QuoteRepository;
import edu.quote.collection.dbaccess.repository.UserRepository;
import edu.quote.collection.remote.vo.QuoteMainInfoVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final QuoteConverter quoteConverter;

    public long getAllQuotesCount() {
        return quoteRepository.count();
    }

    public List<QuoteMainInfoVO> getAllQuotesMainInfoPage(Pageable paging) {
        return quoteConverter.convertToMainInfoVOList(quoteRepository.findAllByOrderByCreationTimeAsc(paging).stream().toList());
    }

    public long getUserQuotesCount(Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        return quoteRepository.countByUser(user);
    }

    public List<QuoteMainInfoVO> getUserQuotesMainInfoPage(Long userId, Pageable paging) {
        UserEntity user = userRepository.findById(userId).get();
        return quoteConverter.convertToMainInfoVOList(quoteRepository.findAllByUserOrderByCreationTimeAsc(user, paging).stream().toList());
    }
}
