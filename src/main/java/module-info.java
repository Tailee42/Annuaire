module fr.isika.cda21.annuaire {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires itextpdf;


    opens fr.isika.cda21.annuaire to javafx.fxml, javafx.controls, javafx.base;
    exports fr.isika.cda21.annuaire;

    opens fr.isika.cda21.annuaire.tests to javafx.fxml, java.base, javafx.controls;
    exports fr.isika.cda21.annuaire.tests;

}