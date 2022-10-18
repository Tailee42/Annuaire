package fr.isika.cda21.annuaire.tests;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestLibrairiePDF {
    public static final String DESTINATION = "src/main/resources/hello_word.pdf";

    public static void main(String[] args) {
        try {
            new TestLibrairiePDF().createPDF(DESTINATION);
        } catch (FileNotFoundException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private void createPDF(String fileName) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        FileOutputStream fos = new FileOutputStream(fileName);
        PdfWriter.getInstance(document, fos);

        document.open();

        document.add(new Paragraph("Hello PDF"));

        document.close();
    }
}
