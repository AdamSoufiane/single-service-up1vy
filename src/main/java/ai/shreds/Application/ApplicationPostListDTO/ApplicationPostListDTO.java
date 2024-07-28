import java.util.List;
import shared.SharedPostDTO;

public class ApplicationPostListDTO {
    private List<SharedPostDTO> posts;
    private Long total;

    public ApplicationPostListDTO(List<SharedPostDTO> posts, Long total) {
        this.posts = posts;
        this.total = total;
    }

    public List<SharedPostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<SharedPostDTO> posts) {
        this.posts = posts;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}