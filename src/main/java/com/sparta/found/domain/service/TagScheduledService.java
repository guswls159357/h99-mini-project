package com.sparta.found.domain.service;

import com.sparta.found.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagScheduledService {

    private final TagRepository tagRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(fixedRate = 1000*60*60)
    public void clearDeletedTag(){

        tagRepository.deleteAllByDeletedReference();
    }
}
