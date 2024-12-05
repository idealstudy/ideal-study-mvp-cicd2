package com.idealstudy.mvp.application.dto.classroom.preclass;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FAQPageResultDto {

    private List<FAQDto> dtoList;

    private int totalPage;

    private int page;

    private int size;

    private int startPage, endPage;

    private boolean hasPrev, hasNext;

    private List<Integer> pageList;
}