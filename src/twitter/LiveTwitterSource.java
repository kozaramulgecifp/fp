package twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Encapsulates the connection to Twitter
 *
 * Terms to include in the returned tweets can be set with setFilterTerms
 *
 * Implements Observable - each received tweet is signalled to all observers
 */
public class LiveTwitterSource extends TwitterSource {
    private TwitterStream twitterStream;
    private StatusListener listener;

    public LiveTwitterSource() {
        initializeTwitterStream();
    }

    protected void sync() {
        FilterQuery filter = new FilterQuery();
        // https://stackoverflow.com/questions/21383345/using-multiple-threads-to-get-data-from-twitter-using-twitter4j
        String[] queriesArray = terms.toArray(new String[0]);
        filter.track(queriesArray);

        System.out.println("Syncing live Twitter stream with " + terms);

        twitterStream.filter(filter);
    }

    private void initializeListener() {
        listener = new StatusAdapter() {
            @Override
            public void onStatus(Status status) {
                // This method is called each time a tweet is delivered by the twitter API
                if (status.getPlace() != null) {
                    handleTweet(status);
                }
           }
        };
    }

    // Create ConfigurationBuilder and pass in necessary credentials to authorize properly, then create TwitterStream.
    private void initializeTwitterStream() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("Pg0D7K2zGj7sfxfeKVnJ0kC6C")
                .setOAuthConsumerSecret("Pg0D7K2zGj7sfxfeKVnJ0kC6C")
                .setOAuthAccessToken("AAAAAAAAAAAAAAAAAAAAADAnSAEAAAAAwg1aFSYjgB32mlJ9oJ5AUzj2Jfs%3D1VGDq5TU3GkLLd4viwfiCgNbEfVWtCxi2VJmWTZoOAzHLEYnej")
                .setOAuthAccessTokenSecret("WsALg2QdlgU1kf2gKX9H0fiStVku3KyDXgcDAtOWtO3yty0P8M");

        // Pass the ConfigurationBuilder in when constructing TwitterStreamFactory.
        twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        initializeListener();
        twitterStream.addListener(listener);
    }
}
