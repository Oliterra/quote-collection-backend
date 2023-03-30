package edu.quote.collection.converter;

import edu.quote.collection.dbaccess.entity.AuthorEntity;
import edu.quote.collection.dbaccess.entity.BookEntity;
import edu.quote.collection.remote.vo.BookVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookConverter extends BaseConverter {

    private final AuthorConverter authorConverter;

    public List<BookVO> convertToVOList(List<BookEntity> bookEntities) {
        List<BookVO> bookVOList = new ArrayList<>();
        if (bookEntities != null) {
            bookVOList = bookEntities.stream()
                    .map(this::convertToVO)
                    .collect(Collectors.toList());
        }
        return bookVOList;
    }

    public BookVO convertToVO(BookEntity bookEntity) {
        BookVO bookVO = super.convert(bookEntity, BookVO.class);
        AuthorEntity author = bookEntity.getAuthor();
        if (author != null) {
            bookVO.setAuthor(authorConverter.convertToVO(author));
        }
        return bookVO;
    }
}
