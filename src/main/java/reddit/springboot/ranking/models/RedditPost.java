package reddit.springboot.ranking.models;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reddit_post")
public class RedditPost implements Serializable {

    @Id
    @GeneratedValue
    private String id;
    private String subbredditName;
    private double createdUtc;
    private int score;
    private int ranker_score;
    private String domain;
    private String title;
    private String author;
    private int ups;
    //private int predicted_ups;
    private int downs;
    //private int predicted_downs;
    private int numComments;
    private String permalink;
    private String selfText;
    private String link_flair_text;
    private boolean over_18;
    private String thumbnail;
    private String subreddit_id;
    private boolean edited;
    private String link_flair_css_class;
    private String author_flair_css_class;
    private boolean is_self;
    private String name;
    private String url;
    private String distinguished;
    
    
    public RedditPost() {

    }

    public RedditPost(LinkedHashMap redditPost) {

        try {
            this.createdUtc = (Double) redditPost.get("created_utc");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            this.score = (Integer) redditPost.get("score");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        this.domain = (String) redditPost.get("domain");

        this.id = (String) redditPost.get("id");

        this.title = (String) redditPost.get("title");
        this.author = (String) redditPost.get("author");

        try {
            this.ups = (Integer) redditPost.get("ups");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            this.downs = (Integer) redditPost.get("downs");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            this.numComments = (Integer) redditPost.get("num_comments");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        this.permalink = (String) redditPost.get("permalink");
        this.selfText = (String) redditPost.get("self_text");
        this.link_flair_text = (String) redditPost.get("link_flair_text");

        this.thumbnail = (String) redditPost.get("thumbnail");

        try {
            this.subreddit_id = (String) redditPost.get("subreddit_id");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        this.url = (String) redditPost.get("url");
    }

    public RedditPost(String[] tokens) {

        this.createdUtc = Double.parseDouble(tokens[0]);
        this.score = Integer.parseInt(tokens[1]);
        this.domain = tokens[2];
        this.id = tokens[3];
        this.title = tokens[4];
        this.author = tokens[5];
        this.ups = Integer.parseInt(tokens[6]);
        this.downs = Integer.parseInt(tokens[7]);
        this.numComments = Integer.parseInt(tokens[8]);
        this.permalink = tokens[9];
        this.selfText = tokens[10];
        this.link_flair_text = tokens[11];
        this.thumbnail = tokens[13];
        this.subreddit_id = tokens[14];
        this.edited = Boolean.parseBoolean(tokens[15]);
        this.link_flair_css_class = tokens[16];
        this.author_flair_css_class = tokens[17];
        this.is_self = Boolean.parseBoolean(tokens[18]);
        this.name = tokens[19];
        this.url = tokens[20];
        this.distinguished = tokens[21];
    }

    public double getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(double createdUtc) {
        this.createdUtc = createdUtc;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public int getDowns() {
        return downs;
    }

    public void setDowns(int downs) {
        this.downs = downs;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getSelfText() {
        return selfText;
    }

    public void setSelfText(String selfText) {
        this.selfText = selfText;
    }

    public String getLink_flair_text() {
        return link_flair_text;
    }

    public void setLink_flair_text(String link_flair_text) {
        this.link_flair_text = link_flair_text;
    }

    public boolean isOver_18() {
        return over_18;
    }

    public void setOver_18(boolean over_18) {
        this.over_18 = over_18;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSubreddit_id() {
        return subreddit_id;
    }

    public void setSubreddit_id(String subreddit_id) {
        this.subreddit_id = subreddit_id;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public String getLink_flair_css_class() {
        return link_flair_css_class;
    }

    public void setLink_flair_css_class(String link_flair_css_class) {
        this.link_flair_css_class = link_flair_css_class;
    }

    public String getAuthor_flair_css_class() {
        return author_flair_css_class;
    }

    public void setAuthor_flair_css_class(String author_flair_css_class) {
        this.author_flair_css_class = author_flair_css_class;
    }

    public boolean isIs_self() {
        return is_self;
    }

    public void setIs_self(boolean is_self) {
        this.is_self = is_self;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDistinguished() {
        return distinguished;
    }

    public void setDistinguished(String distinguished) {
        this.distinguished = distinguished;
    }

    public String getSubbredditName() {
        return subbredditName;
    }

    public void setSubbredditName(String subbreddit_name) {
        this.subbredditName = subbreddit_name;
    }

    public int getRanker_score() {
        return ranker_score;
    }

    public void setRanker_score(int predicted_score) {
        this.ranker_score = predicted_score;
    }

/*    public int getPredicted_ups() {
        return predicted_ups;
    }

    public void setPredicted_ups(int predicted_ups) {
        this.predicted_ups = predicted_ups;
    }

    public int getPredicted_downs() {
        return predicted_downs;
    }

    public void setPredicted_downs(int predicted_downs) {
        this.predicted_downs = predicted_downs;
    }*/

}
