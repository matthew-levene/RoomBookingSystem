package manager;

import javax.swing.*;
import java.awt.*;

import sharedui.TableDisplayPanel;
import sharedui.TableSearchPanel;

public class RoomManagerUI extends JPanel {

    private static RoomManagerUI instance;
    private TableSearchPanel searchPanel;
    private TableDisplayPanel tableDisplayPanel;
    private RoomActionPanel roomActionPanel;

    private RoomManagerUI(){
        //Drawing component implementation goes here
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));

        //Initialise objects
        initUI();

        //Add search panel to UI
        add(searchPanel);
        //Add table display panel to UI
        add(tableDisplayPanel);
        //Add room actions panel to UI
        add(roomActionPanel);

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
        if(roomActionPanel == null){ roomActionPanel = new RoomActionPanel(); }

    }





}
