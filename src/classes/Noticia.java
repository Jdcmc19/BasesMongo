package classes;

public class Noticia {
    String title;
    String author;
    String dateline;
    String body;

    public Noticia(String title, String author, String dateline, String body) {
        this.title = title;
        this.author = author;
        this.dateline = dateline;
        this.body = body;
    }

    @Override
    public String toString() {
        return "{\"title\" : \""+title.replaceAll("\"","\'")+"\", \"author\" : \""+author.replaceAll("\"","\'")+"\", \"dateline\" : \""+dateline.replaceAll("\"","\'")+"\", \"body\" : \""+body.replaceAll("\"","\'")+"\"}";
    }
}
