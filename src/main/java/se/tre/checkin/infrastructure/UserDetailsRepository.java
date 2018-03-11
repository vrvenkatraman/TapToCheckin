package se.tre.checkin.infrastructure;

import se.tre.checkin.domain.db.UserDetails;

import java.util.Optional;

public interface UserDetailsRepository {

    Optional<UserDetails> getUserDetailsByEmail(String email);

    Optional<UserDetails> getUserDetailsByName(String name);
}
