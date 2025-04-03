package mlanima.cards.core.user;

import mlanima.cards.dtos.UserDTO;
import mlanima.cards.exceptions.observed.IncorrectPasswordException;
import mlanima.cards.exceptions.observed.UserNotFoundException;
import mlanima.cards.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User getUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(principal.getId()).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser() {
        userRepository.deleteById(getUser().getId());
    }

    public User updateUser(UserDTO dto) {
        User userToUpdate = userRepository.findById(getUser().getId()).orElseThrow(
                UserNotFoundException::new
        );

        if (!passwordEncoder.matches(dto.getPassword(), userToUpdate.getPassword())) {
            throw new IncorrectPasswordException();
        }

        if (dto.getNewEmail() != null) {
            userToUpdate.setEmail(dto.getNewEmail());
        }

        if (dto.getNewPassword() != null ) {
            userToUpdate.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        }

        return userRepository.save(userToUpdate);
    }


}
