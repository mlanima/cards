package mlanima.cards.dtos.responses;

import lombok.Builder;
import lombok.Data;
import mlanima.cards.core.user.User;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String email;

    public static UserResponse build(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
