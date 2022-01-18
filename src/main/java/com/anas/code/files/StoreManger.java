package com.anas.code.files;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public class StoreManger {
    private final String baseStorePath;
    private final Logger LOGGER = Logger.getLogger(StoreManger.class.getName());

    // Singleton pattern
    private static StoreManger instance = null;

    private StoreManger(String baseStorePath) {
        this.baseStorePath = baseStorePath;
    }

    public static StoreManger getInstance(String baseStorePath) {
        if (instance == null) {
            instance = new StoreManger(baseStorePath);
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

    public void writeData(FilesKeys key, String data) {
        // Open the file
        try {
            File fileReader = new File(key.name() + ".data");

            // Create the file if it doesn't exist
            if (!fileReader.exists()) {
                fileReader.createNewFile();
            }

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
