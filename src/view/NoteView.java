package view;

import control.NoteController;
import control.UserController;
import model.Note;
import model.QuoteNote;
import model.User;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class NoteView {
    private JPanel mainPanel;
    private JComboBox<String> userSelection;

    private UserController userController;
    private NoteController noteController;

    //Textfelder: Hier werden die Eingaben der Verwender getätigt und von der GUI ausgelesen.
    private JTextField newUserName;
    private JTextField toDoTitle;
    private JTextField toDoContent;
    private JTextField quoteTitle;
    private JTextField quoteContent;
    private JTextField quoteSource;
    private JTextField invertToDoFlag;
    private JTextField removeNoteIndex;
    private JTextPane noteDisplay;

    //Buttons: Erst beim Drücken eines Knopfes wird ein Ereignis ausgelöst
    private JButton createNewUser;
    private JButton confirmChoice;
    private JButton addToDo;
    private JButton addQuote;
    private JButton invertToDo;
    private JButton removeNote;


    public NoteView(UserController userController, NoteController noteController){
        this.userController=userController;
        this.noteController=noteController;
        createButtons();
        for (User user : userController.getAllUsers()) {
            if (user != null)
                userSelection.addItem(user.getUsername());
        }
        updateDisplay();
    }

    /**
     * Erzeugt alle notwendigen Buttons und fügt ihnen ihre jeweiligen Aktionen zu.
     */
    public void createButtons(){
        //Button zum Hinzufügen eines neuen User-Objekts
        createNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newUserName.getText() != null) {
                    userController.createUser(newUserName.getText());
                    userSelection.addItem(newUserName.getText());
                }
            }
        });

        //Auswahl aus dem User-Dropdown wird bestätigt.
        confirmChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay();
            }
        });

        //Eine neue ToDoNote wird dem aktuell ausgewählten Nutzer zugeordnet
        addToDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userSelection.getSelectedItem()!=null) {
                    String username = userSelection.getSelectedItem().toString();
                    Note note = noteController.createToDoNote(toDoTitle.getText(), toDoContent.getText());

                    userController.addNoteToUser(username, note);
                    updateDisplay();
                }
            }
        });

        //Eine neue QuoteNote wird dem aktuell ausgewählten Nutzer zugeordnet
        addQuote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //COMPLETE: Implementiere das Verhalten des addQuote-Buttons
                if(userSelection.getSelectedItem()!=null) {
                    String username = userSelection.getSelectedItem().toString();
                    Note note = noteController.createQuoteNote(quoteTitle.getText(),quoteContent.getText(),quoteSource.getText());

                    userController.addNoteToUser(username, note);
                    updateDisplay();
                }
            }
        });

        //Eine ToDoNote des aktuell ausgewählten Nutzers wird als beendet oder nicht beendet markiert
        invertToDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //COMPLETE: Implementiere das Verhalten des invertToDo-Buttons
                if(userSelection.getSelectedItem()!=null) {
                    String username = userSelection.getSelectedItem().toString();
                    User user = userController.getUserByUsername(username);
                    try {
                        int toDoIndex = Integer.parseInt(invertToDoFlag.getText());

                        noteController.invertToDoNote(user,toDoIndex);
                        updateDisplay();
                    }catch (NumberFormatException ignored){
                        JOptionPane.showMessageDialog(getPanel(),"NUH UH Only Strings \u2654 ");
                    }
                }
            }
        });

        //Eine Notiz wird des aktuell ausgewählten Nutzers wird gelöscht
        removeNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //COMPLETE: Implementiere das Verhalten des removeNote-Buttons
                if(userSelection.getSelectedItem()!=null) {
                    String username = userSelection.getSelectedItem().toString();
                    try {
                        int toDoIndex = Integer.parseInt(removeNoteIndex.getText());

                        userController.deleteNoteFromUser(username,toDoIndex);
                        updateDisplay();
                    }catch (NumberFormatException ignored){
                        JOptionPane.showMessageDialog(getPanel(),"NUH UH Only Strings \u2654 ");
                    }
                }
            }
        });
    }

    //Erzeugt die Ausgabe aller Notizen eines Nutzers
    private void updateDisplay(){
        noteDisplay.setText("");
        if(userSelection.getSelectedItem()!=null) {
            String username= userSelection.getSelectedItem().toString();
            Note[] notes=userController.getUserNotes(username);
            for(int i=0; i<notes.length; i++){
                if(notes[i]!=null){
                    String newString=noteController.createText(notes[i]);
                    noteDisplay.setText(noteDisplay.getText()+i+". "+newString+"\n");
                }
            }
        }
    }

    public JPanel getPanel(){
        return mainPanel;
    }
}
