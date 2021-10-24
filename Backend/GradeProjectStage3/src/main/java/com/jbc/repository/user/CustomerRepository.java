package com.jbc.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbc.model.user.Customer;

/**
 * {@link com.jbc.model.user.Customer} {@code JpaRepository} that is used to
 * manage the customers in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Customer
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/* methods */
	public Optional<Customer> findByIdNotAndEmailIgnoreCase(long id, String email);

	public Optional<Customer> findByEmailIgnoreCaseAndPassword(String email, String password);

	public Optional<Customer> findByEmailIgnoreCase(String email);

}
