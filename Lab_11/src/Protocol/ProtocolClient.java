package Protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProtocolClient {

    private static String ip = "localhost";
    private static int port = 3000;

    private static String run(String command){
        try(Socket socket = new Socket(ip, port)){
            try(PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                    out.println(command);
                    return in.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (IOException e){
            System.out.println("Could not connect to adress: " + ip + " on port: " + port);
        }
        return "false";
    }

    public static void main(String[] args) {
        String ID = "";
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while (!exit) {
            System.out.print("$");
            String in = null;
            try {
                in = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (in.equals("exit")) exit=true;
            else if (in.startsWith("LOGIN")){
                String[] arg = in.split(" ");
                if (arg.length<3) System.out.println("Usage: LOGIN [username] [password]");
                else {
                    String result = run(arg[0] + " " + arg[1] + ";" + arg[1].concat(arg[2]).hashCode());
                    if (result.equals("false")) {
                        System.out.println("Login failed");
                    } else {
                        ID = result;
                        System.out.println("Login successful");
                    }
                }
            }
            else if (in.startsWith("LOGOUT")){
                System.out.println(run("LOGOUT " + ID));
            }
            else if (in.equals("LS")){
                System.out.println(run("LS "+ID));
            }
            else if (in.startsWith("GET")){
                String[] arg = in.split(" ");
                if (arg.length<2) System.out.println("Usage: GET [file]");
                else System.out.println(run("GET "+ ID + " " + arg[1]));
            }
        }

    }
}
