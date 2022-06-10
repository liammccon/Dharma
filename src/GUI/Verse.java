package GUI;

public class Verse {
    private final String bookTitle;
    private final String bookCode;
    private final String author;
    private final String chapterTitle;
    private final int chapterNumber;
    private final int verseNumber;
    private final String verseText;
    private final String citation;

    public Verse(String fullText){
        bookTitle = findTextOf("book", fullText);
        bookCode = findTextOf("bookCode", fullText);
        author = findTextOf("author", fullText);
        chapterTitle = findTextOf("chapterTitle", fullText);
        chapterNumber = findNumberOf("chapterNumber", fullText);
        verseNumber = findNumberOf("verseNumber", fullText);
        verseText = findTextOf("text", fullText);
        citation = findTextOf("citation", fullText);
    }

    public String getVerseText(){
        return verseText;
    }
    public String getBookTitle() { return bookTitle;}
    public String getBookCode() {return bookCode;}
    public String getAuthor() {return author;}
    public String getChapterTitle() {return chapterTitle;}
    public int getChapterNumber() {return chapterNumber;}
    public int getVerseNumber() {return verseNumber;}
    public String getCitation() {return citation;}


    private static String findTextOf(String precedingText, String fullText){
        return findStringOf(precedingText, fullText, false);
    }

    private static int findNumberOf(String precedingText, String fullText){
        String strNum = findStringOf(precedingText, fullText, true);
        assert(strNum != null);
        return Integer.parseInt(strNum);
    }
    /**
     *Helper method for Verse. For preceding text just enter
     * the name of the section you'd like to access. For instance, enter to get the text of
     * the book, enter "book" in precedingText rather than something like "\"book\":".
     *
     * @param precedingText - the label of what element of the API you want to get
     * @param fullText - the full text taken from the API
     * @param isNumber  - whether you are getting a number or not (such as verseNumber)
     * @return - the text after the label you entered, before the next label.
     */
    private static String findStringOf(String precedingText, String fullText, boolean isNumber){
        String fullPrecedingText = "\"" + precedingText + "\":";
        int precedentStart = fullText.indexOf(fullPrecedingText);
        if (precedentStart == -1) return null;
        //Add 1 for text because it contains an extra " before the text starts
        int textStart = fullText.indexOf(fullPrecedingText) + fullPrecedingText.length() + 1;
        String endMark = "\",";

        //adjusts the previous settings for number.
        // Removes the extra \" before number and the extra \" before it ends
        if (isNumber){
            textStart --;
            endMark = ",";
        }

        //Finds the end of the section, or end of text
        int endMarkIndex = fullText.indexOf(endMark, textStart);
        int textEnd = endMarkIndex > 0 ? endMarkIndex : fullText.indexOf("\"}", textStart);
        return fullText.substring(textStart, textEnd);
    }
}
