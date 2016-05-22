package reddit.springboot.ranking.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reddit.springboot.ranking.models.RedditPost;

public class RedditCrawler {

    public ArrayList<RedditPost> crawlReddits(String subreddit){
        ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
        return getLinks(getJson(getSubbredditUrl(subreddit)));
    }
    
    private String getSubbredditUrl(String subreddit){
        String url = "https://www.reddit.com/r/" + subreddit + "/.json";
        return url;
    }
    
    private String getJson(String urlString){
        URL url;
        StringBuilder builder = new StringBuilder();
        System.setProperty("http.agent", "");
        try {
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                builder.append(inputLine);
            in.close();
        } catch ( IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private ArrayList<RedditPost> getLinks(String json){
        ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
        int indexOfChildren = json.indexOf("children\": ") + "children\": ".length(); 
        int indexOfAfter = json.indexOf(", \"after");
        System.out.println(" indexOfChildren >> " + indexOfChildren );
        System.out.println(" indexOfAfter >> " + indexOfAfter );
        String jsonData = json.substring(indexOfChildren, indexOfAfter);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<HashMap<String, Object>> t3list =  new ArrayList<HashMap<String, Object>>();
        try {
            t3list = mapper.readValue(jsonData, t3list.getClass());
            for(HashMap<String, Object> t3 : t3list){
                posts.add(new RedditPost((LinkedHashMap)t3.get("data")));
            }
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return posts;
    } 
    
    public static void main(String[] args){
        ArrayList<RedditPost> posts = new RedditCrawler().crawlReddits("Health");
        System.out.println(" psosts " + posts);
    }
}

