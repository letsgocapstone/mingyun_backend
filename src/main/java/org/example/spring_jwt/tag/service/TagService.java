package org.example.spring_jwt.tag.service;

import lombok.RequiredArgsConstructor;
import org.example.spring_jwt.tag.Entity.TagEntity;
import org.example.spring_jwt.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    // 정확한 이름 검색
    public TagEntity findTagByName(String name) {
        return tagRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("태그를 찾을 수 없습니다: " + name));
    }

    // 키워드로 태그 검색 (부분 일치)
    public List<TagEntity> searchTags(String keyword) {
        return tagRepository.findByNameContaining(keyword);
    }
}

