package com.example.myboard.repository;

import com.example.myboard.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//データベースと直接コミュニケーションする階層
public interface UserRepository extends JpaRepository<SiteUser,Long> {
    Optional<SiteUser> findByusername(String username);
}
