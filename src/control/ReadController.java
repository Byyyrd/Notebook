package control;

import model.QuoteNote;
import model.ToDoNote;
import model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ReadController {
    //COMPLETE Implement readUserData
    public static boolean readUserData(UserController userController) {
        try {
            File folder = new File(System.getProperty("user.dir") + "\\UserData\\");
            if(folder.list() == null){
                return true;
            }
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                String username = file.getName().replace(".txt", "");
                userController.createUser(username);
                User user = userController.getUserByUsername(username);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String content = "";
                String title = "";
                String completed = "";
                String source = "";
                String line;
                while ((line = reader.readLine()) != null) {
                    char[] chars = line.toCharArray();
                    if (chars[0] == 'c') {
                        chars[0] = ' ';
                        content = String.valueOf(chars).strip();
                    }
                    if (chars[0] == 't') {
                        chars[0] = ' ';
                        title = String.valueOf(chars).strip();
                    }
                    if (chars[0] == 's') {
                        chars[0] = ' ';
                        source = String.valueOf(chars).strip();
                    }
                    if (chars[0] == 'f') {
                        chars[0] = ' ';
                        completed = String.valueOf(chars).strip();
                    }
                    if (chars[0] == '}') {
                        if (!completed.equals("")) {
                            ToDoNote note = new ToDoNote(title, content);
                            if (completed.equals("Ja") && !note.getCompleted()) {
                                note.invertCompleted();
                            }
                            user.addNote(note);
                        } else {
                            QuoteNote note = new QuoteNote(title, content,source);
                            user.addNote(note);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
