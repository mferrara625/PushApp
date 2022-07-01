package com.mferrara.PushApp.repositories;

import com.mferrara.PushApp.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    @Query("SELECT c FROM Content c WHERE c.text LIKE %?1%")
    List<Content> findByTextLike(String text);


}
