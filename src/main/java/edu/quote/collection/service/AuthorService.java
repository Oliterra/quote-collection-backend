package edu.quote.collection.service;

import edu.quote.collection.converter.AuthorConverter;
import edu.quote.collection.dbaccess.entity.AuthorEntity;
import edu.quote.collection.dbaccess.repository.AuthorRepository;
import edu.quote.collection.remote.vo.AuthorVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;

    public List<AuthorVO> getAllAuthors() {
        return authorConverter.convertToVOList(authorRepository.findAllByOrderByNameAsc());
    }

    public AuthorVO createAuthor(AuthorVO author) {
        AuthorEntity savedAuthor = authorRepository.save(authorConverter.convertToEntity(author));
        return authorConverter.convertToVO(savedAuthor);
    }
}
