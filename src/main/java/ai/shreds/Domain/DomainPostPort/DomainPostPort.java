package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedRequestParams; 
 import ai.shreds.domain.DomainPostEntity; 
 import ai.shreds.domain.DomainPostListEntity; 
  
 /** 
  * DomainPostPort defines the domain layer's interface for post retrieval operations. 
  */ 
 public interface DomainPostPort { 
     /** 
      * Retrieves a list of posts based on given parameters. 
      * 
      * @param params SharedRequestParams 
      * @return DomainPostListEntity containing the list of posts and additional metadata. 
      */ 
     public DomainPostListEntity retrievePosts(SharedRequestParams params); 
  
     /** 
      * Retrieves a single post by its ID. 
      * 
      * @param id Long identifier of the post 
      * @return DomainPostEntity of the requested post 
      */ 
     public DomainPostEntity retrievePostById(Long id); 
  
     /** 
      * Retrieves a single post by its slug. 
      * 
      * @param slug String slug of the post 
      * @return DomainPostEntity of the requested post 
      */ 
     public DomainPostEntity retrievePostBySlug(String slug); 
 }