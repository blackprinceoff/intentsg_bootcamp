package com.tasks.task1;

import java.util.List;

public class PdfReportFormatter implements ReportFormatter {
    @Override
    public String format(List<String> data) {
        StringBuilder builder = new StringBuilder();
        builder.append("Generating PDF...\n");
        for (String line : data) {
            builder.append("[PDF] ").append(line).append("\n");
        }
        return builder.toString();
    }
}
