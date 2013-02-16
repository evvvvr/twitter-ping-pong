package twitter_ping_pong.ping;

import twitter4j.*;

import java.util.*;

public class Program {
    private static final String EXIT_COMMAND = "exit";
    private static final String PING_COMMAND = "ping";

    public static void main(String[] args) {
        System.out.printf("Commands:\n");
        System.out.printf("%s - to ping\n", PING_COMMAND);
        System.out.printf("%s - to exit\n", EXIT_COMMAND);

        Scanner scanner = new Scanner(System.in);
        String command;

        Twitter twitter = new TwitterFactory().getInstance();

        boolean exit = false;
        do {
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            if (command.equals(EXIT_COMMAND)) {
                exit = true;
            } else {
                try {
                    twitter.updateStatus("ping");
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
            }
        } while (!exit);
    }
}
