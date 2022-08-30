package topia.com.myApp.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeCrawlerTest {

    public HashMap<String, String> crawlYoutube(String url){
        HashMap<String, String> youtubeInfo = new HashMap<>();

        Document doc = null;

        try{
            doc = Jsoup.connect(url).get();
//            System.out.println("=======================DOC============================");
//            System.out.println(doc);
//            System.out.println("=======================DOC============================");
        }catch (Exception e){e.printStackTrace();}

        //확정지은 요소들
        Elements info = doc.select("div#watch7-content");




        //테스트

        Elements script = doc.select("script");
        System.out.println("=======================DOC channel============================");
        System.out.println(script.get(39));
        System.out.println("=======================DOC channel============================");

        String str = script.get(39).toString();
//        Pattern p = Pattern.compile("\\\"height\\\":88\\},\\{\\\"url\\\":\\\"(.*?)\\\",\\\"width\\\":176");
        Pattern p = Pattern.compile("https://yt3.ggpht.com/(.*?)-no-rj");
        Matcher m = p.matcher(str);
        ArrayList<String> list = new ArrayList<String>();
        while(m.find()){
            System.out.println("=======================pattern test============================");
            System.out.println(m.group());
            System.out.println("=======================pattern test============================");
            list.add(m.group());
        }

//        System.out.println("=======================Split Test============================");
//        System.out.println(str.split("\"width\":176,\"height\":176")[0]);
//        str.
//        System.out.println("=======================Split Test============================");
//
//        String str2 = str.split("\"width\":176,\"height\":176")[0];
//
//        System.out.println("=======================Split Test============================");
//        System.out.println(str2.split("\"")[5]);
//        System.out.println("=======================Split Test============================");



//        System.out.println("=======================info============================");
//        System.out.println(info);
//        System.out.println("=======================info============================");

//        for(int i=0; i<info.select("meta").size();i++){
//            System.out.println("==========================meta("+i+")=========================");
//            System.out.println(info.select("meta[item]"));
//            System.out.println("==========================meta("+i+")=========================");
//        }
//
        for(int i=0; i<info.select("link").size();i++){
            System.out.println("==========================link("+i+")=========================");
            System.out.println(info.select("link").get(i));
            System.out.println("==========================link("+i+")=========================");
        }
//
//        for(int i=0; i<docChannel.select("script").size();i++){
//            System.out.println("==========================script("+i+")=========================");
//            System.out.println(docChannel.select("script").get(i));
//            System.out.println("==========================script("+i+")=========================");
//        }
//
//        for(int i=0; i<doc.select("script").size();i++){
//            System.out.println("==========================script("+i+")=========================");
//            System.out.println(doc.select("script").get(i));
//            System.out.println("==========================script("+i+")=========================");
//        }
//        System.out.println("==========================script(18)=========================");
//        System.out.println(doc.select("script").get(18));
//        System.out.println("==========================script(18)=========================");

        String ytbTitle = info.select("meta[itemprop=name]").attr("content");
        String ytbThumbnail = info.select("link[itemprop=thumbnailUrl]").attr("href");
        String ytbChannelName = info.select("link[itemprop=name]").attr("content");
        String ytbChannelURL = info.select("link").get(1).attr("href");
        String ytbChannelProfile = list.get(2);

        System.out.println("ytbTitle: "+ytbTitle);
        System.out.println("ytbThumbnail: "+ytbThumbnail);
        System.out.println("ytbChannelName: "+ytbChannelName);
        System.out.println("ytbChannelURL: "+ytbChannelURL);
        System.out.println("ytbChannelProfile: "+ytbChannelProfile);


        return youtubeInfo;
    }

    public static void main(String[] args) {
        YoutubeCrawlerTest cw = new YoutubeCrawlerTest();
//        cw.crawlYoutube("https://www.youtube.com/watch?v=vBl4K1SspZc");
        cw.crawlYoutube("https://www.youtube.com/watch?v=BqJP_L9ZgYg");
//        cw.crawlYoutube("https://www.youtube.com/watch?v=qk8rmWtzNS8");
    }
}
