package manager;

import javax.swing.*;

public class RoomManagerUI extends JPanel {

    private static RoomManagerUI instance;

    private RoomManagerUI(){
        //Drawing component implementation goes here

    }

    public static synchronized RoomManagerUI getInstance(){
         if(instance == null){
             instance = new RoomManagerUI();
         }
         return instance;
    }


}
