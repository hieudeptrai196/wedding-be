package com.wedding.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.wedding.dto.TemplateDesignDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ExportService {

    public byte[] exportToPdf(TemplateDesignDTO designDTO) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Add simple logic to read DTO and write out to PDF
            // This needs to be expanded for exact coordinate positioning and images
            document.add(new Paragraph("Wedding Invitation"));
            if (designDTO.getTexts() != null) {
                for (TemplateDesignDTO.TextElement text : designDTO.getTexts()) {
                    document.add(new Paragraph(text.getContent()));
                }
            }

            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }

        return outputStream.toByteArray();
    }
}
