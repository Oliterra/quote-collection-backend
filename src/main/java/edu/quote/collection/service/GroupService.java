package edu.quote.collection.service;

import edu.quote.collection.converter.GroupConverter;
import edu.quote.collection.dbaccess.repository.GroupRepository;
import edu.quote.collection.remote.vo.GroupVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupConverter groupConverter;

    public List<GroupVO> getAllGroups() {
        return groupConverter.convertToVOList(groupRepository.findAllByOrderByNameAsc());
    }

    public List<GroupVO> getUserGroups(Long userId) {
        return groupConverter.convertToVOList(groupRepository.findAllByUserIdOrderByNameAsc(userId));
    }
}
