package fr.isika.cda21.annuaire.models;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class ImpressionPDF extends Application {
    public static final String DESTINATION = "src/main/resources/impressionRecherche.pdf";

    // Crée le pdf, ouvre l'écriture dessus, puis ecris chaque stagaire de la liste.
    public void createPDF(List<Stagiaire> listeRecherche) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        FileOutputStream fos = new FileOutputStream(DESTINATION);
        PdfWriter.getInstance(document, fos);

        Font fontTitle = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font fontLigneGras = new Font(Font.FontFamily.COURIER, 8, Font.BOLD);
        Font fontLigne = new Font(Font.FontFamily.COURIER, 8);

        document.open();

        document.add(new Paragraph("Liste des stagaires recherchés", fontTitle));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));


        document.add(new Paragraph("   Nom                  | Prénom               | Département          | Promotion            | Année"  , fontLigneGras));

        int i = 1;
        for(Stagiaire stagiaire : listeRecherche){
            document.add(new Paragraph(i + ". " + stagiaire.toString(), fontLigne));
            i++;
        }

        document.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        File pdfFile = new File(DESTINATION);
        getHostServices().showDocument(pdfFile.toURI().toString());
    }
}
