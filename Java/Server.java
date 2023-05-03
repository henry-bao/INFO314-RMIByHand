import java.net.ServerSocket;

public class Server {
    public static void main (String[] args) {
        try {
            ServerSocket server = new ServerSocket(10314);
            // Read the bytes
            // Deserialize the bytes using ObjectInputStream
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

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