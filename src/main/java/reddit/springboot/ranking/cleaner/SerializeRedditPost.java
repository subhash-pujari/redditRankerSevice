package reddit.springboot.ranking.cleaner;

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

import reddit.springboot.ranking.importdata.ReadCsv;
import reddit.springboot.ranking.models.RedditPost;

public class SerializeRedditPost {

    private static final String pathSerializedDir = "./serializedFile/";
    private static final String filenameRedditPostSerializeFile = "redditPost.ser";
    private static final String filenameRedditMapSerializeFile = "redditPostMap.ser";
    private static final String pathDataset = "./dataset/";
    
    public static HashMap<String, RedditPost> getRedditIdRedditPostMap(ArrayList<RedditPost> posts){
        HashMap<String, RedditPost> redditIdRedditPostMap = new HashMap<String, RedditPost>();
        if(new File(pathSerializedDir + filenameRedditMapSerializeFile).exists()){
            redditIdRedditPostMap = deserializeRedditIdRedditPostMap(pathSerializedDir + filenameRedditMapSerializeFile);
        }else{
            for(RedditPost post : posts){
                redditIdRedditPostMap.put(post.getId(), post);
            }
            serializeRedditIdRedditPostMap(redditIdRedditPostMap, pathSerializedDir + filenameRedditMapSerializeFile);
        }
        return redditIdRedditPostMap;
    }
    
    public static ArrayList<RedditPost> getRedditPostsFromFile(){
        ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
        ArrayList<String> filenames = FileUtil.listFilesForFolder(new File(pathDataset));
        for(String filename : filenames){
            posts.addAll(ReadCsv.readCsv(new File(filename)));
        }
        return posts;
    }
    
    public static ArrayList<RedditPost> getRedditPosts(){
        ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
        if(new File(pathSerializedDir + filenameRedditPostSerializeFile).exists()){
            posts = deserializeRedditPosts(pathSerializedDir + filenameRedditPostSerializeFile);
        }else{
            for(String filename : FileUtil.listFilesForFolder(new File(pathDataset))){
                posts.addAll(ReadCsv.readCsv(new File(filename)));
            }   
            serializeRedditPosts(posts, pathSerializedDir + filenameRedditPostSerializeFile);
        }
        return posts;
    }
    
    private static void serializeRedditIdRedditPostMap(HashMap<String, RedditPost> redditIdRedditPostMap, String filePath){
        OutputStream file = null;
        try {
            file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(redditIdRedditPostMap);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static HashMap<String, RedditPost> deserializeRedditIdRedditPostMap(String filepath){
        InputStream file = null;
        HashMap<String, RedditPost> redditIdRedditPostMap = null;
        try {
            file = new FileInputStream(filepath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            redditIdRedditPostMap = (HashMap<String, RedditPost>) input.readObject();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redditIdRedditPostMap;
    }
    
    private static void serializeRedditPosts(ArrayList<RedditPost> posts, String filePath){
        OutputStream file = null;
        try {
            file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(posts);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static ArrayList<RedditPost> deserializeRedditPosts(String filepath){
        InputStream file = null;
        ArrayList<RedditPost> redditPosts = null;
        try {
            file = new FileInputStream(filepath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            redditPosts = (ArrayList<RedditPost>) input.readObject();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redditPosts;
    }
    
    public static void main(String[] args){
        SerializeRedditPost.getRedditPosts();
    }
}
