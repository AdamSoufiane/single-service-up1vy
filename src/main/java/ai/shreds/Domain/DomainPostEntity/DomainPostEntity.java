package ai.shreds.domain; 
  
 import lombok.Data; 
  
 /** 
  * DomainPostEntity represents a blog post in the domain layer. 
  * This class holds the core attributes of a post and is used across the domain layer. 
  */ 
 @Data 
 public class DomainPostEntity { 
     private Long id; 
     private String title; 
     private String content; 
     private String author; 
     private String date; 
 }