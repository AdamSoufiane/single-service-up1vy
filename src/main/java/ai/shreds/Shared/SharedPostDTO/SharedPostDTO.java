package ai.shreds.shared; 
  
 import lombok.Data; 
  
 /** 
  * SharedPostDTO is a Data Transfer Object for transferring post data across different layers. 
  * It encapsulates the data of a post in a format suitable for client-facing interfaces. 
  * 
  * @param id The unique identifier for the post. 
  * @param title The title of the post. 
  * @param content The content of the post. 
  * @param author The author of the post. 
  * @param date The date when the post was published. 
  */ 
 @Data 
 public class SharedPostDTO { 
     private Long id; 
     private String title; 
     private String content; 
     private String author; 
     private String date; 
 }