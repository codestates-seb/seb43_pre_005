package com.potatos.stackoverflow.domain.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class MultiResponseDto<T> {
    private T data;
    private PageInfo pageInfo;

    public MultiResponseDto(T response, Page page) {

        this.data = response;
        this.pageInfo = new PageInfo(page.getNumber()+1, page.getSize(), (int) page.getTotalElements(), page.getTotalPages());
    }
}
