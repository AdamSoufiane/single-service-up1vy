package ai.shreds.domain;

import ai.shreds.domain.entities.DomainPostEntity;
import ai.shreds.domain.entities.DomainPostListEntity;
import ai.shreds.shared.SharedRequestParams;
import ai.shreds.domain.ports.DomainPostPort;
import ai.shreds.domain.ports.DomainRepositoryPort;

public class DomainPostService implements DomainPostPort {

    private final DomainRepositoryPort postRepository;

    public DomainPostService(DomainRepositoryPort postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public DomainPostListEntity retrievePosts(SharedRequestParams params) {
        if (params == null) throw new IllegalArgumentException("Parameters cannot be null");
        return postRepository.findPosts(params);
    }

    @Override
    public DomainPostEntity retrievePostById(Long id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        return postRepository.findPostById(id);
    }

    @Override
    public DomainPostEntity retrievePostBySlug(String slug) {
        if (slug == null) throw new IllegalArgumentException("Slug cannot be null");
        return postRepository.findPostBySlug(slug);
    }
}