package ai.shreds.application;

import ai.shreds.shared.SharedPostDTO;
import ai.shreds.application.exceptions.ApplicationPostNotFoundException;

/**
 * Interface defining the input port for retrieving a single post by its ID.
 * This interface is part of the application layer in the Hexagonal Architecture,
 * serving as a primary port.
 *
 * @throws ApplicationPostNotFoundException if the post with the specified ID does not exist.
 */
public interface ApplicationGetPostByIdInputPort {
    /**
     * Retrieves a post by its ID.
     * @param id The ID of the post to retrieve.
     * @return SharedPostDTO containing the post details.
     * @throws ApplicationPostNotFoundException if the post is not found, indicating the post with the provided ID does not exist.
     */
    SharedPostDTO getPostById(Long id);
}