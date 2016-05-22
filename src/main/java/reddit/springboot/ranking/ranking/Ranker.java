package reddit.springboot.ranking.ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import reddit.springboot.ranking.models.RedditPost;

public class Ranker {
    
    private static Ranker rankerInstance;
    
    ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
    
    public ArrayList<RedditPost> getRankedRedditPost(ArrayList<RedditPost> posts){
        ArrayList<RedditPost> returnPosts = new ArrayList<RedditPost>();
        Map<String, Integer> redditIdScoreMap = getRedditIdScoreMap(posts);
        redditIdScoreMap = sortHashMap(redditIdScoreMap);
        HashMap<String, RedditPost> redditIdRedditPostMap = getRedditIdRedditPostMap(posts);
        for(String postId : redditIdScoreMap.keySet()){
            returnPosts.add(redditIdRedditPostMap.get(postId));
        }
        return returnPosts;
    }
    
    private HashMap<String, RedditPost> getRedditIdRedditPostMap(ArrayList<RedditPost> posts){
        HashMap<String, RedditPost> redditIdRedditPostMap = new HashMap<String, RedditPost>();
        for(RedditPost post : posts){
            // post.setPredicted_downs(getDowns(post));
            // post.setPredicted_ups(getUps(post));
            post.setRanker_score(getScore(post));
            redditIdRedditPostMap.put(post.getId(), post);
        }
        return redditIdRedditPostMap;
    }
    
    private Map<String, Integer> sortHashMap(Map<String, Integer> redditMap){
        Map<String, Integer> sortedResultMap = new TreeMap<String, Integer>(new DoubleMapComparator(redditMap));
        sortedResultMap.putAll(redditMap);
        return sortedResultMap;
    }
    
    private HashMap<String, Integer> getRedditIdScoreMap(ArrayList<RedditPost> posts){
        HashMap<String, Integer> redditScoreMap = new HashMap<String, Integer>();
        for(RedditPost post : posts){
            redditScoreMap.put(post.getId(), getScore(post));
        }
        return redditScoreMap;
    }
    
    public Ranker getInstance(){
        if(rankerInstance == null){
            rankerInstance = new Ranker();
        }
        return rankerInstance;
    }
    
    private int getScore(RedditPost post){
        return getUps(post) - getDowns(post);
    }
    
    public int getUps(RedditPost post){
        double ups = 1.5513 * post.getNumComments() + 152.9849;
        return (int)ups;
    }
    
    public int getDowns(RedditPost post){
        double downs = 1.2299 * post.getNumComments() + 51.3915;
        return (int)downs;
    }
    
}
