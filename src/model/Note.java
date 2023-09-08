package model;

import java.util.Date;

public abstract class Note {
    protected String title;
    protected String content;
    protected Date creationDate;

    public Note(String title, String content) {
        //COMPLETE: Implementiere den Konstruktor
        this.title = title;
        this.content = content;
        this.creationDate = new Date();
    }

    public abstract String display();



    //Getter und Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
