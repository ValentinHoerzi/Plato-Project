/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platoMainPackage;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Valentin
 */
public class Chat extends Application {
    //Idee: Block user?
    //Idee: Uhrzeit i.wo anzeigen
    private static Controller controller;

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);

        //Sets PLATO as Application Titel
        stage.setTitle("PLATO");
        //Adds icon as Programmicon
        Image image = new Image("/Images/Cicero.PNG");
        stage.getIcons().add(image);
        //Exits Programm when Window closed
        stage.setOnCloseRequest((WindowEvent e) ->
        {
            Platform.exit();
            System.exit(0);
        });
        //Adds stylesheet to Application
        scene.getStylesheets().add("D:/2. HTL/Plato/Plato/src/platoMainPackage/style.css");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static Controller getController()
    {
        return controller;
    }
}
