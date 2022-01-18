package com.anas.code.followers;

import com.anas.code.files.StoreManger;
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
        return StoreManger.getInstance("store").getData(StoreManger.FilesKeys.Followers);
    }

    public static void storeCurrentFollowers(ArrayList<String> currentFollowers) {
        StringBuilder sb = new StringBuilder();
        for (String currentFollower : currentFollowers) {
            sb.append(currentFollower).append("\n");
        }
        StoreManger.getInstance("store").writeData(StoreManger.FilesKeys.Followers, sb.toString());
    }
}
