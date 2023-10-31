package booksctocked.booksctocked.CustomUtil;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SearchBarComboBox {
    private Map<String, String> map;
    private ObservableList<Map.Entry<String, String>> list;
    private ObservableList<String> names;
    private ComboBox<String> comboBox;
    private TextField searchField;

    public SearchBarComboBox() {
        map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("target/CrystalPalace/books_csv.csv"))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] fields = line.split(",");


                map.put(fields[0], fields[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        map.forEach((k, v) -> System.out.println(k + ": " + v));


        list = FXCollections.observableArrayList(map.entrySet());


        names = FXCollections.observableArrayList();

        list.forEach(entry -> names.add(entry.getKey() + " " + entry.getValue()));

        comboBox = new ComboBox<>(names);
        comboBox.setEditable(true);


        searchField = comboBox.getEditor();
        searchField.setPromptText("Search...");


        searchField.textProperty().addListener((observable, oldValue, newValue) -> {

            comboBox.setItems(names.filtered(name -> name.toLowerCase().contains(newValue.toLowerCase())));

            comboBox.show();
        });
    }

    public ComboBox<String> getComboBox() {
        return comboBox;
    }
}