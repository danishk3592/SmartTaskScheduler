package com.danish.export;

import com.danish.model.Task;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PDFExportService {

    public boolean exportTasksToPDF(List<Task> tasks, String fileName) {

        Path directory = Path.of("pdfs");

        try {
            Files.createDirectories(directory);

            Path filePath = directory.resolve(fileName);

            try (PDDocument document = new PDDocument()) {

                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);

                PDType1Font font =
                        new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                PDType1Font boldFont =
                        new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

                float margin = 50;
                float y = 750;

                try (PDPageContentStream content =
                             new PDPageContentStream(document, page)) {

                    // Title
                    content.beginText();
                    content.setFont(boldFont, 18);
                    content.newLineAtOffset(margin, y);
                    content.showText("SMART TASK SCHEDULER");
                    content.endText();

                    y -= 30;

                    // Report title
                    content.beginText();
                    content.setFont(boldFont, 14);
                    content.newLineAtOffset(margin, y);
                    content.showText("TASK REPORT");
                    content.endText();

                    y -= 25;

                    // Total tasks
                    content.beginText();
                    content.setFont(font, 11);
                    content.newLineAtOffset(margin, y);
                    content.showText("Total Tasks: " + tasks.size());
                    content.endText();

                    y -= 30;

                    for (Task task : tasks) {

                        // New page if necessary
                        if (y < 100) {

                            content.close();

                            page = new PDPage(PDRectangle.A4);
                            document.addPage(page);

                            y = 750;
                        }

                        try (PDPageContentStream pageContent =
                                     new PDPageContentStream(
                                             document,
                                             page,
                                             PDPageContentStream.AppendMode.APPEND,
                                             true,
                                             true)) {

                            pageContent.beginText();
                            pageContent.setFont(boldFont, 11);
                            pageContent.newLineAtOffset(margin, y);
                            pageContent.showText(
                                    "Task ID: " + task.getTaskId()
                            );
                            pageContent.endText();

                            y -= 18;

                            writeLine(
                                    pageContent,
                                    font,
                                    margin,
                                    y,
                                    "Category: " + safe(task.getCategoryName())
                            );

                            y -= 16;

                            writeLine(
                                    pageContent,
                                    font,
                                    margin,
                                    y,
                                    "Title: " + safe(task.getTitle())
                            );

                            y -= 16;

                            writeLine(
                                    pageContent,
                                    font,
                                    margin,
                                    y,
                                    "Priority: " + task.getPriority()
                            );

                            y -= 16;

                            writeLine(
                                    pageContent,
                                    font,
                                    margin,
                                    y,
                                    "Status: " + task.getStatus()
                            );

                            y -= 16;

                            writeLine(
                                    pageContent,
                                    font,
                                    margin,
                                    y,
                                    "Due Date: " + task.getDueDate()
                            );

                            y -= 25;

                            writeLine(
                                    pageContent,
                                    font,
                                    margin,
                                    y,
                                    "----------------------------------------"
                            );

                            y -= 20;
                        }
                    }
                }

                document.save(filePath.toFile());
            }

            return true;

        } catch (IOException e) {

            System.out.println(
                    "PDF Export Error: " + e.getMessage()
            );

            return false;
        }
    }

    private void writeLine(
            PDPageContentStream content,
            PDType1Font font,
            float x,
            float y,
            String text
    ) throws IOException {

        content.beginText();
        content.setFont(font, 10);
        content.newLineAtOffset(x, y);
        content.showText(cleanText(text));
        content.endText();
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }

    private String cleanText(String text) {

        if (text == null) {
            return "";
        }

        // PDFBox standard Helvetica does not support every Unicode character.
        return text
                .replace("₹", "Rs.")
                .replace("–", "-")
                .replace("—", "-")
                .replace("’", "'")
                .replace("“", "\"")
                .replace("”", "\"");
    }
}