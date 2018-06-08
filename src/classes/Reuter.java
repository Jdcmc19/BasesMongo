package classes;

import java.util.ArrayList;
import java.util.Date;

public class Reuter {
    int newId;
    Date date;
    ArrayList<String> topics;
    ArrayList<String> places;
    ArrayList<String> people;
    ArrayList<String> orgs;
    ArrayList<String> exchanges;
    Noticia notice;

    public Reuter(int newId, Date date, ArrayList<String> topics, ArrayList<String> places, ArrayList<String> people, ArrayList<String> orgs, ArrayList<String> exchanges,Noticia notice) {
        this.newId = newId;
        this.date = date;
        this.topics = topics;
        this.places = places;
        this.people = people;
        this.orgs = orgs;
        this.exchanges = exchanges;
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "{\"newId\" : "+newId+", \"date\" : \""+date.toString()+"\", \"topics\" : "+topics+", \"places\" : "+places+", \"people\" : "+people+", \"orgs\" : "+orgs+", \"exchanges\" : "+exchanges+", \"notice\" : "+notice.toString()+"}";
    }
}
