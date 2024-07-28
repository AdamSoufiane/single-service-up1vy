package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.ToString; 
  
 /** 
  * SharedValueObjectPostFilter is a value object that encapsulates post filter criteria. 
  */ 
 @Getter 
 @AllArgsConstructor 
 @ToString 
 public class SharedValueObjectPostFilter { 
     private final SharedRequestParams filter; 
 }