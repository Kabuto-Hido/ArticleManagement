package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String filterKey;
    private String value;
    Operation operation;

    public enum Operation {
        EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN
    }
}
