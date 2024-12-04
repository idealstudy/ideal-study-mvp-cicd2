package com.idealstudy.mvp.infrastructure;

import com.idealstudy.mvp.application.dto.classroom.preclass.ClassInquiryDto;
import com.idealstudy.mvp.application.dto.classroom.preclass.ClassInquiryPageResultDto;
import com.idealstudy.mvp.enums.classroom.Visibility;

public interface ClassInquiryRepository {

    ClassInquiryDto create(String title, String content, String classroomId, String writer, Visibility visibility);

    ClassInquiryPageResultDto findList(String classId, int page);

    ClassInquiryDto findById(Long inquiryId);

    ClassInquiryDto update(Long inquiryId, String title, String content, String classroomId, String writer, Visibility visibility);

    boolean delete(Long inquiryId);
}
