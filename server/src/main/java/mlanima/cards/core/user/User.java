package mlanima.cards.core.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import mlanima.cards.core.deck.Deck;
import mlanima.cards.core.tag.Tag;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.Objects;

@Table( name = "users")
@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Email
    private String email;

    @Column( nullable = false )
    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(  mappedBy = "user", cascade = CascadeType.ALL)
    private List<Deck> decks;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Tag> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User( String email, String password) {
        this.email = email;
        this.password = password;
    }

}
