package com.example.excelparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ParserResult {
    String fileName;
    @JsonProperty("valid_rows")
    Long validRows;
    @JsonProperty("invalid_rows")
    Long invalidRows;
    Status status;
}
