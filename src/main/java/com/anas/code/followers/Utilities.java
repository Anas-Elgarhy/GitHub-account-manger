package com.anas.code.followers;

import com.anas.code.files.StorgeManger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.kohsuke.github.GitHub;

import java.io.File;
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

    // TODO: Re implement this method
    public static String generateHtmlReport(FollowersReport followersReport, GitHub gitHub) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Followers Report</h2>")
                .append("<h2>Hi ").append(gitHub.getMyself().getName()).append("</h2>")
                .append("<ul>").append("<li>You Have a ").append(followersReport.getCurrentFollowersNumber()).append(" followers</li>")
                .append("<li>You have").append(followersReport.getFollowingNumber()).append(" following</li>")
                .append("</ul>")
                .append("<ul>").append("<li>You have a ").append(followersReport.getNewFollowersNumber()).append(" new follower to day</li>")
                .append("<li>You have $NUM_OF_NEW_FOLLOWING new following to day</li>").append("<li>").append(followersReport.getUnfollowedNumber()).append(" un following you to day</li>")
                .append("</ul>");

        sb.append("<ul>");
        for (String newFollower : followersReport.getNewFollowers()) {
            sb.append("<li>").append(newFollower).append("</li>\n");
        }
        sb.append("</ul>");

        sb.append("<ul>");
        for (String unfollowedFollower : followersReport.getUnfollowed()) {
            sb.append("<li>").append(unfollowedFollower).append("</li>\n");
        }
        sb.append("</ul>");

        return sb.toString();
    }

    public static ArrayList<String> scanFollowings(GitHub github) {
        ArrayList<String> followings = new ArrayList<>();
        try {
            github.getMyself().getFollows().forEach(follower -> {
                try {
                    followings.add(follower.getName() + " - @" + follower.getLogin());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return followings;
    }
}
