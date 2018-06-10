package sample;


import classes.Archivo;
import classes.Conexion;
import classes.Reuter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextField txtPath,txtBase,txtColeccion;
    @FXML
    Button btoExaminar,btoCargarDatos,btoCrearIndices,btoConsulta1,btoConsulta2,btoConsulta3;
    @FXML
    TextArea txtConsultas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btoConsulta1.setOnAction(event -> {
            txtConsultas.setText("");

            txtConsultas.setText(Conexion.PrimeraConsulta(Conexion.Nombre_colleccion));
        });
        btoConsulta2.setOnAction(event -> {
            txtConsultas.setText("");
            txtConsultas.setText(Conexion.SegundaConsulta(Conexion.Nombre_colleccion));
        });
        btoConsulta3.setOnAction(event -> {
            txtConsultas.setText("");
            txtConsultas.setText(Conexion.TerceraConsulta(Conexion.Nombre_colleccion));
        });
        btoExaminar.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File file = directoryChooser.showDialog(null);
            Conexion.Path_conexion=file.getAbsolutePath();
            System.out.println(file.getAbsolutePath());
            txtPath.setText(file.getAbsolutePath());
            System.out.println("abrir filechooser");
        });
        btoCargarDatos.setOnAction(event -> {
            Conexion.Nombre_colleccion=txtColeccion.getText();

            System.out.println("carga datos del path"+txtPath.getText());
            Conexion.AgregarDatos(Conexion.Path_conexion,Conexion.Nombre_colleccion);
        });
        btoCrearIndices.setOnAction(event -> {
            Conexion.CrearIndices(Conexion.Nombre_colleccion);
            System.out.println("crear indices");
        });

    }
    /*public void empezar(String path,String base,String coleccion){
        try{
            Mongo mongo = new Mongo("localhost",27017);
            DB db = mongo.getDB(base);

            DBCollection collection = db.getCollection(coleccion);

            ArrayList<Reuter> arrayList = Archivo.meterArchivo();
            for(int i = 0; i<arrayList.size();i++){

                String json = arrayList.get(i).toString();
                DBObject dbObject = (DBObject)JSON.parse(json);

                collection.insert(dbObject);
            }
            System.out.println("finish");



        }catch (Exception e){
            System.out.println(e.getMessage());}
    }*/
}
