package com.jbc.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbc.model.user.User;

/**
 * {@link com.jbc.model.user.User} {@code JpaRepository} that is using the
 * {@link User} {@code Entity} to manage the users in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#User
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/* methods */
	public Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);

	public Optional<User> findByIdNotAndEmailIgnoreCase(long id, String email);

	public Optional<User> findByEmailIgnoreCase(String email);

}
