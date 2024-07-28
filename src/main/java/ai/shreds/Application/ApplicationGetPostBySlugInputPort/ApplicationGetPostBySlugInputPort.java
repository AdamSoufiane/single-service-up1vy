package ai.shreds.application; 
  
 import ai.shreds.shared.SharedPostDTO; 
  
 /** 
  * Interface for input port that handles fetching a post by its slug. 
  * This interface is part of the application layer in the Hexagonal Architecture, 
  * ensuring decoupling from the domain and infrastructure layers. 
  */ 
 public interface ApplicationGetPostBySlugInputPort { 
     /** 
      * Fetches a post based on its slug. 
      * @param slug the slug of the post to retrieve 
      * @return SharedPostDTO containing the post details 
      * @throws IllegalArgumentException if slug is null or empty 
      */ 
     SharedPostDTO getPostBySlug(String slug); 
 }