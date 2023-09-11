package control;

import Helper.ArrayHelper;
import model.Note;
import model.QuoteNote;
import model.ToDoNote;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class UserController {
    private User[] users;
    private int userCount;

    public UserController() {
        //COMPLETE: Implementiere den Konstruktor
        users = new User[10];
        userCount = 0;
    }

    /**
     * Erzeugt ein neues User-Objekt und fügt es an die erste leere Stelle im User-Array ein, falls das Array noch nicht komplett gefüllt ist.
     * Zur besseren Verwaltbarkeit wird zudem der userCount-Zähler aktualisiert.
     * <p>
     * <p>
     * Zusatzaufgabe: Falls das Array vollständig gefüllt ist, so wird das Array verlängert und anschließend wird das User-Objekt einsortiert.
     *
     * @param username Name des neuen Nutzers. Wird dem Konstruktor der Klasse User als Argument übergeben.
     * @return Gibt true zurück, falls der Nutzer angelegt werden konnte, sonst false.
     */
    public boolean createUser(String username) {
        //COMPLETE: Implementiere die Methode

        if (userCount < users.length) {
            users[userCount] = new User(username);
        } else {
            users = ArrayHelper.arrayAdd(User.class, users, new User(username));
        }
        userCount++;
        return true;
    }

    /**
     * Sucht in allen Nutzern denjenigen mit dem angegebenen Nutzernamen
     *
     * @param username Nutzername des gesuchten Nutzers
     * @return Das gefundene User-Objekt; null, wenn das Objekt nicht existiert.
     */
    public User getUserByUsername(String username) {
        //COMPLETE: Implementiere die Methode
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Sucht unter allen Nutzern denjenigen mit dem angegebenen Nutzernamen. Dem gefundenen Nutzer wird das Notizobjekt hinzugefügt.
     *
     * @param username Nutzername des gesuchten Nutzers
     * @param note     Notiz-Objekt, dass dem Nutzer zugeordnet werden soll
     */
    public void addNoteToUser(String username, Note note) {
        //COMPLETE: Implementiere die Methode.
        User user = getUserByUsername(username);
        if (user != null) {
            user.addNote(note);
        }
    }

    /**
     * Sucht unter allen Nutzern denjenigen mit dem angegebenen Nutzernamen. Die Notiz an der angegebenen Stelle wird bei dem Nutzer gelöscht.
     *
     * @param username Nutzername des gesuchten Nutzers
     * @param index    Position des Notiz-Objekts, das gelöscht werden soll
     */
    public void deleteNoteFromUser(String username, int index) {
        User user = getUserByUsername(username);
        if (user != null) {
            user.removeNote(index);
        }
    }

    public Note[] getUserNotes(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            return user.getNotes();
        }
        return null;
    }

    //HEHHEAHHWE BAD CoDe
    public void saveUserData() {
        try {
            if(userCount != 0) {
                for (User user : users) {
                    if (user != null) {
                        File file = new File("H:\\Desktop\\JavaProject\\Notebook_Vorlage\\UserData\\" + user.getUsername() + ".txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        for (Note note : user.getNotes()) {
                            if(note != null){
                                writer.write("{\n");
                                writer.write("c     " + note.getContent() + "\n");
                                writer.write("t     " + note.getTitle() + "\n");
                                if(note instanceof QuoteNote){
                                    writer.write("s     " + ((QuoteNote) note).getSource() + "\n");
                                }else if(note instanceof ToDoNote){
                                    writer.write("f     " + (((ToDoNote) note).getCompleted() ? "Ja" : "Nein") + "\n");
                                }
                                writer.write("}");
                                writer.newLine();
                            }
                        }
                        writer.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //TODO Implement readUserData
    public void readUserData(){
        try {
            File folder = new File("H:\\Desktop\\JavaProject\\Notebook_Vorlage\\UserData\\");
            for (File file: Objects.requireNonNull(folder.listFiles())) {
                String username = file.getName().replace(".txt","");
                createUser(username);
                User user = getUserByUsername(username);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String content;
                while (reader.readLine() != null){
                    String line = reader.readLine();
                    char[] chars = line.toCharArray();
                    if(chars[0] == 'c'){
                        content = line.replace("c","").strip();
                    }
                    if(chars[0] == '}'){

                    }
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
