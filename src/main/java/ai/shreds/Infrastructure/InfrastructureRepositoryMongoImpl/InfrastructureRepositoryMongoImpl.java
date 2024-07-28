package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainPostEntity; 
 import ai.shreds.domain.DomainPostListEntity; 
 import ai.shreds.domain.DomainRepositoryPort; 
 import ai.shreds.shared.SharedRequestParams; 
 import com.mongodb.client.MongoCollection; 
 import com.mongodb.client.MongoDatabase; 
 import com.mongodb.client.model.Filters; 
 import org.bson.conversions.Bson; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import com.mongodb.MongoException; 
 import com.mongodb.MongoSocketOpenException; 
 import java.util.ArrayList; 
 import java.util.List; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Repository 
 public class InfrastructureRepositoryMongoImpl implements DomainRepositoryPort { 
  
     private final MongoDatabase database; 
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureRepositoryMongoImpl.class); 
  
     @Autowired 
     public InfrastructureRepositoryMongoImpl(MongoDatabase database) { 
         this.database = database; 
     } 
  
     @Override 
     public DomainPostEntity findPostBySlugOrId(String slugOrId) { 
         logger.info("Attempting to retrieve post by slug or ID: {}", slugOrId); 
         if (slugOrId == null || slugOrId.isEmpty()) { 
             throw new IllegalArgumentException("Slug or ID cannot be null or empty"); 
         } 
         try { 
             MongoCollection<DomainPostEntity> collection = database.getCollection("posts", DomainPostEntity.class); 
             Bson filter = Filters.or(Filters.eq("id", slugOrId), Filters.eq("slug", slugOrId)); 
             DomainPostEntity result = collection.find(filter).first(); 
             logger.info("Successfully retrieved post: {}", result); 
             return result; 
         } catch (MongoSocketOpenException e) { 
             logger.error("Network error while retrieving post by slug or ID: {}", slugOrId, e); 
             return null; 
         } catch (MongoException e) { 
             logger.error("MongoDB error retrieving post by slug or ID: {}", slugOrId, e); 
             return null; 
         } 
     } 
  
     @Override 
     public DomainPostListEntity findPosts(SharedRequestParams params, int pageNumber, int pageSize) { 
         logger.info("Fetching posts with parameters: {}, page number: {}, page size: {}", params, pageNumber, pageSize); 
         if (params == null) { 
             throw new IllegalArgumentException("Search parameters cannot be null"); 
         } 
         try { 
             MongoCollection<DomainPostEntity> collection = database.getCollection("posts", DomainPostEntity.class); 
             Bson filter = Filters.eq(params.getFilterType(), params.getFilterValue()); 
             List<DomainPostEntity> posts = collection.find(filter).skip(pageSize * (pageNumber - 1)).limit(pageSize).into(new ArrayList<>()); 
             long total = collection.countDocuments(filter); 
             DomainPostListEntity postList = new DomainPostListEntity(); 
             postList.setPosts(posts); 
             postList.setTotal(total); 
             logger.info("Retrieved {} posts with filter {}={}, page number: {}, page size: {}", total, params.getFilterType(), params.getFilterValue(), pageNumber, pageSize); 
             return postList; 
         } catch (MongoSocketOpenException e) { 
             logger.error("Network error while fetching posts", e); 
             return new DomainPostListEntity(); 
         } catch (MongoException e) { 
             logger.error("MongoDB error retrieving posts with filter {}={}: ", params.getFilterType(), params.getFilterValue(), e); 
             return new DomainPostListEntity(); 
         } 
     } 
 }