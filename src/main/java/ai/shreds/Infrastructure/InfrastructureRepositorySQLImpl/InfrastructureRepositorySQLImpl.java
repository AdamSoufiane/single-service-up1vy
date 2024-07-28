package ai.shreds.infrastructure;

import ai.shreds.domain.DomainPostEntity;
import ai.shreds.domain.DomainPostListEntity;
import ai.shreds.domain.DomainRepositoryPort;
import ai.shreds.shared.SharedRequestParams;
import ai.shreds.shared.exceptions.CustomDatabaseException;
import ai.shreds.shared.exceptions.CustomNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.lang.Long;
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
    @Transactional(readOnly = true)
    public DomainPostListEntity findPostsByParams(SharedRequestParams params) {
        logger.info("Starting to fetch posts with parameters: {}", params);
        String sql = "SELECT * FROM posts WHERE filterType = :filterType AND filterValue = :filterValue LIMIT :limit OFFSET :offset";
        MapSqlParameterSource paramsSource = new MapSqlParameterSource();
        paramsSource.addValue("filterType", params.getFilterType());
        paramsSource.addValue("filterValue", params.getFilterValue());
        paramsSource.addValue("limit", params.getLimit(), Integer.class);
        paramsSource.addValue("offset", params.getOffset(), Integer.class);
        try {
            List<DomainPostEntity> posts = jdbcTemplate.query(sql, paramsSource, (rs, rowNum) -> new DomainPostEntity(rs.getLong("id"), rs.getString("title"), rs.getString("content"), rs.getString("author"), rs.getString("date")));
            long total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM posts WHERE filterType = :filterType AND filterValue = :filterValue", paramsSource, Long.class);
            logger.info("Fetched {} posts.", total);
            return new DomainPostListEntity(posts, total);
        } catch (EmptyResultDataAccessException e) {
            logger.error("No posts found for parameters: {}", params);
            throw new CustomNotFoundException("No posts found for parameters: " + params);
        } catch (DataAccessException e) {
            logger.error("Error accessing data for posts with parameters: {}", params, e);
            throw new CustomDatabaseException("Error accessing data for posts with parameters: " + params, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DomainPostEntity findPostById(Long id) {
        logger.info("Fetching post by ID: {}", id);
        String sql = "SELECT * FROM posts WHERE id = :id";
        MapSqlParameterSource paramsSource = new MapSqlParameterSource();
        paramsSource.addValue("id", id);
        try {
            DomainPostEntity post = jdbcTemplate.queryForObject(sql, paramsSource, (rs, rowNum) -> new DomainPostEntity(rs.getLong("id"), rs.getString("title"), rs.getString("content"), rs.getString("author"), rs.getString("date")));
            logger.info("Fetched post: {}", post);
            return post;
        } catch (EmptyResultDataAccessException e) {
            logger.error("Post not found with ID: {}", id);
            throw new CustomNotFoundException("Post not found with ID: " + id);
        } catch (DataAccessException e) {
            logger.error("Error accessing data for post with ID: {}", id, e);
            throw new CustomDatabaseException("Error accessing data for post with ID: " + id, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DomainPostEntity findPostBySlug(String slug) {
        logger.info("Fetching post by slug: {}", slug);
        String sql = "SELECT * FROM posts WHERE slug = :slug";
        MapSqlParameterSource paramsSource = new MapSqlParameterSource();
        paramsSource.addValue("slug", slug);
        try {
            DomainPostEntity post = jdbcTemplate.queryForObject(sql, paramsSource, (rs, rowNum) -> new DomainPostEntity(rs.getLong("id"), rs.getString("title"), rs.getString("content"), rs.getString("author"), rs.getString("date")));
            logger.info("Fetched post: {}", post);
            return post;
        } catch (EmptyResultDataAccessException e) {
            logger.error("Post not found with slug: {}", slug);
            throw new CustomNotFoundException("Post not found with slug: " + slug);
        } catch (DataAccessException e) {
            logger.error("Error accessing data for post with slug: {}", slug, e);
            throw new CustomDatabaseException("Error accessing data for post with slug: " + slug, e);
        }
    }
}