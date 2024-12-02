package com.idealstudy.mvp.application.service.classroom;

import com.idealstudy.mvp.application.dto.PageRequestDto;
import com.idealstudy.mvp.application.dto.classroom.FAQDto;
import com.idealstudy.mvp.application.dto.classroom.FAQPageResultDto;
import com.idealstudy.mvp.infrastructure.FAQRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FAQService {

    private final static Integer PAGE_SIZE = 10;

    @Autowired
    private final FAQRepository faqRepository;

    public void create(String teacherId, String classroomId, String title, String content) {

        FAQDto dto = FAQDto.builder()
                .title(title)
                .content(content)
                .createdBy(teacherId)
                .classroomId(classroomId)
                .build();
        try {
            faqRepository.create(dto);
        } catch (Exception e) {
            log.error(e + " : " + e.getMessage());
        }
    }

    public FAQDto findById(Long faqId) {

        return faqRepository.findById(faqId);
    }

    public FAQPageResultDto findList(int page) {

        PageRequestDto dto = PageRequestDto.builder()
                .page(page)
                .size(PAGE_SIZE)
                .build();

        return faqRepository.findList(dto);
    }

    public FAQDto updateTitle(Long faqId, String title) {

        FAQDto dto = FAQDto.builder()
                .id(faqId)
                .title(title)
                .build();

        return faqRepository.update(dto);
    }

    public FAQDto updateContent(Long faqId, String content) {

        FAQDto dto = FAQDto.builder()
                .id(faqId)
                .content(content)
                .build();

        return faqRepository.update(dto);
    }

    public FAQDto update(Long faqId, String title, String content) {

        FAQDto dto = FAQDto.builder()
                .id(faqId)
                .title(title)
                .content(content)
                .build();

        return faqRepository.update(dto);
    }

    public boolean delete(Long faqId) {

        try {
            faqRepository.delete(faqId);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
