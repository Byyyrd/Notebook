package model;

import Helper.ArrayHelper;

import java.util.Arrays;

public class User {
    private String username;
    private Note[] notes;
    private int noteCount;

    public User(String username){
        //COMPLETE: Implementiere den Konstruktor.
        this.username = username;
        noteCount = 0;
        notes = new Note[20];
    }

    /**
     * Fügt dem Nutzer eine neue Notiz hinzu. Dies geschieht nur, falls noch genügend Platz im Array ist.
     * @param note Die übergebene Notiz.
     * @return Boolscher Wert, ob das Hinzufügen funktioniert hat oder nicht.
     */
    public boolean addNote(Note note) {
        //COMPLETE: Implementiere die Methode
        if (noteCount < notes.length){
            notes[noteCount] = note;
        }else{
            notes = ArrayHelper.arrayAdd(Note.class,notes,note);
        }
        noteCount++;
        return true;
    }

    /**
     * Löscht die Notiz, die an der entsprechenden Stelle im Notizen-Array gespeichert ist.
     * Damit es keine Leerstellen in dem Array gibt, werden alle dahinter stehenden Notizen jeweils einen Platz nach vorne verschoben.
     * @param index Index der zu löschenden Notiz
     * @return true, falls das Note-Objekt erfolgreich gelöscht wurde; false sonst
     */
    public boolean removeNote(int index) {
        //COMPLETE: Implementiere die Methode
        if (notes[index] != null){
            notes[index] = null;
            noteCount--;
            ArrayHelper.arrayCompress(notes);
            return true;
        }
        return false;
    }

    //Getter und Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Note[] getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", notes=" + Arrays.toString(notes) +
                ", noteCount=" + noteCount +
                '}';
    }
}
