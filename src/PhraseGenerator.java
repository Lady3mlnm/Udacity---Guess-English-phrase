import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class PhraseGenerator {
    private LinkedList<String> phraseList = new LinkedList<String>();

    PhraseGenerator(){
        this("English phrases4.txt");
    }

    PhraseGenerator(String fileName){
        File file = new File(fileName);
        Scanner scanner;

        //Build array of all possible phrases
        try {
            scanner = new Scanner(file);

            boolean isIgnoredLine = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (isIgnoredLine){
                    if (line.indexOf("*/")>=0){
                        line = line.substring(line.indexOf("*/")+2).trim();
                        isIgnoredLine = false;
                    } else
                        continue;
                }

                boolean isInlineComment;
                do {
                    isInlineComment = false;
                    if (line.indexOf("//") >= 0 || line.indexOf("/*") >= 0) {
                        int indexSC = line.indexOf("//");  //beginning of single-line comment
                        int indexMC = line.indexOf("/*");  //beginning of multi-line comment
                        if (indexSC >= 0 && (indexMC == -1 || indexSC < indexMC))
                            //work with single-line comment
                            line = line.substring(0, indexSC).trim();
                        else {
                            //work with multi-line comment
                            if (line.indexOf("*/",indexMC+2) >= 0){
                                line = line.substring(0, indexMC) + line.substring(line.indexOf("*/",indexMC+2)+2);
                                isInlineComment = true;
                            } else {
                                line = line.substring(0, indexMC).trim();
                                isIgnoredLine = true;
                            }
                        }
                    }
                } while (isInlineComment);

                if (!line.equals(""))
                    phraseList.add(line);
            }
        }catch (FileNotFoundException e){
            System.out.println("\n! Caught FileNotFoundException !\n" + e + "\n");
        }catch (Exception e){
            System.out.println("\n! Caught common Exception !\n" + e + "\n");
        }

        // Display generated phraseList
        /*for(String s: phraseList ) {
            System.out.println(s);
        }
        System.out.println();
        */
    }



    //Return random phrase from the phrase array phraseList
    public String getPhrase(){
        if (phraseList.size()==0)
            return "";

        int rnd = (int) (Math.random()*phraseList.size());
        String returnPhrase = phraseList.get(rnd);
        phraseList.remove(returnPhrase);
        return returnPhrase;
    }



    //Add new phrase to the phrase array phraseList
    public void addPhrase(String newStr){
        phraseList.add(newStr.trim());
    }
}
