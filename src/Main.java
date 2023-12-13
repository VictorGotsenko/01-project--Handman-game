import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Me on 12.12.2023.
 */
public class Main {
    public static void main(String[] args) {

        inviteGame();
        System.out.println("Hello!");

        readWordsFromFile();


        // offerGame
        // gamingLoop

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


    public static void inviteGame() {
        String strngTemp;
        char cKeyControl = 2;
        System.out.println("Игра - Виселица");                                    // начальное приветсвие и предложение игры
        System.out.println("Начать игру нажмите 1 / Для Выхода нажмите 2");
        Scanner cReadFromKeyboard = new Scanner(System.in);
        while (cKeyControl != '1') {
            strngTemp = cReadFromKeyboard.nextLine();
            cKeyControl = strngTemp.charAt(0);
            switch (cKeyControl) {
                case '1':
                    System.out.println();  // "Game"
                    break;
                case '2':
                    System.out.println("Goodbuy !");
                    cReadFromKeyboard.close();
                    System.exit(0);
                    break;
                default:
                    System.out.printf("Error! Please enter 1 or 2 введено %strngTemp", cKeyControl);
                    System.out.println("");
            }
        }
    }




}   //end Main
