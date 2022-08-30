package topia.com.myApp.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import topia.com.myApp.cont.YoutubeCont;
import topia.com.myApp.serv.YoutubeServ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeMain {

    public ArrayList<Object> crawlYoutube(String url){
        ArrayList<Object> urlList = new ArrayList<>();
        ArrayList<Object> rawlist = new ArrayList<>();

        Document doc = null;

        try{
            doc = Jsoup.connect(url).get();
//            System.out.println("=======================DOC============================");
//            System.out.println(doc);
//            System.out.println("=======================DOC============================");
        }catch (Exception e){e.printStackTrace();}

        //확정지은 요소들
        Elements info = doc.select("script");
//        System.out.println(info.get(33));

        String str = info.toString();
        Pattern p = Pattern.compile("\"/watch(.*?)\",");
        Matcher m = p.matcher(str);
        while(m.find()){
//            System.out.println("=======================pattern test============================");
//            System.out.println(m.group().split("\"")[1]);
//            System.out.println("=======================pattern test============================");
            rawlist.add("https://www.youtube.com"+m.group().split("\"")[1]);
        }
        for(int i=0;i<rawlist.size();){
            urlList.add(rawlist.get(i));
//            System.out.println(rawlist.get(i));
            i = i+2;
        }

//        for(int i=0;i<info.size();i++){
//            System.out.println("========================script"+i+"======================");
//            System.out.println("i: "+info.get(i));
//            System.out.println("========================script"+i+"======================");
//        }

        return urlList;
    }

    public static void main(String[] args) {
        YoutubeMain cw = new YoutubeMain();
        YoutubeCrawler yc = new YoutubeCrawler();
        YoutubeServ ys = new YoutubeServ();
        ArrayList<Object> arr = cw.crawlYoutube("https://www.youtube.com/");

        for(int i=0; i<arr.size();i++){
            System.out.println(arr.get(i));
        }
    }

}