package control;

import model.Note;
import model.QuoteNote;
import model.ToDoNote;
import model.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteController {
    //HEHHEAHHWE BAD CoDe
    public static void saveUserData(UserController userController) {
        try {
            if (userController.getUserCount() != 0) {
                for (User user : userController.getAllUsers()) {
                    if (user != null) {
                        File file = new File(System.getProperty("user.dir") + "\\UserData\\" + user.getUsername() + ".txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        for (Note note : user.getNotes()) {
                            if (note != null) {
                                writer.write("{\n");
                                writer.write("c     " + note.getContent() + "\n");
                                writer.write("t     " + note.getTitle() + "\n");
                                if (note instanceof QuoteNote) {
                                    writer.write("s     " + ((QuoteNote) note).getSource() + "\n");
                                } else if (note instanceof ToDoNote) {
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
}
