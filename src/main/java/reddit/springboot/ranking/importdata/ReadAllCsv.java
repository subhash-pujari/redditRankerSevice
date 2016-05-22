package reddit.springboot.ranking.importdata;

import java.io.File;
import java.util.ArrayList;

import reddit.springboot.ranking.indexing.Indexer;
import reddit.springboot.ranking.models.RedditPost;

public class ReadAllCsv {
    
    public ArrayList<RedditPost> readAllCSV(String path){
        ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
        ArrayList<File> files = getAllFilesInADirectory(path);
        ReadCsv readCsv = new ReadCsv();
        for(File file : files){
            System.out.println("reading for file >> " + file.getAbsolutePath());
            if(file.exists()){
                posts.addAll(readCsv.readCsv(file));
            }
        }
        return posts;
    }
    
    private ArrayList<File> getAllFilesInADirectory(String path){
        ArrayList<File> filepaths = new ArrayList<File>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filepaths.add(listOfFiles[i]);
            }
        }
        return filepaths;
    }
    
    public static void main(String[] args){
        /*ArrayList<RedditPost> posts = new ReadAllCsv().readAllCSV("/home/spujari/dataset/reddit-top-2.5-million/data/");
        System.out.println(" size posts >> " + posts.size());
        Indexer indexer = new Indexer("./index4"); 
        indexer.initWriter();
        for(RedditPost post : posts){
            if(post == null){
                System.out.println("post null");
                continue;
            }else{
                //System.out.println("post not null");
            }
            indexer.indexReddit(post);
        }
        indexer.closeWriter();*/
    
        Indexer indexer = new Indexer("./index4"); 
        indexer.search("America");
    }
}
