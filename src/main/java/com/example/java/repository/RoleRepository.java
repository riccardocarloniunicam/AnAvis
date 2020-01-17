package com.example.java.repository;



import com.example.java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.java.model.Role;
import org.springframework.stereotype.Repository;


@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}