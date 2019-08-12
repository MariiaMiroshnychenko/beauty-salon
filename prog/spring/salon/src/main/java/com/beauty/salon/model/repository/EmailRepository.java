package com.beauty.salon.model.repository;

import com.beauty.salon.model.entity.Email;
import com.beauty.salon.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Integer> {
    List<Email> findEmailsByClientIdAndStatusIsTrue(User client);
    Integer countAllByClientIdAndStatusIsTrue(User client);
}
