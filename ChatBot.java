import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class chatbot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Java Console Chatbot!");
        System.out.println("You can ask me to open applications or search the web.");
        System.out.println("Type 'exit' to quit the chatbot.");
        
        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            
            handleCommand(input);
        }
        
        scanner.close();
    }

    private static void handleCommand(String input) {
        if (input.startsWith("open ")) {
            openApplication(input.substring(5));
        } else if (input.startsWith("search for ")) {
            searchWeb(input.substring(11));
        } else {
            System.out.println("Sorry, I didn't understand that command.");
        }
    }

    private static void openApplication(String appName) {
        try {
            switch (appName.toLowerCase()) {
                case "notepad":
                    Runtime.getRuntime().exec("notepad");
                    break;
                case "calculator":
                    Runtime.getRuntime().exec("calc");
                    break;
                case "browser":
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().browse(new URI("http://www.google.com"));
                    }
                    break;
                default:
                    System.out.println("Sorry, I can't open " + appName);
                    break;
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("An error occurred while trying to open " + appName);
            e.printStackTrace();
        }
    }

    private static void searchWeb(String query) {
        try {
            if (Desktop.isDesktopSupported()) {
                String url = "https://www.google.com/search?q=" + query.replace(" ", "+");
                Desktop.getDesktop().browse(new URI(url));
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("An error occurred while trying to search the web.");
            e.printStackTrace();
        }
    }
}
