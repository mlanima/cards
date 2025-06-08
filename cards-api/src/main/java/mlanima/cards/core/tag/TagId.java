package mlanima.cards.core.tag;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;


@Data
public class TagId implements Serializable {
    private String name;
    private Long userId;
}
