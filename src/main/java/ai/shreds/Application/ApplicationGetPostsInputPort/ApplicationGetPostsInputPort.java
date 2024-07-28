package ai.shreds.application;

import ai.shreds.shared.SharedRequestParams;
import ai.shreds.application.dto.ApplicationPostListDTO;

/**
 * Interface for input port that handles fetching posts.
 */
public interface ApplicationGetPostsInputPort {
    /**
     * Retrieves a list of posts based on filter criteria.
     * @param params the filtering criteria, encapsulated in SharedRequestParams
     * @return a list of posts encapsulated in ApplicationPostListDTO with specific post type
     */
    ApplicationPostListDTO getPosts(SharedRequestParams params);
}