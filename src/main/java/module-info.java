module booksctocked.booksctocked {
    requires javafx.controls;
    requires javafx.fxml;


    opens booksctocked.booksctocked to javafx.fxml;
    exports booksctocked.booksctocked;
}