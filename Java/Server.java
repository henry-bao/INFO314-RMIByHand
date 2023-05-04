import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(Client.PORT)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                    // Receive the remote method request from the client
                    RemoteMethod remoteMethod = (RemoteMethod) in.readObject();
                    String methodName = remoteMethod.getMethodName();
                    Object[] methodArgs = remoteMethod.getArgs();
                    Object result;

                    // Execute the requested method and store the result
                    try {
                        switch (methodName) {
                            case "add":
                                result = add((int) methodArgs[0], (int) methodArgs[1]);
                                break;
                            case "divide":
                                result = divide((int) methodArgs[0], (int) methodArgs[1]);
                                break;
                            case "echo":
                                result = echo((String) methodArgs[0]);
                                break;
                            default:
                                throw new NoSuchMethodException("Method not found: " + methodName);
                        }
                    } catch (Throwable t) {
                        // If an exception occurs, store it as the result
                        result = t;
                    }
                    
                    // Send the result (or exception) back to the client
                    out.writeObject(result);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) { 
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}