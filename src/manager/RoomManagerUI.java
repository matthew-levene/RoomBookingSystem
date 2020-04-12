package manager;

import javax.swing.*;
import java.awt.*;

import shared.ui.TableDisplayPanel;
import shared.ui.TableSearchPanel;

public class RoomManagerUI extends JPanel {

    private static RoomManagerUI instance;
    private TableSearchPanel searchPanel;
    private TableDisplayPanel tableDisplayPanel;
    private RoomActionPanel roomActionPanel;

    private RoomController roomController;

    private RoomManagerUI(){
        //set the layout and the border
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));

        //Initialise UI and controller objects
        initUI();
        //Adds the room manager user interface panels to this class
        addUIPanels();

         //Listens out for button click event on the RoomActionPanel
        roomActionPanel.setActionListener(event -> roomController.processRoomAction(roomActionPanel, event));

        //Listens out for button click event on the TableSearchPanel
        searchPanel.setActionListener(() -> roomController.findRoom());

    }

    public static synchronized RoomManagerUI getInstance(){
         if(instance == null){
             instance = new RoomManagerUI();
         }
         return instance;
    }

    private void initUI() {
         searchPanel = new TableSearchPanel();
         tableDisplayPanel = new TableDisplayPanel();
         roomActionPanel = new RoomActionPanel();
         roomController = new RoomController(this);
    }

    private void addUIPanels(){
        //Add search panel to UI
        add(searchPanel);
        //Add table display panel to UI
        add(tableDisplayPanel);
        //Add room actions panel to UI
        add(roomActionPanel);
    }

    public TableDisplayPanel getTableDisplayPanel(){
        return tableDisplayPanel;
    }
    public TableSearchPanel getTableSearchPanel(){ return searchPanel; }
}
