import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Me on 12.12.2023.
 */
public class Main {
    public static void main(String[] args) {
       readWordsFromFile();



        // offerGame
        // gamingLoop
        System.out.println("Hello!");
    }

    public static void readWordsFromFile () {
        ArrayList<String> wordsDictionary4game = new ArrayList<>();
        try {
            File file = new File(String.valueOf(Paths.get("src", "WordsDictionary.txt").toFile()));
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                wordsDictionary4game.add(line);
                System.out.println(line);
                // считываем остальные строки в цикле
                line= reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }






}   //end Main
