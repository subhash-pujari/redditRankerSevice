package reddit.springboot.ranking.importdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import reddit.springboot.ranking.indexing.Indexer;
import reddit.springboot.ranking.models.RedditPost;

public class ReadCsv {
    
    public ReadCsv() {
    }
    
    public ArrayList<RedditPost> readCsv(String filename){
        return readCsv(new File(filename));
    }
    
    public static ArrayList<RedditPost> readCsv(File file){
       ArrayList<RedditPost> redditPosts = new ArrayList<RedditPost>();
       try {
           CSVReader reader = new CSVReader(new FileReader(file));
           //System.out.println("reader.readNext() >> " + reader.readNext());
           List<String[]> allRecords = reader.readAll();
           for(String[] record : allRecords ){
               if(allRecords.indexOf(record) == 0){
                   continue;
               }
               try{
                   RedditPost post = convertToRedditPost(record);
                   if(post != null){
                       redditPosts.add(post);   
                   }
               }catch(NumberFormatException e){
                   e.printStackTrace();
               }
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e){
           e.printStackTrace();
       }
       return redditPosts;
    }
    
    private static RedditPost convertToRedditPost(String[] tokens){
        if(tokens.length == 22){
            RedditPost redditPost = new RedditPost(tokens);
            return redditPost;
        }else{
            return null;
        }
    }
    
    public static void main(String[] args){
        final int TEST_SIZE = 100;
        ArrayList<RedditPost> posts = new ReadCsv().readCsv("/home/spujari/dataset/reddit-top-2.5-million/data/4x4.csv");
        Indexer indexer = new Indexer("./index1"); 
        indexer.initWriter();
        for(RedditPost post : posts){
            indexer.indexReddit(post);
        }
        indexer.closeWriter();
        ArrayList<RedditPost> reddiPostIds = indexer.search("Happy Father");
        
        /*ArrayList<RedditPost> trainSet = new ArrayList<RedditPost>();
        for(int i=0; i< posts.size() - TEST_SIZE; i++){
            trainSet.add(posts.get(i));
        }*/
        
        /*SimpleRegression regression = new RankingEngine(trainSet).createRegeressionModel(trainSet);
        ArrayList<RedditPost> testSet = new ArrayList<RedditPost>();
        for(int i=posts.size() - TEST_SIZE; i< posts.size(); i++){
            System.out.println(" Score >> " + posts.get(i).getScore());
            System.out.println(" upvote - downvote " + posts.get(i).getUps() + " - " + posts.get(i).getDowns());
            double predictionScore = regression.predict(posts.get(i).getUps() - posts.get(i).getDowns());
            System.out.println(" regression predict >> " + ( predictionScore ));
            System.out.println(" error >> " + (predictionScore - posts.get(i).getScore()));
        }*/
        
        /*
         * OLSMultipleLinearRegression olsregression = new OLSMultipleLinearRegression();
            double[][] features = new double[trainSet.size()][3];
            double[] result = new double[trainSet.size()];
            for(int i=0; i< testSet.size(); i++){
                features[i][0] = testSet.get(i).getUps();
                features[i][1] = testSet.get(i).getDowns();
                features[i][2] = testSet.get(i).getNumComments();
                result[i] = testSet.get(i).getScore();
            }
            olsregression.newSampleData(result, features);
            System.out.println(" ols regression >> " + olsregression.estimateRegressionParameters());
         */    
        getRedditPostRank(posts);
    }
    
    public static Map<String, Double> getRedditPostRank(ArrayList<RedditPost> redditPosts){
    	 Map<String, Double> redditRank = new HashMap<String, Double>();
    	 
    	 for(RedditPost post : redditPosts){
    		 redditRank.put(post.getId(), (double)post.getScore());
    	 }
    	 //redditRank = sortMap(redditRank);
    	 //System.out.println("reddit rank >> " + redditRank);
    	 return redditRank;
     }
     
    public static HashMap<String, Double> getRedditPostRankWithTemporalFactor(){
    	 HashMap<String, Double> redditPostRankListTemporalFactor = new HashMap<String, Double>();
    	 for(String redditId : redditPostRankListTemporalFactor.keySet()){
    	 }
    	 return redditPostRankListTemporalFactor;
     }
}
