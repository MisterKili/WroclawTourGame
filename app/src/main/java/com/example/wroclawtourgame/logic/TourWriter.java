package com.example.wroclawtourgame.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class TourWriter {

    public void setFirstNotVisitedPointAsVisited(File tourFile) {
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(tourFile));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            boolean replaced = false;

            while ((line = file.readLine()) != null) {
                if (line.contains("<answered>false</answered>") && !replaced) {
                    replaced = true;
                    line = "        <answered>true</answered>";
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(tourFile);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
