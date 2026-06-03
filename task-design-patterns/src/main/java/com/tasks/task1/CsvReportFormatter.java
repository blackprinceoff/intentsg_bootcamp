package com.tasks.task1;

import java.util.List;

public class CsvReportFormatter implements ReportFormatter {
    @Override
    public String format(List<String> data) {
        StringBuilder builder = new StringBuilder();
        builder.append("Generating CSV...\n");
        for (String line : data) {
            builder.append(line).append(",\n");
        }
        return builder.toString();
    }
}
