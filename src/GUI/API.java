package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class API {

    //From: http://palicanon.codebuckets.com.au/swagger/index.html

    private static final String QUOTE_URL = "http://palicanon.codebuckets.com.au/api/quote";

    public static Verse getRandomVerse() {
        return new Verse(getFullText(QUOTE_URL));
    }

    private static String getFullText(String urlString){
        URL url;
        try {
            url = new URL(urlString);
            Scanner sc = new Scanner(url.openStream());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(" ");
                sb.append(sc.next());
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: URL could not be found";
        }
    }

    public static void main(String[]args) {
        System.out.println(getRandomVerse());
    }
}
