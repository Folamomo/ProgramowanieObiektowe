package Protocol;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ProtocolServer extends Thread{

    private Map<String, String> passwd= new HashMap<>();
    private int port;
    private IDManager idManager = new IDManager();


    public ProtocolServer(int _port) {
        String username = "admin";
        String password = "admin1";
        port = _port;
        passwd.put(username, Integer.toString(username.concat(password).hashCode()));
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Now listening on port: " + port);
            boolean end = false;
            while (!end) {
                try (Socket clientSocket = serverSocket.accept()) {
                    try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                         BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                        String input = in.readLine();
                        out.println(processRequest(input));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    System.out.println("Accept failed: " + port);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + port);
        }
    }

    private String processRequest(String request){
        String[] args = request.split(" ");
        if (args.length<2) return "false";
        String command = args[0];
        if (command.equals("LOGIN")){
            String[] s2 = args[1].split(";");
            if (s2.length < 2) return "false";
            String login = s2[0];
            String hashedPassword = s2[1];
            String p = passwd.get((login));
            if(p!=null&&p.equals(hashedPassword)){
                return idManager.getId();
            }
        } else if (command.equals("LOGOUT")){
            return Boolean.toString(idManager.removeID(args[1]));
        } else if (command.equals("LS")&&idManager.checkID(args[1])){
            return "";
        } else if (command.equals("GET")&&idManager.checkID(args[1])){
            return "";
        }
        return "false";
    }

}
