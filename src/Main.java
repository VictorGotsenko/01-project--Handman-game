import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Me on 12.12.2023.
 */
public class Main {
   private static Scanner cReadFromKeyboard = new Scanner(System.in);


    public static void main(String[] args) {
        ArrayList<String> wordsDictionary4game = new ArrayList<>();


        inviteGame();
   //     System.out.println("Hello!");
        wordsDictionary4game= readWordsFromFile();
        String strGuessWord= guessTheWord(wordsDictionary4game);        // Загаданное слово...
        //System.out.println(strGuessWord);
       // printHangman(0);
        gamePlay(strGuessWord);



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

    public static boolean gamePlay(String stGuessWord) {
        List<Character> chArrayGuessTheWord = new ArrayList<>();        // массив для загаданного слова
        List<Character> chArrayRresult = new ArrayList<>();             // массив для отгаданных букв
        Set<Character> chArrayWrongChars = new LinkedHashSet<>();       // массив для неправ.введённых букв
        int iError = 0;
        boolean isCharHere;
        boolean isWin = false;

        char[] cGuessTheWord = stGuessWord.toCharArray();
        for (char c : cGuessTheWord) {                                       // слово в коллекцию букв
            chArrayGuessTheWord.add(c);
            chArrayRresult.add('_');                                         // коллекцию для отгаданных букв заполнить _ подчёркиванием
        }
        System.out.println("Загаданное слово...");
        for (int i = 0; i < chArrayRresult.size(); i++) {                    // вывод коллекции _ _ _ _
            System.out.print(chArrayRresult.get(i) + " ");
        }
        System.out.println();
        /*******************
         Игровой цикл
         *******************/
        printHangman(iError); // печать виселицы
        char cKey;
        while (iError < 6) {                                                // играем до 6 ошибок
            isCharHere = false;
            cKey= getChar();
            System.out.println();
            System.out.println("Загаданное слово...");
            for (int i = 0; i < chArrayGuessTheWord.size(); i++) {          // сравнение посимвольно загаданного слова
                if (cKey == chArrayGuessTheWord.get(i)) {
                    chArrayRresult.set(i, cKey);                            //поместить букву в массив угаданных
                    isCharHere = true;
                }
                System.out.print(chArrayRresult.get(i) + " ");              // напечатать угаданные буквы
            }
            /** варианты:  1 буква в слове есть - поместить в массив угаданных
            *              2 буквы в слове нет - а) буква введена первый раз - зачитываю ошибку
            *                                    б) буква введена повторно - напомнить, что буква уже введена, нет ошибки
            */
            if (isCharHere == false) {
                if ( chArrayWrongChars.add(cKey) ) {
                    iError++;                                              // буквы нет, плюсую ошибку
                } else {
                    System.out.println("\n Такую букву вы уже вводили и ее нет в слове");
                }

            }
            System.out.println("Ошибки (" + iError + "):" + chArrayWrongChars);
            printHangman(iError);
            if (  !chArrayRresult.contains('_') ) {                   // слово отгаданно, если нет символов подчёркивания
                isWin = true;
                break;
            }
        } // end While
        return isWin;
    }


    public static void printHangman(int iError) {
        String sGallow1 = " ____";
        String sGallow2 = " |  |";
//    String sGallow3 = " |  o";
//    String sGallow4 = " | /|\\";
//    String sGallow5 = " | /`"+"\\";
        String sGallow3 = " |  ";
        String sGallow4 = " | ";
        String sGallow5 = " | ";
        String sGallow6 = " | ";
        String sGallow7 = "/|"+"\\";

        ///
        if (iError > 6) {
            System.out.println("Количество ошибок равно "+iError+" печать виселицы невозможна!");
            return;
        }
        switch (iError) {
            case 1:
                sGallow3 = " |  o";
                break;
            case 2:
                sGallow4 = " |  |";
                break;
            case 3:
                sGallow4 = " | /|";
                break;
            case 4:
                sGallow4 = " | /|\\";
                break;
            case 5:
                sGallow5 = " | /`";
                break;
            case 6:
                sGallow5 = " | /`\\";
                break;
            default:
                break;
        }

        System.out.println(sGallow1);
        System.out.println(sGallow2);
        System.out.println(sGallow3);
        System.out.println(sGallow4);
        System.out.println(sGallow5);
        System.out.println(sGallow6);
        System.out.println(sGallow7);
        System.out.println();





    }

    public static char getChar() {
        char cKey = 2;
        String strTmp;
        do {
            System.out.print("Введите одну русскую букву: ");
            strTmp = cReadFromKeyboard.nextLine();
            strTmp = strTmp.toLowerCase();
        }
        while (!strTmp.matches("[а-я]"));                      // Выполнить проверку на принадлежность диапазона ru букв
        cKey = strTmp.charAt(0);
        return cKey;
    }


}   //end Main
