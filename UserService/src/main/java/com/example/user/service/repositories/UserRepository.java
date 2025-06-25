package com.example.user.service.repositories;

import com.example.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    //coz ID is String type - the second parameter

    //if you want to implement any custom method or query write here

}
