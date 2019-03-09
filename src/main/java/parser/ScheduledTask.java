package parser;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

import javax.xml.soap.Text;
import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;


class ScheduledTask extends TimerTask {
    ScheduledTask() throws IOException {
    }

    static private Set<String> testid = new TreeSet<>();
    static private String fileName = "list_id.txt";
    private String url = "https://il.linkedin.com/jobs/search?location=Israel&sortBy=DD&pageNum=0&keywords=Developer&position=1 ";
    private Date now;

    public void run() {
        try {
            parseUrl(url, testid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        now = new Date();
        System.out.println("Time is :" + now);
    }

    public static void writeFile() throws IOException {
        Scanner in = new Scanner(new File("list_id.txt"));
        while(in.hasNext()){
            if(!in.equals("\n")){
                String a = in.nextLine();
                testid.add(a);
               }
        }
    }

    private static void parseUrl(String url, Set testid) throws IOException {

        try {
            System.err.println(" conect linkedIn ");
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
                String id = rez.attr("data-job-id");
                Elements com = rez.select("div");
                Elements com1 = com.select("p");

                if (testid.add(id)) {
                    try (FileWriter writer = new FileWriter("list_id.txt", true)) {
                        writer.write(id + "\n");
                        writer.flush();
//                        String rez1 = "link: " + rez.attr("href") + "\n" + "Job ID: " + rez.attr("data-job-id") + "\n" + "|||" + "\n";
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                       LinkDto rez1= makeDto(rez);
                        makingJson(rez1);
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private static LinkDto makeDto(Element rez) {

        String id=null;
        String source_id=rez.attr("data-job-id");
        String source_link=rez.attr("href");
        String source=null;
        String apply_link="https://www.linkedin.com/jobs/view/{"+source_id+"}";
        String company=rez.select("div").select("h4").text();
        String location=rez.select("div").select("p.listed-job-posting__location").text();
        String position=rez.select("div").select("h3").text();
        String employment_type=null;
        String relocation=null;
        Long second = getData(rez);
        LocalDateTime date_opened=LocalDateTime.now().minusSeconds(second);
        LocalDateTime date_closed=null;
        String company_location=null;
        Array skills=null;
        Array education=null;
        String experience=null;
        String language=null;
        String processing_stages=null;
        Boolean manual_processing=null;
        Array sent_to=null;
        Text plain_text=null;
        return new LinkDto(id, source_id, source, source_link, apply_link, company,location,
                position, employment_type, relocation, date_opened,  date_closed, company_location,
                 skills,  education,  experience,  language, processing_stages,  manual_processing,
                 sent_to,  plain_text) ;
    }

    private static Long getData(Element rez) {
        Long second=null;
        String[] timeAdded = rez.select("div").select("span.posted-time-ago__text").text().split(" ");
        for(int i = 0; i < timeAdded.length; i++) {

            if (timeAdded[1].equalsIgnoreCase("hour")||timeAdded[1].equalsIgnoreCase("hours")) {
                second = (Long) TimeUnit.HOURS.toSeconds(Long.parseLong(timeAdded[0]));
            }else if(timeAdded[1].equalsIgnoreCase("minutes")){
                second = (Long) TimeUnit.MINUTES.toSeconds(Long.parseLong(timeAdded[0]));
            }
            else {
                second = (Long.parseLong(timeAdded[0]));
            }
        }
        return second;
    }

    private static void makingJson(LinkDto rez1) {
        String urlPost = "https://hooks.slack.com/services/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";
        String result = "Source_id: " + rez1.source_id + "\n" + "Source_link: " + rez1.source_link +" \n" +
                "Apply_link: " + rez1.apply_link +"\n" +  "Company: " + rez1.company +"\n" +  "Location "+ rez1.location +"\n" +
                "Position "  + rez1.position+"\n"  + "Date_opened " + rez1.date_opened +"\n" + "=================================";
        String param =
                " { \"text\":\" " + result + "\"}";
        System.out.println(param + " param");

//        post(urlPost, param);
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

