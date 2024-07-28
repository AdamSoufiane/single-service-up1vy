package adapters.primary;

import shared.SharedRequestParams;
import shared.ApplicationPostListDTO;
import shared.SharedPostDTO;
import application.ApplicationPostApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class AdapterPostController {

    private final ApplicationPostApplicationService applicationPostAppService;

    @Autowired
    public AdapterPostController(ApplicationPostApplicationService applicationPostAppService) {
        this.applicationPostAppService = applicationPostAppService;
    }

    @GetMapping
    public ResponseEntity<ApplicationPostListDTO> getAllPosts(@RequestParam SharedRequestParams params) {
        ApplicationPostListDTO postList = applicationPostAppService.getPosts(params);
        return ResponseEntity.ok(postList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SharedPostDTO> getPostById(@PathVariable Long id) {
        SharedPostDTO post = applicationPostAppService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<SharedPostDTO> getPostBySlug(@PathVariable String slug) {
        SharedPostDTO post = applicationPostAppService.getPostBySlug(slug);
        return ResponseEntity.ok(post);
    }
}