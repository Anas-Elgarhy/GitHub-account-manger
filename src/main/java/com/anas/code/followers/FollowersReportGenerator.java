package com.anas.code.followers;

import com.anas.code.Generator;
import com.anas.code.Report;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public class FollowersReportGenerator implements Generator {
    private FollowersReport followersReport;
    private final GitHub github;
    private final Logger LOGGER = Logger.getLogger(FollowersReportGenerator.class.getName());

    public FollowersReportGenerator(GitHub github) {
        this.github = github;
    }

    @Override
    public void generate() {
        ArrayList<String> currentFollowers = Utilities.scanCurrentFollowers(github); // get current followers
        ArrayList<String> previousFollowers = Utilities.scanPreviousFollowers(); // get previous followers
        ArrayList<String> currentFollow = Utilities.scanFollowings(github);; // get current followings
        // Create a new report object
        followersReport = new FollowersReport() {
            @Override
            public ArrayList<String> getCurrentFollowers() {
                return currentFollowers;
            }
            @Override
            public ArrayList<String> getPreviousFollowers() {
                return previousFollowers;
            }

            @Override
            public ArrayList<String> getFollowings() {
                return currentFollow;
            }
        };
        LOGGER.info("Report generated successfully");
    }

    public void storeCurrentFollowers() {
        Utilities.storeCurrentFollowers(followersReport.getCurrentFollowers());
    }

    @Override
    public Report getReport() {
        return followersReport;
    }

    public String getHtmlReport() {
        try {
            return Utilities.generateHtmlReport(followersReport, github);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
