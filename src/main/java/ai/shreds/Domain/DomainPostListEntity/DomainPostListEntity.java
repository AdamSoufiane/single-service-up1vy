package ai.shreds.domain; 
  
 import java.util.List; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * DomainPostListEntity represents a collection of posts and the total number of posts. 
  * This is used to encapsulate the result of fetching posts based on certain criteria. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class DomainPostListEntity { 
     /** 
      * List of DomainPostEntity representing the posts. 
      */ 
     private List<DomainPostEntity> posts; 
     /** 
      * Total number of posts that match the query. 
      */ 
     private Long total; 
 }