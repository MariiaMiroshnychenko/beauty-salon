package com.beauty.salon.model.repository;

import com.beauty.salon.model.entity.Email;
import com.beauty.salon.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Integer> {
    Page<Email> findEmailsByClientIdAndStatusIsTrue(User client, Pageable pageable);
    Integer countAllByClientIdAndStatusIsTrue(User client);
}
