package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextField txtPath;
    @FXML
    Button btoExaminar,btoCargarDatos,btoCrearIndices,btoConsulta1,btoConsulta2,btoConsulta3;
    @FXML
    TextArea txtConsultas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btoConsulta1.setOnAction(event -> {
            txtConsultas.setText("Consulta 1");
        });
        btoConsulta2.setOnAction(event -> {
            txtConsultas.setText("Consulta 2");
        });
        btoConsulta3.setOnAction(event -> {
            txtConsultas.setText("Consulta 3");
        });
        btoExaminar.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File file = directoryChooser.showDialog(null);
            txtPath.setText(file.getAbsolutePath());
            System.out.println("abrir filechooser");
        });
        btoCargarDatos.setOnAction(event -> {
            System.out.println("carga datos del path"+txtPath.getText());
        });
        btoCrearIndices.setOnAction(event -> {
            System.out.println("crear indices");
        });
    }
}
