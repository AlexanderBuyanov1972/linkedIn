//package parser;
//
//import java.util.Set;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//
//public class MyTimerTask implements Runnable{
//
//
//
//
//    private final String url;
//    ParserEntity parse = new ParserEntity();
//    private final Set<String> rezult;
//
//    {
//        this.rezult = parse.getRezult();
//        this.url = parse.getUrl();
//    }
//
//    public void run() {
//       ScheduledTask.parseUrl(url, rezult);
//    }
//    public static void main(String args[]) throws InterruptedException {
//
//        System.out.printf(rezult);
//        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(new MyTimerTask(), 0, 1, TimeUnit.MINUTES);
//    }
//}
