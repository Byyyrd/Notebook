import Helper.ArrayHelper;
import control.NoteController;
import control.UserController;
import model.User;
import view.MainView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

public class MainProgramm {

    public static void main(String[] args) {
        UserController userController = new UserController();
        NoteController noteController = new NoteController();
        MainView mainView = new MainView(userController, noteController);
        mainView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                userController.saveUserData();
                System.out.println("Hehawhehawe");
            }

        });
    }
}

