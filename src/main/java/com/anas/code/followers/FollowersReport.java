package com.anas.code.followers;

import com.anas.code.Report;
import com.anas.code.ReportType;

import java.util.ArrayList;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public interface FollowersReport extends Report {
    @Override
    public default String getName() {
        return "Followers Report";
    }

    @Override
    public default ReportType getType() {
        return ReportType.FOLLOWERS;
    }

    public ArrayList<String> getCurrentFollowers();

    public default int getCurrentFollowersNumber() {
        if (getCurrentFollowers() == null) {
            return 0;
        }
        return getCurrentFollowers().size();
    }

    public ArrayList<String> getPreviousFollowers();

    public default int getPreviousFollowersNumber() {
        if (getPreviousFollowers() == null) {
            return 0;
        }
        return getCurrentFollowers().size();
    }

    public default ArrayList<String> getNewFollowers() {
        ArrayList<String> newFollowers = new ArrayList<>();
        if (getCurrentFollowers() == null || getPreviousFollowers() == null) {
            return newFollowers;
        }
        for (String currentFollower : getCurrentFollowers()) {
            if (!getPreviousFollowers().contains(currentFollower)) {
                newFollowers.add(currentFollower);
            }
        }
        return newFollowers;
    }

    public default int getNewFollowersNumber() {
        if (getNewFollowers() == null) {
            return 0;
        }
        return getNewFollowers().size();
    }

    public default ArrayList<String> getUnfollowed() {
        ArrayList<String> unfollowed = new ArrayList<>();
        if (getCurrentFollowers() == null || getPreviousFollowers() == null) {
            return unfollowed;
        }
        System.out.println("Start unfollowed");
        for (String previousFollower : getPreviousFollowers()) {
            if (!getCurrentFollowers().contains(previousFollower)) {
                unfollowed.add(previousFollower);
                System.out.println("- " + previousFollower);
            }
        }
        return unfollowed;
    }

    public default int getUnfollowedNumber(){
        if (getUnfollowed() == null) {
            return 0;
        }
        return getUnfollowed().size();
    }

    public ArrayList<String> getFollowings();
    public default int getFollowingNumber() {
        if (getFollowings() == null) {
            return 0;
        }
        return getFollowings().size();
    }
}
