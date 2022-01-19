package com.anas.code.followers;

import com.anas.code.files.StorgeManger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.ArrayList;

public class Utilities {
    public static ArrayList<String> scanCurrentFollowers(GitHub github) {
        ArrayList<String> currentFollowers = new ArrayList<>();
        try {
            github.getMyself().getFollowers().forEach(follower -> {
                try {
                    currentFollowers.add(follower.getName() + " - @" + follower.getLogin());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentFollowers;
    }

    public static ArrayList<String> scanPreviousFollowers() {
        return StorgeManger.getInstance("store").getData(StorgeManger.FilesKeys.Followers);
    }

    public static void storeCurrentFollowers(ArrayList<String> currentFollowers) {
        StringBuilder sb = new StringBuilder();
        for (String currentFollower : currentFollowers) {
            sb.append(currentFollower).append("\n");
        }
        try {
            StorgeManger.getInstance("store").writeData(StorgeManger.FilesKeys.Followers, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateHtmlReport(FollowersReport followersReport, GitHub gitHub) throws IOException {
        Document doc = Jsoup.connect("src/main/resources/html/ReportTemplite.html").get();
        Element title = doc.select("#report-title").first();
        title.text(title.text().replace("{USER_NAME}", gitHub.getMyself().getName()));

        doc.select("#follower-status").first().append(
                            "<li>You Have a " + followersReport.getNewFollowersNumber() + "</li>\n" +
                            "<li>You have $NUM_OF_FOLLOWING</li>");

        doc.select("followers-det").first().append(
                "<li>You have a " + followersReport.getNewFollowersNumber() + " new follower to day</li>\n" +
                        "<li>You have $NUM_OF_NEW_FOLLOWING new following to day</li>\n" +
                        "<li>" + followersReport.getUnfollowedNumber() + " un following you to day</li>"
        );

        StringBuilder newFollowers = new StringBuilder();
        for (String newFollower : followersReport.getNewFollowers()) {
            newFollowers.append("<li>").append(newFollower).append("</li>\n");
        }
        doc.select("#new-followers-list").first().append(newFollowers.toString());

        StringBuilder unfollowed = new StringBuilder();
        for (String unfollowedFollower : followersReport.getUnfollowed()) {
            unfollowed.append("<li>").append(unfollowedFollower).append("</li>\n");
        }
        doc.select("#unfollowed-list").first().append(unfollowed.toString());

        return doc.toString();
    }
}
