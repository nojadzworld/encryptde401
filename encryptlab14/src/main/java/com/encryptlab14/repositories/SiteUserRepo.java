package com.encryptlab14.repositories;

import com.encryptlab14.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepo extends JpaRepository<SiteUser, Long> {
    public SiteUser getSiteUserByUsername(String username);
}
