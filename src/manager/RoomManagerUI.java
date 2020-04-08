package manager;

import javax.swing.*;
import java.awt.*;

import sharedui.TableDisplayPanel;
import sharedui.TableSearchPanel;

public class RoomManagerUI extends JPanel {

    private static RoomManagerUI instance;
    private TableSearchPanel searchPanel;
    private TableDisplayPanel tableDisplayPanel;

    private RoomManagerUI(){
        //Drawing component implementation goes here
        setLayout(new FlowLayout());

        //Initialise objects
        initUI();

        //Add search panel to UI
        add(searchPanel);

        //Add table display panel to UI
        add(tableDisplayPanel);

    }

    public static synchronized RoomManagerUI getInstance(){
         if(instance == null){
             instance = new RoomManagerUI();
         }
         return instance;
    }

    private void initUI() {
        if (searchPanel == null) { searchPanel = new TableSearchPanel(); }
        if (tableDisplayPanel == null) { tableDisplayPanel = new TableDisplayPanel(); }

    }





}
