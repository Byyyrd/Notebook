package view;

import control.NoteController;
import control.UserController;

import javax.swing.*;

public class MainView extends JFrame {

    // Attribute

    // Referenzen
    private JPanel noteView;

    /**
     * Konstruktor
     * @param userController
     * @param noteController
     */
    public MainView(UserController userController, NoteController noteController) {

        this.noteView = new NoteView(userController, noteController).getPanel();

        this.getContentPane().add(noteView);

        this.setLocation(50,50);
        this.setSize(1000,750);
        this.setResizable(false);
        this.setTitle("Mein Eigenes Notizbuch");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
