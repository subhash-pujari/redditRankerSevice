package reddit.springboot.ranking.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import reddit.springboot.ranking.importdata.ReadAllCsv;
import reddit.springboot.ranking.indexing.Indexer;
import reddit.springboot.ranking.models.RedditPost;

public class SearchThroughReddit {
    
	
	Map<String, RedditPost> redditHashMap;
	
	public SearchThroughReddit() {
		this.redditHashMap = this.getHashMap("", "");
	}
	
	public ArrayList<RedditPost> searchRedditPosts (String searchString){
		ArrayList<RedditPost> reddiPosts = new ArrayList<RedditPost>();
		ArrayList<RedditPost> redditPostIds = new Indexer().search(searchString);
		return reddiPosts;
	}
	
    public Map<String, RedditPost> getHashMap(String datasetPath, String serializedPath){
        if(new File(serializedPath).exists()){
            return deSerializeHashtagPosts(serializedPath);
        }else{
            ArrayList<RedditPost> posts = new ReadAllCsv().readAllCSV(datasetPath);
            Map<String, RedditPost> reditPostsMap = convertArrayListToHashMap(posts);
            serializeHashtagRedditPosts(reditPostsMap, serializedPath);
            return reditPostsMap;
        }
    }
    
    public Map<String, RedditPost> convertArrayListToHashMap(ArrayList<RedditPost> posts){
        Map<String, RedditPost> redditPostsMap = new HashMap<String, RedditPost>();
        for(RedditPost post : posts){
            if(post == null){
                continue;
            }
            redditPostsMap.put(post.getId(), post);
        }
        return redditPostsMap;
    }
    
    /**
     * Serialize the entropy value for the dataset.
     * @param hashtagEntropyMap
     * @param filePath
     */
    public void serializeHashtagRedditPosts(Map<String, RedditPost> hashtagEntropyMap, String filePath) {
        OutputStream file = null;
        try {
            file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(hashtagEntropyMap);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deserialize the Entropy Map.
     * @param filePath
     * @return
     */
    public Map<String, RedditPost> deSerializeHashtagPosts(String filePath) {
        InputStream file = null;
        Map<String, RedditPost> hashtagEntropyMap = null;
        try {
            file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            hashtagEntropyMap = (Map<String, RedditPost>) input.readObject();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashtagEntropyMap;
    }
}
