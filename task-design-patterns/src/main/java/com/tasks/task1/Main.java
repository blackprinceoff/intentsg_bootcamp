package com.tasks.task1;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> data = Arrays.asList("Report Line 1", "Report Line 2", "Report Line 3");

        System.out.println("--- PDF to File ---");
        ReportManager pdfFileManager = new ReportManager(
                new ReportValidator(),
                new PdfReportFormatter(),
                new FileDeliveryService()
        );
        pdfFileManager.generateAndDeliverReport(data);

        System.out.println("\n--- CSV to Email ---");
        ReportManager csvEmailManager = new ReportManager(
                new ReportValidator(),
                new CsvReportFormatter(),
                new EmailDeliveryService()
        );
        csvEmailManager.generateAndDeliverReport(data);
    }
}
