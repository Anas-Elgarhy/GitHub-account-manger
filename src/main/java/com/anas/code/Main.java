package com.anas.code;

import com.anas.code.emailSender.EmailSender;
import com.anas.code.emailSender.email.Email;
import com.anas.code.emailSender.email.EmailDetails;
import com.anas.code.emailSender.user.User;
import com.anas.code.followers.FollowersReport;
import com.anas.code.followers.FollowersReportGenerator;
import org.kohsuke.github.GitHub;

import java.io.IOException;

public class Main {

    /**
     * @param args the command line arguments.<br>
     *      Syntax: GitHubToken FollowBack(true/false) userEmail userEmailPassword targetEmail emailHost <br>
     *      Example: ... 2544t4 true anas.elgarhy.dev@gmail.com a76an anas@email.com smtp.gmail.com
     */
    public static void main(String ... args) {
        try {
            // Conect to GitHub
            GitHub gitHub = GitHub.connectUsingOAuth(args[0]);
            // TODO: Check if user is following back

            // Create Report generator
            FollowersReportGenerator reportGenerator = new FollowersReportGenerator(gitHub);
            // Generate report
            reportGenerator.generate();

            // Print report (for debugging)
            System.out.println(((FollowersReport)reportGenerator.getReport()).getCurrentFollowers());

            // Store report
            reportGenerator.storeCurrentFollowers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
