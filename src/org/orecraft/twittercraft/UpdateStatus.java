package org.orecraft.twittercraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public final class UpdateStatus
{
  public static void main(String[] args)
  {
    if (args.length < 1) {
      System.out.println("Usage: java twitter4j.examples.tweets.UpdateStatus [text]");
      System.exit(-1);
    }
    File file = new File("twitter4j.properties");
    Properties prop = new Properties();
    InputStream is = null;
    try {
      if (file.exists()) {
        is = new FileInputStream(file);
        prop.load(is);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    try {
      Twitter twitter = new TwitterFactory().getInstance();
      try {
        twitter.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"));
      }
      catch (IllegalStateException ie)
      {
        if (!twitter.getAuthorization().isEnabled()) {
          System.out.println("OAuth consumer key/secret is not set.");
          System.exit(-1);
        }
      }
      Status status = twitter.updateStatus(args[0]);
      System.out.println("Successfully updated the status to [" + status.getText() + "].");
    }
    catch (TwitterException te) {
      te.printStackTrace();
      System.out.println("Failed to get timeline: " + te.getMessage());
    }
  }
}