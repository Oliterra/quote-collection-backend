package edu.quote.collection.service;

import edu.quote.collection.converter.TagConverter;
import edu.quote.collection.dbaccess.repository.TagRepository;
import edu.quote.collection.remote.vo.TagVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final TagConverter tagConverter;

    public List<TagVO> getAllTags() {
        return tagConverter.convertToVOList(tagRepository.findAllByOrderByNameAsc());
    }
}
