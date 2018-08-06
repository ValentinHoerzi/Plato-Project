/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platoMainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Platform;

/**
 * @author Valentin
 */
public class ReadingThread implements Runnable {

    private BufferedReader in;
    private List<String> names;
    private String username;
    private boolean running = true;
    Controller c = new Controller();

    public ReadingThread(BufferedReader in, String username)
    {
        this.username = username;
        this.in = in;
        names = new ArrayList<>();

    }

    @Override
    public void run()
    {
        String string = null;
        try
        {
            string = in.readLine();
        } catch (IOException ex)
        {
            System.err.println("Exception at Reading line in ClientThread : 43");
        }
        while (running)
        {
            if (string.startsWith("["))
            {
                String replace = string.replace("[", "");
                String replace1 = replace.replace("]", "");
                String[] split = replace1.split(",");
                names.clear();
                names.addAll(Arrays.asList(split));
                Platform.runLater(() -> Chat.getController().observeChoiceBoxShowUsers.setAll(names));
            } else
            {
                final String s = string;
                Platform.runLater(() -> Chat.getController().getObserveListView().add(s));
            }

            try
            {
                string = in.readLine();
            } catch (IOException ex)
            {
                System.err.println("Exception at Reading line in ClientThread : 66");
            }
        }
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
}
