package twitter_ping_pong.pong;

import twitter4j.*;

class MentionsListener extends UserStreamAdapter  {
    private final Twitter twitter;

    public MentionsListener(Twitter twitter) {
        super();

        this.twitter = twitter;
    }

    @Override
    public void onStatus(Status status) {
        User statusAuthor = status.getUser();

        try {
            long ownUserId = twitter.getId();
            boolean isMentioned = isUserMentioned(status);

            if (statusAuthor.getId() != ownUserId && isMentioned) {
                String statusAuthorScreenName = "@" + status.getUser().getScreenName();

                System.out.println("Received status: " + status.getText());
                System.out.println("From: " + statusAuthorScreenName);

                StatusUpdate reply = new StatusUpdate(statusAuthorScreenName + " pong");
                reply.setInReplyToStatusId(status.getId());

                twitter.updateStatus(reply);
            }
        } catch (TwitterException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isUserMentioned(Status status) throws TwitterException {
        boolean isUserMentioned = false;
        long ownUserId = twitter.getId();
        UserMentionEntity[] allMentions = status.getUserMentionEntities();

        for (UserMentionEntity userMention : allMentions) {
            isUserMentioned = userMention.getId() == ownUserId;

            if (isUserMentioned) {
                break;
            }
        }

        return isUserMentioned;
    }
}