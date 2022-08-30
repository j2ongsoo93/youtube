package topia.com.myApp.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public class YoutubeCrawler {

    public HashMap<String, Object> crawlYoutube(String url){
        HashMap<String, Object> youtubeInfo = new HashMap<>();

        Document doc = null;

        try{
            doc = Jsoup.connect(url).get();
        }catch (Exception e){e.printStackTrace();}

        Elements info = doc.select("div#watch7-content");

        Elements script = doc.select("script");

        //채널 썸네일 찾기
        String thumb = script.get(39).toString();
        Pattern p = Pattern.compile("https://yt3.ggpht.com/(.*?)-no-rj");
        Matcher m = p.matcher(thumb);
        ArrayList<String> list = new ArrayList<>();
        while(m.find()){
//            System.out.println("=======================pattern test============================");
//            System.out.println(m.group());
//            System.out.println("=======================pattern test============================");
            list.add(m.group());
        }

        //영상 설명 찾기
        String content = script.get(18).toString();
//        System.out.println(content);
        Pattern p2 = Pattern.compile("shortDescription\":\"(.*?)\",\"isCrawlable\":true,\"");
        Matcher m2 = p2.matcher(content);
        ArrayList<String> list2 = new ArrayList<>();
        while(m2.find()){
//            System.out.println("=======================pattern test============================");
//            System.out.println(m2.group().split("\"")[2]);
//            System.out.println("=======================pattern test============================");
            list2.add(m2.group().split("\"")[2]);
        }

        String ytbTitle = info.select("meta[itemprop=name]").attr("content");
        String ytbThumbnail = "https://i.ytimg.com/vi/"+url.split("=")[1].split("&")[0]+"/maxresdefault.jpg";
        String ytbInfo = list2.get(0);
        String ytbChannelName = info.select("link[itemprop=name]").attr("content");
        String ytbChannelURL = info.select("link").get(1).attr("href");
        String ytbChannelThumb = list.get(2);
        String ytbEmbedUrl = info.select("link[itemprop=embedUrl]").attr("href");;

        System.out.println("ytbTitle: "+ytbTitle);
        System.out.println("ytbThumbnail: "+ytbThumbnail);
        System.out.println("ytbInfo: "+ytbInfo);
        System.out.println("ytbChannelName: "+ytbChannelName);
        System.out.println("ytbChannelURL: "+ytbChannelURL);
        System.out.println("ytbChannelThumb: "+ytbChannelThumb);
        System.out.println("ytbEmbedUrl: "+ytbEmbedUrl);

        youtubeInfo.put("ytbTitle",ytbTitle);
        youtubeInfo.put("ytbThumbnail",ytbThumbnail);
        youtubeInfo.put("ytbInfo",ytbInfo);
        youtubeInfo.put("ytbChannelName",ytbChannelName);
        youtubeInfo.put("ytbChannelUrl",ytbChannelURL);
        youtubeInfo.put("ytbChannelThumb", ytbChannelThumb);
        youtubeInfo.put("ytbEmbedUrl", ytbEmbedUrl);

        return youtubeInfo;
    }

    public static void main(String[] args) {
        YoutubeCrawler cw = new YoutubeCrawler();
        cw.crawlYoutube("https://www.youtube.com/watch?v=vBl4K1SspZc");
//        cw.crawlYoutube("https://www.youtube.com/watch?v=NyVD4Hh8KY4&t=1s");
//        cw.crawlYoutube("https://www.youtube.com/watch?v=BqJP_L9ZgYg");
//        cw.crawlYoutube("https://www.youtube.com/watch?v=qk8rmWtzNS8");
    }
}
