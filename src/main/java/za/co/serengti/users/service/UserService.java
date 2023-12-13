package za.co.serengti.users.service;

import za.co.serengti.users.entity.User;
import za.co.serengti.users.entity.EmailUser;
import za.co.serengti.users.entity.MobileUser;
import za.co.serengti.users.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    final Identification userIdentifier;
    final UserRepository userRepo;

    @Inject
    public UserService(Identification userIdentifier, UserRepository userRepository) {
        this.userIdentifier = userIdentifier;
        this.userRepo = userRepository;
    }

    @Transactional
    public User findOrSaveUser(String identifier) {
        Identification.Type type = userIdentifier.determineIdentifierType(identifier);
        User user = null;
        if (type == Identification.Type.EMAIL) {
            user = userRepo.findByEmailAddress(identifier)
                    .orElseGet(() -> userRepo.save(new EmailUser("Philani", type.name(), identifier)));
        } else if (type == Identification.Type.MOBILE) {
            user = userRepo.findByMobileNumber(identifier)
                    .orElseGet(() -> userRepo.save(new MobileUser("Philani", type.name(), identifier)));
        }
        return user;
    }
}
