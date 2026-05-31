package com.tasks.task1;

import java.util.List;

public class ReportManager {
    private final ReportValidator validator;
    private final ReportFormatter formatter;
    private final ReportDelivery delivery;

    public ReportManager(ReportValidator validator, ReportFormatter formatter, ReportDelivery delivery) {
        this.validator = validator;
        this.formatter = formatter;
        this.delivery = delivery;
    }

    public void generateAndDeliverReport(List<String> data) {
        validator.validate(data);
        String formattedContent = formatter.format(data);
        delivery.deliver(formattedContent);
    }
}
