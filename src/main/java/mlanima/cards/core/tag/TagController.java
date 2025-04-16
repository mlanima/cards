package mlanima.cards.core.tag;

import mlanima.cards.dtos.requests.TagRequest;
import mlanima.cards.dtos.responses.TagResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagResponse> getTags() {
        return tagService.getTags().stream().map(TagResponse::build).toList();
    }

    @GetMapping("/{tagName}")
    public TagResponse getTag(@PathVariable String tagName) {
        return TagResponse.build(tagService.getTag(tagName));
    }

    @GetMapping(params = "name")
    public List<TagResponse> findTagsByPart(@RequestParam String name) {
        return tagService.findTagsByNamePart(name).stream().map(TagResponse::build).toList();
    }

    @PostMapping
    public ResponseEntity<TagResponse>  createTag(TagRequest tagRequest) {
        TagResponse response = TagResponse.build(tagService.createTag(tagRequest));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{tagName}")
                .buildAndExpand(response.getName())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{tagName}")
    public TagResponse updateTag(@PathVariable String tagName, TagRequest tagRequest) {
        return TagResponse.build(tagService.updateTag(tagName, tagRequest));
    }

    @DeleteMapping("/{tagName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable String tagName) {
        tagService.deleteTag(tagName);
    }

}
