import java.io.*;

/**
 * Created by Me on 12.12.2023.
 */
public class Main {
    public static void main(String[] args) {
        // readWordsfromFile
        // offerGame
        // gamingLoop
        System.out.println("Hello!");
    }

    public static void readWordsFromFile () {
        String sPath;
        try {
            File file = new File(sPath);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }






}   //end Main
