package com.potatos.stackoverflow.domain.response;

import com.potatos.stackoverflow.domain.question.dto.QuestionResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SingleResponseDto<T> {
   private T data;
}
