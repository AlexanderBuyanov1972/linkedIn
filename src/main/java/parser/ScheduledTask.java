package parser;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import java.io.*;
import java.net.*;
import java.util.*;


class ScheduledTask extends TimerTask {


    private Set<String> testid;
    private String url;
    ParserEntity parse = new ParserEntity();

    public ScheduledTask() {
        this.url = parse.getUrl();
        this.testid = parse.getTestid();
    }

    Date now;

    public void run() {
        parseUrl(url, testid);
        now = new Date();
        System.out.println("Time is :" + now);
    }

    public static void parseUrl(String url, Set testid) {
        System.out.println("rezult" + testid.size());
        try {
            System.err.printf(" idem na sayt ");
            Connection.Response response = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
//                    .referrer("http://www.google.com")
                    .timeout(12000)
                    .followRedirects(true)
                    .execute();
            Document doc = response.parse();
            Elements aElement = doc.getElementsByClass("listed-job-posting");

            for (Element rez : aElement) {
                String rez1 = " ";
                String id = rez.attr("data-job-id");
                if (testid.add(id)) {
                    System.out.println(id + "   id");
                    rez1 = "link: " + rez.attr("href") + "\n" + "Job ID: " + rez.attr("data-job-id") + "\n" + "|||" + "\n";
                    makingJson(rez1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makingJson(String rez1) throws IOException {
        String urlPost = "https://hooks.slack.com/services/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";
        String param =
                " { \"text\":\" " + rez1 + "\"}";

        post(urlPost, param);
    }

    private static void post(String urlPost, String param) throws IOException {
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

