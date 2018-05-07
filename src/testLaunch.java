public class testLaunch {
    public static void main(String[] args) {
        PhraseGenerator PG = new PhraseGenerator("English phrases.txt");
        //PhraseGenerator PG = new PhraseGenerator();

        PG.addPhrase("  My new string 2  ");

        String testStr = PG.getPhrase();
        System.out.println("\nDone! There was received string\n" + testStr);

        testStr = PG.getPhrase();
        System.out.println("\nDone! There was received 2nd string\n" + testStr);

        testStr = PG.getPhrase();
        System.out.println("\nDone! There was received 3rd string\n" + testStr);

        testStr = PG.getPhrase();
        System.out.println("\nDone! There was received 4th string\n" + testStr);

        testStr = PG.getPhrase();
        System.out.println("\nDone! There was received 5th string\n" + testStr);

    }
}
