package com.potatos.stackoverflow.domain.search.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    Pageable pageable;
    String word;
}
