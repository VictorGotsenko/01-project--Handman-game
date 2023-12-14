import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Me on 12.12.2023.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<String> wordsDictionary4game = new ArrayList<>();
        Set<Character> chArrayWrongChars = new LinkedHashSet<>();       // массив для неправ.введённых букв
      //  inviteGame();
   //     System.out.println("Hello!");

        wordsDictionary4game= readWordsFromFile();
       String strGuessWord= guessTheWord(wordsDictionary4game);
        System.out.println(strGuessWord);



        // offerGame
        // gamingLoop

    }

    public static ArrayList<String> readWordsFromFile () {
        // инициализация переменных
        int iCountLines = 0;
        int iLenghtWord = 5;
        String sWorkDir = "src";
        String sWordsDictionary = "WordsDictionary.txt";

        char chTmp;
        String sTmpLine="а"; // temp var
        ArrayList<String> wordsDictionary4game = new ArrayList<>();
        try {
            File file = new File(String.valueOf(Paths.get(sWorkDir, sWordsDictionary).toFile()));
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
 labelOne:  while ((sTmpLine = reader.readLine()) != null) {           // считываем строки в цикле
                sTmpLine= sTmpLine.toLowerCase();
                if ( sTmpLine.length() < iLenghtWord ) continue;     // обработка слова (менее 5 букв отбросить)

                for (int i = 0; i < sTmpLine.length(); i++) {       // проверка - слово содержит только русские буквы ?
                    chTmp = sTmpLine.charAt(i);
                    if (!(chTmp >= 'а' && chTmp <= 'я')) {
                        if (chTmp == 'ё') { continue; }
                        else {                                       //слово с символами не входящими в русский алфавит отбрасывать
                            System.out.println("Слово: " + sTmpLine + " содержит недопустимые символы и было отброшено ");
                            continue labelOne;
                        }
                    }
                }
                wordsDictionary4game.add(sTmpLine);
            //    System.out.println(sTmpLine);
            }
        reader.close();
        fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // обработка файла с малым количеством слов < 5
        if (wordsDictionary4game.size() <= 5) {                     // обработка файла с малым количеством слов < 5
            System.out.println("Файла словаря" + sWordsDictionary + " было загружено менее 6 слов. Для работы программы необходимо больше слов");
            System.out.println("Работа программы завершена");
            System.exit(0);
        }
        return wordsDictionary4game;
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
    public static String guessTheWord(ArrayList<String> wordsDictionary4game) {
       /*
        List<Character> chArrayGuessTheWord = new ArrayList<>();        // массив для загаданного слова
        List<Character> chArrayRresult = new ArrayList<>();             // массив для отгаданных букв
       */
        Random numRnd = new Random();
        int iNumRnd = numRnd.nextInt(wordsDictionary4game.size());      // random int
        String sGuessTheWord = wordsDictionary4game.get(iNumRnd);

       /*// chArrayGuessTheWord = sTmp.toCharArray();
       // String sGuessTheWord = GuessTheWord.RndWord(iNumLine);                // Загадать слово
        char[] cGuessTheWord = sGuessTheWord.toCharArray();
        for (char c : cGuessTheWord) {                                       // слово в коллекцию букв
            chArrayGuessTheWord.add(c);
            chArrayRresult.add('_');                                         // коллекцию для отгаданных букв заполнить _ подчёркиванием
        }
       // return chArrayGuessTheWord, chArrayRresult;
*/
        return sGuessTheWord;
    }



}   //end Main
