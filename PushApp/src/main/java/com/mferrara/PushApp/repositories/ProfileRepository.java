package com.mferrara.PushApp.repositories;

import com.mferrara.PushApp.auth.User;
import com.mferrara.PushApp.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(User user);

}
