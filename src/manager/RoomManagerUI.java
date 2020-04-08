package manager;

import javax.swing.*;
import java.awt.*;
import sharedui.TableSearchPanel;

public class RoomManagerUI extends JPanel {

    private static RoomManagerUI instance;
    private TableSearchPanel searchPanel;

    private RoomManagerUI(){
        //Drawing component implementation goes here
        setLayout(new FlowLayout());

        initUI();

        //Add components to UI
        add(searchPanel);


    }

    public static synchronized RoomManagerUI getInstance(){
         if(instance == null){
             instance = new RoomManagerUI();
         }
         return instance;
    }

    private void initUI() {
        if (searchPanel == null) {
            searchPanel = new TableSearchPanel();
        }
    }





}
