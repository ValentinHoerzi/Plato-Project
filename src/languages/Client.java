package languages;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package chatpackage;
//
//import mainChatpackage.ClientThread;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.Scanner;
//
///**
// *
// * @author Valentin
// * 
// * NOT NECCESSARY FOR THIS PROJECT
// */
//public class Client {
//
//    private final static Scanner SCANNER = new Scanner(System.in);
//    private static ClientThread client;
//    private static String username;
//
//    public static void main(String[] args)
//    {
//        System.out.println("*~*~Welcome to Vali's Chatroom~*~*");
//        System.out.println("   -Please enter a Nickname-");
//        System.out.print("-> ");
//        username = SCANNER.nextLine();
//        try (
//                final Socket echoSocket = new Socket("localhost", 12345);
//                final PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
//                final BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));)
//        {
//            ClientThread c = new ClientThread(in);
//            Thread t = new Thread(c);
//            t.start();
//
//            out.println("<login=" + username + ">");
//            System.out.println("~~You are now logged in as " + username + "~~");
//            System.out.println("++type \"help\" to see all available commands++");
//            System.out.println("");
//            System.out.println("");
//            String string = SCANNER.nextLine();
//            while (true)
//            {
//                if (string.toUpperCase().equals("HELP"))
//                {
//                    showCommands();
//                } else if (string.startsWith("#"))
//                {
//                    if (commands(string, out))
//                    {
//                        return;
//                    }
//                } else
//                {
//                    out.println(string);
//                }
//                string = SCANNER.nextLine();
//            }
//        } catch (Exception e)
//        {
//        }
//    }
//
//    public static boolean commands(String input, PrintWriter out)
//    {
//        if (input.startsWith("#clients")) //geht
//        {
//            out.println("<getClients>");
//        } else if (input.startsWith("#join"))
//        {
//            out.println("<join="+input.split(" ")[1]+">"+username);
//        } else if (input.startsWith("#leave"))
//        {
//            out.println("<leave>");
//        } else if (input.startsWith("#rooms"))
//        {
//            out.println("<getRooms>"+username);
//        } else if (input.startsWith("#logout")) //geht
//        {
//            out.println("<logout>");
////            client.setRun(false);
//        } else if (input.startsWith("#msg")) //geht
//        {
//            System.out.println("Messege to " + input.split(" ")[1]);
//            System.out.print("-> ");
//            String message = SCANNER.nextLine();
//            out.println("<send=" + input.split(" ")[1] + ">" + message+";"+username);
//        } else{
//            System.out.println("Invalid Command");
//        }
//        return false;
//    }
//
//    public static void showCommands()
//    {
//        System.out.println("#msg [nickname] .. private messages the person");
//        System.out.println("#join [roomname] .. joins a private room");
//        System.out.println("#leave .. leave private room if connected");
//        System.out.println("#clients .. shows all Clients connected to the server");
//        System.out.println("#rooms .. shows all private rooms");
//        System.out.println("#logout .. disconnects you from the server");
//    }
//}
