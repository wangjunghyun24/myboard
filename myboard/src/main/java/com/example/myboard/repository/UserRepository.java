package com.example.myboard.repository;

import com.example.myboard.entity.User;
import org.springframework.data.repository.CrudRepository;

//データベースと直接コミュニケーションする階層
public interface UserRepository extends CrudRepository<User,Long> {
}
