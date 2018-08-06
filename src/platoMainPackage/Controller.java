/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platoMainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * FXML Controller class
 *
 * @author Valentin
 */
public class Controller implements Initializable {
//<editor-fold defaultstate="collapsed" desc="Internationalizing">

    @FXML
    private MenuItem menuItemGerman; //[internationalizing]
    @FXML
    private MenuItem menuItemEnglish; //[internationalizing]
    @FXML
    private MenuItem menuItemCreateRoom; //[internationalizing]
    @FXML
    private MenuItem menuItemShowAllUsers; //[internationalizing]
    @FXML
    private Menu menuSettings; //[internationalizing]
    @FXML
    private MenuItem menuItemLogin; //[internationalizing]
    @FXML
    private MenuItem menuItemDisconnect; //[internationalizing]
    @FXML
    private Label labelShowUsers; //[internationalizing]
    @FXML
    private Label labelShowRooms; //[internationalizing]
    private Button buttonSend; //[internationalizing]
    @FXML
    private Button buttonPublicChat; //[internationalizing]
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="JavaFX items">
    @FXML
    private ListView<String> listView; //Displaying Messages
    public ObservableList<String> observeListView;
    @FXML
    private ChoiceBox<String> choiceBoxShowUsers; //Shows all available users
    public ObservableList<String> observeChoiceBoxShowUsers;
    @FXML
    private ChoiceBox<String> choiceBoxShowRooms; //Shows all available rooms
    public ObservableList<String> observeChoiceBoxShowRooms;
    @FXML
    private TextField textFieldText; // Input for text
    @FXML
    private Label labelShowCurrentChat; //Shows current chat

    private Locale locale;

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Server items">
    private boolean connected; //true if connected to server
    private Socket echoSocket; //THIS socket
    private PrintWriter out; //Writer to server
    private BufferedReader in; //Server reader
    private String username; //THIS username

    private ReadingThread client;
//</editor-fold>    
    @FXML
    private Menu menuChat; //??

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        observeChoiceBoxShowRooms = FXCollections.observableArrayList();
        observeChoiceBoxShowUsers = FXCollections.observableArrayList();
        observeListView = FXCollections.observableArrayList();
        listView.setItems(observeListView);
        choiceBoxShowRooms.setItems(observeChoiceBoxShowRooms);
        choiceBoxShowUsers.setItems(observeChoiceBoxShowUsers);
        labelShowCurrentChat.setText("Public Chat");
        connected = false;
        locale = Locale.ENGLISH;
    }

    @FXML
    private void handleMenuItemCreateRoom(ActionEvent event)
    {
        if (connected)
        {

        } else
        {
            showAlert("You are currently offline");
        }
    }

    @FXML
    private void handleMenuItemShowAllUsers(ActionEvent event)
    {
        if (connected)
        {

        } else
        {
            showAlert("You are currently offline");
        }
    }

    @FXML
    private void handleMenuItemLogin(ActionEvent event)
    {
        if (!connected)
        {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Text Input Dialog");
            dialog.setHeaderText("Look, a Text Input Dialog");
            dialog.setContentText("Please enter your name:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name ->
            {
                try
                {
                    echoSocket = new Socket("localhost", 12345);
                    out = new PrintWriter(echoSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                } catch (IOException ex)
                {
                    System.err.println("Exception at Creatin Socket in Controller : 108");
                }
                out.println("<login=" + name + ">");
                username = result.get();
                connected = true;

                client = new ReadingThread(in, username);
                Thread runningThread = new Thread(client);
                runningThread.start();
            });
        } else
        {
            showAlert("You are already connected");
        }
        
    }

    @FXML
    private void handleMenuItemDisconnect(ActionEvent event)
    {
        if (connected)
        {
            connected = false;
            out.println("<logout>");
            try
            {
                echoSocket.close();
            } catch (IOException ex)
            {
                System.err.println("Error at closing socket in Controller : 176");
            }
            client.setRunning(false);
            Platform.exit();
            out.close();
        } else
        {
            showAlert("You are currently offline");
        }
    }

    @FXML
    private void handleButtonPublicChat(ActionEvent event)
    {
        if (connected)
        {

        } else
        {
            showAlert("You are currently offline");
        }
    }

    private void showAlert(String message)
    {
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle(message);
        a.setHeaderText(message);
        a.setContentText(message);
        a.showAndWait();

    }

    public ObservableList<String> getObserveListView()
    {
        return observeListView;
    }

    @FXML
    private void handleMenuItemGerman(ActionEvent event)
    {
        locale = Locale.GERMAN;
        changeLanguage();
    }

    @FXML
    private void handleMenuItemEnglish(ActionEvent event)
    {
        locale = Locale.ENGLISH;
        changeLanguage();
    }

    private void changeLanguage()
    {
        ResourceBundle bundle = ResourceBundle.getBundle("languages.MessageBundle", this.locale);
        buttonPublicChat.setText(bundle.getString("buttonPublicChat"));
        buttonSend.setText(bundle.getString("buttonSend"));
        labelShowRooms.setText(bundle.getString("labelShowRooms"));
        labelShowUsers.setText(bundle.getString("labelShowUsers"));
        menuItemCreateRoom.setText(bundle.getString("menuItemCreateRoom"));
        menuItemDisconnect.setText(bundle.getString("menuItemDisconnect"));
        menuItemLogin.setText(bundle.getString("menuItemLogin"));
        menuItemShowAllUsers.setText(bundle.getString("menuItemShowAllUsers"));
        menuSettings.setText(bundle.getString("menuSettings"));

    }

    public PrintWriter getOut()
    {
        return out;
    }

    @FXML
    private void handleSendMessage(KeyEvent event)
    {
        if (event.getCode().equals(KeyCode.ENTER))
        {
            if (connected)
            {
                String valueRooms = choiceBoxShowRooms.getValue();
                String valueUsers = choiceBoxShowUsers.getValue();
                String text = textFieldText.getText();
                if (text != null)
                {
                    if (valueRooms != null)
                    {
                        return;
                    }
                    if (valueUsers != null)
                    {
                        return;
                    }
                    out.println(text);
                } else
                {
                    showAlert("No Text");
                }
                textFieldText.setText("");
            } else
            {
                showAlert("You are currently offline");
            }
        }
    }
}
