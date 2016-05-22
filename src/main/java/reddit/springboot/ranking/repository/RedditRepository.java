package reddit.springboot.ranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import reddit.springboot.ranking.models.RedditPost;


public interface RedditRepository extends JpaRepository<RedditPost, String> {

}