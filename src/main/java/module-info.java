module fr.isika.cda21.annuaire {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.isika.cda21.annuaire to javafx.fxml;
    exports fr.isika.cda21.annuaire;
}