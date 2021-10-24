package com.jbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbc.model.Logger;

/**
 * {@link com.jbc.model.Logger} {@code JpaRepository} that is used to manage the
 * logs in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see model#Logger
 */
public interface LoggerRepository extends JpaRepository<Logger, Long> {

}
