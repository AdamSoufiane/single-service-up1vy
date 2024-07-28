package ai.shreds.application; 
  
 import ai.shreds.domain.DomainPostServicePort; 
 import ai.shreds.domain.DomainPostEntity; 
 import ai.shreds.domain.DomainPostListEntity; 
 import ai.shreds.shared.SharedRequestParams; 
 import ai.shreds.shared.ApplicationPostListDTO; 
 import ai.shreds.shared.SharedPostDTO; 
 import ai.shreds.shared.ApplicationPostNotFoundException; 
 import ai.shreds.shared.CustomPostRetrievalException; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import java.util.stream.Collectors; 
  
 @Service 
 public class ApplicationPostApplicationService { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationPostApplicationService.class); 
     private final DomainPostServicePort postDomainService; 
  
     @Autowired 
     public ApplicationPostApplicationService(DomainPostServicePort postDomainService) { 
         this.postDomainService = postDomainService; 
     } 
  
     public ApplicationPostListDTO getPosts(SharedRequestParams params) { 
         try { 
             DomainPostListEntity domainPosts = postDomainService.retrievePosts(params); 
             return new ApplicationPostListDTO(domainPosts.getPosts().stream() 
                     .map(this::convertToSharedPostDTO) 
                     .collect(Collectors.toList()), 
                     domainPosts.getTotal()); 
         } catch (Exception e) { 
             logger.error("Failed to retrieve posts", e); 
             throw new CustomPostRetrievalException("Internal server error during post retrieval", e); 
         } 
     } 
  
     public SharedPostDTO getPostById(Long id) { 
         try { 
             DomainPostEntity post = postDomainService.retrievePostById(id); 
             if (post == null) { 
                 throw new ApplicationPostNotFoundException("Post not found with ID: " + id, "Check the post ID."); 
             } 
             return convertToSharedPostDTO(post); 
         } catch (Exception e) { 
             logger.error("Failed to retrieve post by ID", e); 
             throw new CustomPostRetrievalException("Internal server error during post retrieval by ID", e); 
         } 
     } 
  
     public SharedPostDTO getPostBySlug(String slug) { 
         try { 
             DomainPostEntity post = postDomainService.retrievePostBySlug(slug); 
             if (post == null) { 
                 throw new ApplicationPostNotFoundException("Post not found with slug: " + slug, "Check the post slug."); 
             } 
             return convertToSharedPostDTO(post); 
         } catch (Exception e) { 
             logger.error("Failed to retrieve post by slug", e); 
             throw new CustomPostRetrievalException("Internal server error during post retrieval by slug", e); 
         } 
     } 
  
     private SharedPostDTO convertToSharedPostDTO(DomainPostEntity entity) { 
         return new SharedPostDTO(entity.getId(), entity.getTitle(), entity.getContent(), entity.getAuthor(), entity.getDate()); 
     } 
  
     private void handlePaginationAndSorting(SharedRequestParams params) { 
         // Implementation for pagination and sorting based on params 
     } 
 }