package mlanima.cards.dtos.responses;

import lombok.Builder;
import lombok.Data;
import mlanima.cards.core.tag.Tag;

@Data
@Builder
public class TagResponse {
    private String name;

    public static TagResponse build(Tag tag) {
        return TagResponse
                .builder()
                .name(tag.getName())
                .build();
    }
}
