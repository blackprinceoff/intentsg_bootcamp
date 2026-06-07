package com.tasks.task1;

import java.util.List;

public class ReportValidator {
    public void validate(List<String> data) {
        if (data == null || data.isEmpty()) {
            throw new RuntimeException("Data is empty");
        }
    }
}
