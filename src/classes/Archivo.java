package classes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Archivo {
    public static ArrayList<Reuter> meterArchivo(){
        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("C:/Users/iWorth/iCloudDrive/Documents/Semestre I 2018/Bases II/Proyecto/Proyect/Proyecto 3/reuters21578/reut2-014.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());


            NodeList noticias = doc.getElementsByTagName("REUTERS");
            int cant = noticias.getLength();
            System.out.println("Cantidad : " + cant);
            ArrayList<Reuter> arrayReuters = new ArrayList<>();

            for(int s=0; s<cant ; s++){


                Node noticiaNodo = noticias.item(s);
                if(noticiaNodo.getNodeType() == Node.ELEMENT_NODE){


                    Element noticiaElemento = (Element)noticiaNodo;
                    int newid= Integer.parseInt(noticiaElemento.getAttribute("NEWID"));
                    // System.out.println("NEWID: "+newid);

                    //------------------------------------------
                    NodeList dateNodo = noticiaElemento.getElementsByTagName("DATE");
                    Element dateElement = (Element)dateNodo.item(0);

                    NodeList dateList = dateElement.getChildNodes();

                    Date fecha = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSSSSS").parse(dateList.item(0).getTextContent());
                    // System.out.println("DATE: "+fecha.toString());
                    //-------------------------------------------
                    NodeList topicsNodo = noticiaElemento.getElementsByTagName("TOPICS");
                    Element topicsElement = (Element)topicsNodo.item(0);

                    NodeList topicsList = topicsElement.getChildNodes();

                    ArrayList<String> toppics = new ArrayList<>();
                    //System.out.println("TOPICS : " + topicsList.getLength());
                    for(int i=0;i<topicsList.getLength();i++){
                        toppics.add("\""+topicsList.item(i).getTextContent().replaceAll("\"","\'")+"\"");
                        // System.out.println('\t'+topicsList.item(i).getTextContent());
                    }
                    //-------------------------------------------
                    NodeList placeNodo = noticiaElemento.getElementsByTagName("PLACES");
                    Element placeElement = (Element)placeNodo.item(0);

                    NodeList placeList = placeElement.getChildNodes();
                    ArrayList<String> placces = new ArrayList<>();
                    // System.out.println("PLACES : " + placeList.getLength());

                    for(int i=0;i<placeList.getLength();i++){
                        placces.add("\""+placeList.item(i).getTextContent().replaceAll("\"","\'")+"\"");
                        //System.out.println('\t'+placeList.item(i).getTextContent());
                    }
                    //--------------------------------
                    NodeList peopleNodo = noticiaElemento.getElementsByTagName("PEOPLE");
                    Element peopleElement = (Element)peopleNodo.item(0);

                    NodeList peopleList = peopleElement.getChildNodes();
                    ArrayList<String> peopple = new ArrayList<>();
                    //System.out.println("PEOPLE : " + peopleList.getLength());

                    for(int i=0;i<peopleList.getLength();i++){
                        peopple.add("\""+peopleList.item(i).getTextContent().replaceAll("\"","\'")+"\"");
                        //   System.out.println('\t'+peopleList.item(i).getTextContent());
                    }
                    //---------------------------------------------
                    NodeList orgsNodo = noticiaElemento.getElementsByTagName("ORGS");
                    Element orgsElement = (Element)orgsNodo.item(0);

                    NodeList orgsList = orgsElement.getChildNodes();
                    ArrayList<String> orggs = new ArrayList<>();
                    // System.out.println("ORGS : " + orgsList.getLength());

                    for(int i=0;i<orgsList.getLength();i++){
                        orggs.add("\""+orgsList.item(i).getTextContent().replaceAll("\"","\'")+"\"");
                        //  System.out.println('\t'+orgsList.item(i).getTextContent());
                    }
                    //-------
                    NodeList exchangesNodo = noticiaElemento.getElementsByTagName("EXCHANGES");
                    Element exchangesElement = (Element)exchangesNodo.item(0);

                    NodeList exchangesList = exchangesElement.getChildNodes();
                    ArrayList<String> exxchanges = new ArrayList<>();
                    // System.out.println("EXCHANGES : " + exchangesList.getLength());

                    for(int i=0;i<exchangesList.getLength();i++){
                        exxchanges.add("\""+exchangesList.item(i).getTextContent().replaceAll("\"","\'")+"\"");
                        // System.out.println('\t'+exchangesList.item(i).getTextContent());
                    }
                    //-------
                    NodeList textNodo = noticiaElemento.getElementsByTagName("TEXT");
                    Element textElement = (Element)textNodo.item(0);

                    NodeList textList = textElement.getChildNodes();
                    String nodeName="";
                    String title="";
                    String author="";
                    String dateline="";
                    String body="";

                    for(int i=0;i<textList.getLength();i++){
                        nodeName = textList.item(i).getNodeName();
                        if(nodeName=="TITLE"){
                            title=textList.item(i).getTextContent();
                            //  System.out.println("TITLE: "+textList.item(i).getTextContent());
                        }
                        else if(nodeName=="AUTHOR"){
                            author=textList.item(i).getTextContent();
                            //System.out.println("AUTHOR: "+textList.item(i).getTextContent());
                        }
                        else if(nodeName=="DATELINE"){
                            dateline=textList.item(i).getTextContent();
                            //System.out.println("DATELINE: "+textList.item(i).getTextContent());
                        }
                        else if(nodeName=="BODY" || textList.getLength()==1){
                            body = textList.item(i).getTextContent();
                            //System.out.println("BODY: "+textList.item(i).getTextContent());
                        }
                    }
                    Noticia n = new Noticia(title,author,dateline,body);
                    Reuter r = new Reuter(newid,fecha,toppics,placces,peopple,orggs,exxchanges,n);
                    arrayReuters.add(r);
                }



            }return arrayReuters;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
