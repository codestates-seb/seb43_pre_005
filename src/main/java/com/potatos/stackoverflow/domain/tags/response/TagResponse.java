package com.potatos.stackoverflow.domain.tags.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagResponse {
    private long id;
    private String tag;
    private String description;
}
