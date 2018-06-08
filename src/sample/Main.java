package sample;

import classes.Archivo;
import classes.Noticia;
import classes.Reuter;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
       // launch(args);

        try{
            Mongo mongo = new Mongo("localhost",27017);
            DB db = mongo.getDB("basesmongo");

            DBCollection collection = db.getCollection("reuters");

            ArrayList<Reuter> arrayList = Archivo.meterArchivo();
            for(int i = 0; i<arrayList.size();i++){

            String json = arrayList.get(i).toString();
            DBObject dbObject = (DBObject)JSON.parse(json);

            collection.insert(dbObject);
            }
            System.out.println("finish");



        }catch (Exception e){
            System.out.println(e.getMessage());}

    }
}
