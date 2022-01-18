package com.anas.code;

import com.anas.code.emailSender.EmailSender;
import com.anas.code.emailSender.email.Email;
import com.anas.code.emailSender.email.EmailDetails;
import com.anas.code.emailSender.message.MessageBody;
import com.anas.code.emailSender.user.User;
import com.anas.code.followers.FollowersReport;
import com.anas.code.followers.FollowersReportGenerator;
import org.kohsuke.github.GitHub;

import java.io.File;
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
            boolean followBack = Boolean.parseBoolean(args[1]);
            // Get user details
            User user = new User(){

                @Override
                public String getEmail() {
                    return args[2];
                }
                @Override
                public String getPassword() {
                    return args[3];
                }
            };
            // Get target email details
            EmailDetails targetEmail = new EmailDetails(){

                @Override
                public String getFrom() {
                    return user.getEmail();
                }
                @Override
                public String getTo() {
                    return args[4];
                }

                @Override
                public String getHost() {
                    return args[5];
                }

                @Override
                public String getSubject() {
                    return "Followers Report - Test2";
                }

                @Override
                public MessageBody getBody() {
                    return new MessageBody("<div><h1>Followers Report2</h1></div>");
                }

                @Override
                public File[] getAttachments() {
                    return null;
                }
            };

            // Create Report generator
            FollowersReportGenerator reportGenerator = new FollowersReportGenerator(gitHub);
            // Generate report
            reportGenerator.generate();

            // Print report (for debugging)
            System.out.println(((FollowersReport)reportGenerator.getReport()).getCurrentFollowers());

            // Store report
            reportGenerator.storeCurrentFollowers();

            // Send report
            EmailSender emailSender = new EmailSender(new Email(targetEmail), user);

            // Send report
            emailSender.send();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
