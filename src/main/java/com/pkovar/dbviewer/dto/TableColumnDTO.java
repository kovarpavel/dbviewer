package com.pkovar.dbviewer.dto;

public record TableColumnDTO(
        String columnName,
        String dataType,
        String isNullable
) {
}
