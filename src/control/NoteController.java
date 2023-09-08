package control;

import model.Note;
import model.QuoteNote;
import model.ToDoNote;
import model.User;

public class NoteController {

    public NoteController() {
        //Ein leerer Konstruktor erscheint. Und bleibt auch so.
    }

    /**
     * Erstellt ein neues ToDoNote-Objekt
     * @param title Titel der neuen Notiz
     * @param content Inhalt der neuen Notiz
     * @return Das ToDoNotiz-Objekt
     */
    public Note createToDoNote(String title, String content){
        //COMPLETE: Implementiere die Methode
        return new ToDoNote(title, content);
    }

    /**
     * Erstellt ein neues QuoteNote-Objekt
     * @param title Titel der neuen Notiz
     * @param content Inhalt der neuen Notiz
     * @param source Urheber des Zitates
     * @return Das QuoteNote-Objekt
     */
    public Note createQuoteNote(String title, String content, String source){
        return new QuoteNote(title, content, source);
    }

    /**
     * Prüft, ob das Notiz-Objekt an der Position index des Users ein ToDoNote-Objekt ist.
     * Falls ja: der Status der Notiz wird vom Notiz-Objekt umgekehrt, sonst geschieht nichts.
     * @param user Der Nutzer, dessen Notizen gerade betrachtet werden
     * @param index Der Index des gefragten Notiz-Objektes
     */
    public void invertToDoNote(User user, int index){
        if (user.getNotes()[index] instanceof ToDoNote){
            ((ToDoNote) user.getNotes()[index]).invertCompleted();
        }
    }

    /**
     * Ruft die display-Methode der Klasse Notiz (genauer der Unterklassen) auf
     * @param note Das Notiz-Objekt, dessen Inhalt dargestellt werden soll
     * @return Ein String-Objekt, dass alle relevanten Informationen enthält.
     */
    public String createText(Note note){
        return note.display();
    }
}
