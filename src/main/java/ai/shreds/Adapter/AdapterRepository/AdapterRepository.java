package adapters.primary;

import domain.DomainPostEntity;
import domain.DomainPostListEntity;
import shared.SharedRequestParams;
import shared.SharedValueObjectPostIdentifier;
import infrastructure.InfrastructureRepositorySQLImpl; // Assuming SQL implementation is used

public class AdapterRepository implements DomainRepositoryPort {

    private InfrastructureRepositorySQLImpl repository;

    public AdapterRepository(InfrastructureRepositorySQLImpl repository) {
        this.repository = repository;
    }

    @Override
    public DomainPostEntity findPostsBySlugOrId(SharedValueObjectPostIdentifier slugOrId) {
        return repository.findPostsBySlugOrId(slugOrId.getSlugOrId());
    }

    @Override
    public DomainPostListEntity findPosts(SharedRequestParams params) {
        return repository.findPosts(params);
    }

    @Override
    public DomainPostEntity findPostById(Long id) {
        return repository.findPostById(id);
    }
}