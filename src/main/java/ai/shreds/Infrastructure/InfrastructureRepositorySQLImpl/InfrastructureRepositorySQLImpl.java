package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainPostEntity; 
 import ai.shreds.domain.DomainPostListEntity; 
 import ai.shreds.domain.DomainRepositoryPort; 
 import ai.shreds.shared.SharedRequestParams; 
 import org.springframework.dao.DataAccessException; 
 import org.springframework.dao.DataIntegrityViolationException; 
 import org.springframework.dao.QueryTimeoutException; 
 import org.springframework.jdbc.core.JdbcTemplate; 
 import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
 import java.util.HashMap; 
 import java.util.List; 
 import java.util.Map; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Repository 
 public class InfrastructureRepositorySQLImpl implements DomainRepositoryPort { 
  
     private final NamedParameterJdbcTemplate jdbcTemplate; 
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureRepositorySQLImpl.class); 
  
     public InfrastructureRepositorySQLImpl(NamedParameterJdbcTemplate jdbcTemplate) { 
         this.jdbcTemplate = jdbcTemplate; 
     } 
  
     @Override 
     @Transactional 
     public DomainPostListEntity findPostsByParams(SharedRequestParams params) { 
         logger.info("Starting to fetch posts with parameters: {}", params); 
         String sql = "SELECT * FROM posts WHERE " + params.getFilterType() + " = :filterValue LIMIT :limit OFFSET :offset"; 
         Map<String, Object> paramMap = new HashMap<>(); 
         paramMap.put("filterValue", params.getFilterValue()); 
         paramMap.put("limit", 10); // Example fixed limit 
         paramMap.put("offset", 0); // Example fixed offset 
         try { 
             List<DomainPostEntity> posts = jdbcTemplate.query(sql, paramMap, (rs, rowNum) -> new DomainPostEntity(rs.getLong("id"), rs.getString("title"), rs.getString("content"), rs.getString("author"), rs.getString("date"))); 
             long total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM posts WHERE " + params.getFilterType() + " = :filterValue", paramMap, Long.class); 
             logger.info("Fetched {} posts.", total); 
             return new DomainPostListEntity(posts, total); 
         } catch (DataIntegrityViolationException e) { 
             logger.error("Data integrity violation while accessing posts", e); 
             throw new CustomDatabaseException("Data integrity error occurred", e); 
         } catch (QueryTimeoutException e) { 
             logger.error("Query timeout occurred while accessing posts", e); 
             throw new CustomDatabaseException("Database query timed out", e); 
         } catch (DataAccessException e) { 
             logger.error("General database error occurred", e); 
             throw new CustomDatabaseException("Database access error", e); 
         } 
     } 
  
     @Override 
     @Transactional 
     public DomainPostEntity findPostById(Long id) { 
         logger.info("Fetching post by ID: {}", id); 
         String sql = "SELECT * FROM posts WHERE id = :id"; 
         Map<String, Object> paramMap = new HashMap<>(); 
         paramMap.put("id", id); 
         try { 
             DomainPostEntity post = jdbcTemplate.queryForObject(sql, paramMap, (rs, rowNum) -> new DomainPostEntity(rs.getLong("id"), rs.getString("title"), rs.getString("content"), rs.getString("author"), rs.getString("date"))); 
             logger.info("Fetched post: {}", post); 
             return post; 
         } catch (DataAccessException e) { 
             logger.error("Error accessing data for post with ID: {}", id, e); 
             throw new CustomDatabaseException("Error accessing data for post with ID: " + id, e); 
         } 
     } 
  
     @Override 
     @Transactional 
     public DomainPostEntity findPostBySlug(String slug) { 
         logger.info("Fetching post by slug: {}", slug); 
         String sql = "SELECT * FROM posts WHERE slug = :slug"; 
         Map<String, Object> paramMap = new HashMap<>(); 
         paramMap.put("slug", slug); 
         try { 
             DomainPostEntity post = jdbcTemplate.queryForObject(sql, paramMap, (rs, rowNum) -> new DomainPostEntity(rs.getLong("id"), rs.getString("title"), rs.getString("content"), rs.getString("author"), rs.getString("date"))); 
             logger.info("Fetched post: {}", post); 
             return post; 
         } catch (DataAccessException e) { 
             logger.error("Error accessing data for post with slug: {}", slug, e); 
             throw new CustomDatabaseException("Error accessing data for post with slug: " + slug, e); 
         } 
     } 
 }