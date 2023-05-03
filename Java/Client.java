import java.io.*;
import java.net.*;


public class Client {
    private static final String HOST = "localhost";

    /**
     * This method name and parameters must remain as-is
     */
    public static int add(int lhs, int rhs) {
        return (int) callRemoteMethod("add", lhs, rhs);
    }

    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) {
        return (int) callRemoteMethod("divide", num, denom);
    }

    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        return (String) callRemoteMethod("echo", message);
    }

    private static Object callRemoteMethod(String methodName, Object... args) {
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
    
            out.writeObject(new RemoteMethod(methodName, args));
            Object result = in.readObject();
    
            if (result instanceof Throwable) {
                throw (Throwable) result;
            }
            return result;
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            return null;
        }
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) {
        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");

        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X");

        try {
            divide(1, 0);
            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello").equals("You said Hello!"))
            System.out.print(".");
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}