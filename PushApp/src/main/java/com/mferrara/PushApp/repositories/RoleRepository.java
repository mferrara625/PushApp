package com.mferrara.PushApp.repositories;

import com.mferrara.PushApp.auth.ERole;
import com.mferrara.PushApp.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
