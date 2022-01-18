package com.anas.code.files;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public class StorgeManger {
    private final String baseStorePath;
    private final Logger LOGGER = Logger.getLogger(StorgeManger.class.getName());

    // Singleton pattern
    private static StorgeManger instance = null;

    private StorgeManger(String baseStorePath) {
        this.baseStorePath = baseStorePath;
    }

    public static StorgeManger getInstance(String baseStorePath) {
        if (instance == null) {
            instance = new StorgeManger(baseStorePath);
        }
        return instance;
    }
    
    public enum FilesKeys {
        Followers,
        Following,
        Repositories,
        Projects,
    }
    
    public ArrayList<String> getData(FilesKeys key) {
        ArrayList<String> data = new ArrayList<>();
        // Open the file
        try {
            FileReader fileReader = new FileReader(baseStorePath + "/" + key.name() + ".data");

            // Read the file line by line with BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }

            // Close the file
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.INFO, "File not found: " + baseStorePath + "/" + key.name() + ".data");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeData(FilesKeys key, String data) throws IOException {
        File baseFile = new File(baseStorePath);
        // Create the base directory
        if (!baseFile.exists()) {
            if(!baseFile.mkdir()) {
                throw new IOException("Can't create the base directory: " + baseStorePath);
            }
        }
        // Open the file
        try {
            File fileReader = new File(baseFile.getPath() + "/" +  key.name() + ".data");

            FileWriter fileWriter = new FileWriter(fileReader, false);
            // Write data to the file
            fileWriter.write(data);

            // Close the file
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
