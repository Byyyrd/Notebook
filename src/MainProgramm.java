import Helper.ArrayHelper;
import control.NoteController;
import control.UserController;
import model.User;
import view.MainView;

import java.util.Arrays;

public class MainProgramm {

    public static void main(String[] args){
        UserController userController=new UserController();
        NoteController noteController=new NoteController();
        MainView mainView = new MainView(userController, noteController);
        User[] users = new User[]{new User("Bert"),new User("Samuel"),new User("Maxi")};
        System.out.println(Arrays.toString(users));
        users[1] = null;
        System.out.println(Arrays.toString(users));
        ArrayHelper.arrayCompress(users);
        System.out.println(Arrays.toString(users));
    }
}

