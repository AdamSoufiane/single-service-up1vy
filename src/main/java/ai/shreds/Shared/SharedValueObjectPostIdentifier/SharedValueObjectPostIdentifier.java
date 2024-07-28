package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.NoArgsConstructor; 
 import lombok.Setter; 
  
 /** 
  * Value object that encapsulates the identifier (either slug or ID) for a post. 
  * The 'slugOrId' field can contain either a numeric ID or a slug, providing flexibility in identifying posts. 
  */ 
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedValueObjectPostIdentifier { 
     private String slugOrId; 
 }