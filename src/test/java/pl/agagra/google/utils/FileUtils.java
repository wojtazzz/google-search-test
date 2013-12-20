package pl.agagra.google.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * User: Wojtas
 * Date: 18.12.13
 * Time: 19:08
 *
 * Utils class for extracting test data from txt file
 * */
public class FileUtils {


    public static final String TEXT_FILE_NOUNS = "/Nouns.txt";

    public static List<String> getRandomListOfNouns(int numberOfWords) {
        List<String> wordList = new ArrayList<String>();
        Scanner input = getScanner();
        parseText(wordList, input);
        Collections.shuffle(wordList);
        return wordList.subList(0, numberOfWords);
    }

    private static void parseText(List<String> wordList, Scanner input) {
        while (input.hasNext()) {
            String nextLine = input.nextLine();
            wordList.add(nextLine);
        }
        input.close();
    }

    private static Scanner getScanner() {
        String url = FileUtils.class.getResource(TEXT_FILE_NOUNS).getFile();
        File file = new File(url);
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return input;
    }
}
