package clerk;

import shared.ui.TableDisplayPanel;
import shared.ui.TableSearchPanel;

import javax.swing.*;
import java.awt.*;

public class BookingClerkUI extends JPanel{

    private TableDisplayPanel tableDisplayPanel;
    private TableSearchPanel tableSearchPanel;
    private BookingActionPanel bookingActionPanel;

    private BookingController bookingController;

    public BookingClerkUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));

        //Initialise UI and controller objects
        initUI();
        //Adds the booking clerk ui panels to this class
        addUIPanels();


        //Listens out for button click event on the BookingActionPanel
        bookingActionPanel.setBookingActionListener(
                event -> bookingController.processBookingAction(event));

        //Listens out for button click event on the TableSearchPanel
        tableSearchPanel.setActionListener(() -> bookingController.findRoom());

    }

    private void initUI(){
        tableDisplayPanel = new TableDisplayPanel();
        tableSearchPanel = new TableSearchPanel();
        bookingActionPanel = new BookingActionPanel();
        bookingController = new BookingController(this);
    }

    private void addUIPanels(){
        add(tableDisplayPanel);
        add(tableSearchPanel);
        add(bookingActionPanel);
    }

    public TableDisplayPanel getTableDisplayPanel(){
        return tableDisplayPanel;
    }

    public TableSearchPanel getTableSearchPanel(){
        return tableSearchPanel;
    }

    public BookingActionPanel getBookingActionPanel(){ return bookingActionPanel; }
}
