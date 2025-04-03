package mlanima.cards.core.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import mlanima.cards.core.deck.Deck;
import mlanima.cards.core.group.Group;
import mlanima.cards.security.UserPrincipal;
import org.hibernate.annotations.NaturalId;

import java.util.List;

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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Group> groups;

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
