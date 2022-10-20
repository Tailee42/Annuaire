module fr.isika.cda21.annuaire {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires itextpdf;


    opens fr.isika.cda21.annuaire to javafx.fxml, javafx.base, javafx.controls;
    exports fr.isika.cda21.annuaire;

    opens fr.isika.cda21.annuaire.application to javafx.fxml, javafx.base, javafx.controls;
    exports fr.isika.cda21.annuaire.application;

    opens fr.isika.cda21.annuaire.tests to javafx.fxml, javafx.base, javafx.controls;
    exports fr.isika.cda21.annuaire.tests;

    opens fr.isika.cda21.annuaire.vues to javafx.fxml, javafx.base, javafx.controls;
    exports fr.isika.cda21.annuaire.vues;

    opens fr.isika.cda21.annuaire.models to javafx.fxml, javafx.base, javafx.controls;
    exports fr.isika.cda21.annuaire.models;
}