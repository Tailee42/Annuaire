package fr.isika.cda21.annuaire.models;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
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
        Font fontHeader = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font fontCell = new Font(Font.FontFamily.HELVETICA, 10);
        BaseColor orange = new BaseColor(247, 157, 79);
        BaseColor grisClair = new BaseColor(229, 229, 229);

        PdfPTable tableauStagaires = new PdfPTable(new float[] { 3, 3, 2, 2, 2 });

        //Mise en page  du tableau
        tableauStagaires.setWidthPercentage(100f);
        tableauStagaires.getDefaultCell().setPadding(5);
        tableauStagaires.setHeaderRows(1);

        //Remplissage du Header
        tableauStagaires.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableauStagaires.getDefaultCell().setBackgroundColor(orange);

        tableauStagaires.addCell(new Phrase("Nom", fontHeader));
        tableauStagaires.addCell(new Phrase("Prénom", fontHeader));
        tableauStagaires.addCell(new Phrase("Département", fontHeader));
        tableauStagaires.addCell(new Phrase("Promotion", fontHeader));
        tableauStagaires.addCell(new Phrase("Année de formation", fontHeader));

        //Remplissage du contenu
        tableauStagaires.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableauStagaires.getDefaultCell().setBackgroundColor(null);


        int i = 0;
        for(Stagiaire stagiaire : listeRecherche){
            if (i%2 != 0) {
                tableauStagaires.getDefaultCell().setBackgroundColor(grisClair);
            } else {
                tableauStagaires.getDefaultCell().setBackgroundColor(null);
            }
            tableauStagaires.addCell(new Phrase(stagiaire.getNom(), fontCell));
            tableauStagaires.addCell(new Phrase(stagiaire.getPrenom(), fontCell));
            tableauStagaires.addCell(new Phrase(stagiaire.getDepartement(), fontCell));
            tableauStagaires.addCell(new Phrase(stagiaire.getPromo(), fontCell));
            tableauStagaires.addCell(new Phrase(String.valueOf(stagiaire.getAnneeDeFormation()), fontCell));

            i++;
        }

        document.open();

        //écriture du document
        document.add(new Paragraph("Liste des stagaires recherchés", fontTitle));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        document.add(tableauStagaires);

        document.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        File pdfFile = new File(DESTINATION);
        getHostServices().showDocument(pdfFile.toURI().toString());
    }
}
