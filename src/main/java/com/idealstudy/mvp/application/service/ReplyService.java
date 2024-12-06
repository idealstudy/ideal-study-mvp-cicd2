package com.idealstudy.mvp.application.service;

import com.idealstudy.mvp.application.dto.ReplyDto;
import com.idealstudy.mvp.application.dto.ReplyPageResultDto;
import com.idealstudy.mvp.application.dto.classroom.preclass.ClassInquiryDto;
import com.idealstudy.mvp.enums.DBErrorMsg;
import com.idealstudy.mvp.enums.SecurityErrorMsg;
import com.idealstudy.mvp.enums.classroom.Visibility;
import com.idealstudy.mvp.infrastructure.repository.ReplyRepository;
import com.idealstudy.mvp.util.TryCatchServiceTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    @Autowired
    private final ReplyRepository replyRepository;

    public ReplyDto create(String content, Visibility visibility, Long parentCommentId, Long classInquiryId, 
        Long postId, String createdBy) {

        return TryCatchServiceTemplate.execute(
            () -> replyRepository.create(content, visibility, parentCommentId, classInquiryId, postId, createdBy),
            null,
            DBErrorMsg.CREATE_ERROR
        );
    }

    public ReplyDto findById(Long commentId, String userId) {

        return TryCatchServiceTemplate.execute(
            () -> replyRepository.findById(commentId),
            () -> checkMine(commentId, userId),
            DBErrorMsg.SELECT_ERROR
        );
    }

    public ReplyPageResultDto findListInClassInquiry(Long classInquiryId, int page) {

        return TryCatchServiceTemplate.execute(
            () -> replyRepository.findListInClassInquiry(classInquiryId, page),
            null,
            DBErrorMsg.SELECT_ERROR
        );
    }

    public ReplyDto update(Long id, String userId, String content, Visibility visibility) {

        return TryCatchServiceTemplate.execute(
            () -> replyRepository.update(id, content, visibility),
            () -> checkMine(id, userId),
            DBErrorMsg.UPDATE_ERROR
        );
    }

    public void delete(Long id, String userId) {

        TryCatchServiceTemplate.execute(() -> {
            replyRepository.delete(id);
            return null;
        }, ()-> checkMine(id, userId), DBErrorMsg.DELETE_ERROR);
    }

    private void checkMine(Long commentId, String userId) {

        ReplyDto dto = replyRepository.findById(commentId);

        if( !dto.getCreatedBy().equals(userId)) {
            log.error(SecurityErrorMsg.PRIVATE_EXCEPTION.toString());
            throw new SecurityException(SecurityErrorMsg.PRIVATE_EXCEPTION.toString());
        }
    }
}
