package com.jbc.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbc.model.user.Company;

/**
 * {@link com.jbc.model.user.Company} {@code JpaRepository} that is used to
 * manage the companies in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Company
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

	/* methods */
	public Optional<Company> findByIdNotAndNameIgnoreCase(long id, String name);

	public Optional<Company> findByIdNotAndEmailIgnoreCase(long id, String email);

	public Optional<Company> findByEmailIgnoreCaseAndPassword(String email, String password);

	public Optional<Company> findByEmailIgnoreCase(String email);

}
