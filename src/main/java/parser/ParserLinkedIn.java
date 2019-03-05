package parser;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserLinkedIn {
    public static void main(String args[]) throws IOException {
        String url = "https://il.linkedin.com/jobs/search?location=Israel&sortBy=DD&pageNum=0&keywords=Developer&position=1 ";
        parseUrl(url);

    }

    private static void parseUrl(String url) {
        Set<String> rezult = new HashSet<String>();
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements aElement = doc.getElementsByClass("listed-job-posting");
            for (Element rez : aElement) {
                String rez1 = "link: " + rez.attr("href") + "\n" + "Job ID: " + rez.attr("data-job-id") + "\n" + "|||" + "\n";
                if (rezult.add(rez1)) {
                    makingJson(rezult);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makingJson(Set<String> rezult) throws IOException {
        for (String text : rezult) {
            String urlPost = "https://hooks.slack.com/services/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";
            String param =
                    " { \"text\":\" " + text + "\"}";
            System.out.println(param);
            post(urlPost, param);
        }
    }

    private static void post(String urlPost, String param ) throws IOException {
            String charset = "UTF-8";
            URLConnection connection = new URL(urlPost).openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(param.getBytes(charset));
            }
            InputStream response = connection.getInputStream();
        }
    }

