package classes;
import com.mongodb.client.model.*;
import org.w3c.dom.Document;
import classes.Archivo;
import classes.Noticia;
import classes.Reuter;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.DBObject;
//import com.mongodb.Mongo;
//import com.mongodb.util.JSON;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
//import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.model.Indexes;
import com.mongodb.util.JSON;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
//import org.bson.Document;


//import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Filters;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Conexion {
    public static String Nombre_colleccion;
    public static String Path_conexion;

    public static Mongo ObtenerConexion() {
        try {
            Mongo mongo = new Mongo("localhost", 27017);

            return mongo;


        } catch (Exception e) {

            System.out.println(e);
            return null;

        }



    }
    public static void CrearIndices(String coleccion){
       try{
        Mongo conexion= ObtenerConexion();
        DB db = conexion.getDB("basesmongo");

        DBCollection collection = db.getCollection(coleccion);
        BasicDBObject index = new BasicDBObject("topics",1);
        BasicDBObject index2 = new BasicDBObject("places",1);
        BasicDBObject index3 = new BasicDBObject("people",1);
        BasicDBObject index4 = new BasicDBObject("orgs",1);
        BasicDBObject index5 = new BasicDBObject("exchanges",1);
        BasicDBObject index6 = new BasicDBObject();
           index6.put("notice.body","text");
         index6.put("notice.title","text");


        collection.createIndex(index);
        collection.createIndex(index2);
        collection.createIndex(index3);
        collection.createIndex(index4);
        collection.createIndex(index5);
           collection.createIndex(index6);
           //collection.createIndex(index7);
       }
        catch (Exception e){

        }


    }
    public static void AgregarDatos(String Path,String coleccion){

        Mongo conexion= ObtenerConexion();
        DB db = conexion.getDB("basesmongo");

        DBCollection collection = db.getCollection(coleccion);


        File file = new File(Path);
        File[] files = file.listFiles();
        for(File f: files){
            System.out.println(Path+"/"+f.getName());
            ArrayList<Reuter> arrayList = Archivo.meterArchivo(Path+"/"+f.getName());
            for(int i = 0; i<arrayList.size();i++){

                String json = arrayList.get(i).toString();
                DBObject dbObject = (DBObject)JSON.parse(json);

                collection.insert(dbObject);
            }
        }







    }

    public static String PrimeraConsulta(String coleccion){

        String respuesta="";
        Mongo conexion= ObtenerConexion();
        if (conexion!= null) {
            DB db = conexion.getDB("basesmongo");

            DBCollection collection = db.getCollection(coleccion);
            BasicDBObject allQuery = new BasicDBObject();
            allQuery.append("places","indonesia").append("topics","sugar");


            BasicDBObject fields = new BasicDBObject();
            fields.put("newId",1);
            fields.put("notice.title",1);
            fields.put("_id",0);
            DBCursor consulta= collection.find(allQuery,fields);
            consulta.sort(new BasicDBObject("newId ", 1));
            while (consulta.hasNext()){
                //System.out.println(consulta.next());
                respuesta= respuesta + consulta.next()+"\n";

            }
        }
return respuesta;

    }
    public static String SegundaConsulta(String coleccion){

        String respuesta="";
        Mongo conexion= ObtenerConexion();
        if (conexion!= null) {
            DB db = conexion.getDB("basesmongo");

            DBCollection collection = db.getCollection(coleccion);

            BasicDBObject allQuery = new BasicDBObject();
            DBObject a = new BasicDBObject("$text",new BasicDBObject("$search", " \"Colombia\" \"coffee\" "));

            BasicDBObject fields = new BasicDBObject();
            fields.put("newId",1);
            fields.put("notice.title",1);
            fields.put("_id",0);
            DBCursor consulta= collection.find(a,fields).sort(new BasicDBObject("newId ", 1));
           // consulta.sort(new BasicDBObject("newId ", 1));
            while (consulta.hasNext()){
                respuesta= respuesta + consulta.next()+"\n";
                // System.out.println(consulta.next());
            }

        }
return respuesta;


    }
    public static String TerceraConsulta(String coleccion){
        String respuesta="";
        Mongo conexion= ObtenerConexion();
        if (conexion!= null) {
            DB db = conexion.getDB("basesmongo");

            DBCollection collection = db.getCollection(coleccion);
            String Map = " function(){ for (var lugar=0;lugar<this.places.length;lugar++){ var key = this.places[lugar]; emit(key,1); } }";
            String Reduce = "function(key, values){ return Array.sum(values) }";
            System.out.println("llego aqui");
           // MapReduceCommand cmd;
            System.out.println("llego aqui2");
            MapReduceOutput out = collection.mapReduce(new MapReduceCommand(collection, Map, Reduce, null, MapReduceCommand.OutputType.INLINE, null));
            System.out.println("llego aqui3");
            for (DBObject o : out.results()) {
                //System.out.println(o.toString());
                respuesta= respuesta + o.toString()+"\n";
            }
        }
        else{
            System.out.println("no hubo conexion");
        }
        return respuesta;



    }

}
