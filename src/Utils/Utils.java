package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

    public static int parseInt(String number){

        int result;
         try {
         result = Integer.parseInt(number);
         }catch (NumberFormatException e) {
         e.printStackTrace();
         return 0;
         }
        return result;
    }

    public static String loadFileAsString(String path){

        StringBuilder result = new StringBuilder();
        String line;
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(path));

            while ( (line = bReader.readLine()) != null) result.append(line + "\n");

            bReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return result.toString();
    }
}
