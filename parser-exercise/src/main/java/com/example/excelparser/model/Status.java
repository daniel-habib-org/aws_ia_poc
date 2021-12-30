package com.example.excelparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status{
    @JsonProperty("Pass")
    PASS,
    @JsonProperty("Fail")
    FAIL
}
