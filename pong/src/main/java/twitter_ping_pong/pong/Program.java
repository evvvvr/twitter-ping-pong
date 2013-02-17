package twitter_ping_pong.pong;

import twitter4j.*;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        Twitter twitter = new TwitterFactory().getInstance();

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(new MentionsListener(twitter));

        System.out.println("Listening. Press Enter to exit.");

        twitterStream.user();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        twitterStream.cleanUp();
        twitterStream.shutdown();
    }
}