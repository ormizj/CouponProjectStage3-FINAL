package com.jbc.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbc.model.user.Admin;

/**
 * {@link com.jbc.model.user.Admin} {@code JpaRepository} that is used to manage
 * the admins in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Admin
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

	/* methods */
	public Optional<Admin> findByEmailIgnoreCaseAndPassword(String email, String password);

	public Optional<Admin> findByIdNotAndEmailIgnoreCase(long id, String email);

	public Optional<Admin> findByEmailIgnoreCase(String email);

}
