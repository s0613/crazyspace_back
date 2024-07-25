package com.crazyspace_edu.api.repository;

import com.crazyspace_edu.api.domain.GeneratedAiContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GeneratedAiContentRepository extends JpaRepository<GeneratedAiContent,Long> {
    @Query(value = "SELECT g FROM GeneratedAiContent g ORDER BY g.chg_dt DESC")
    List<GeneratedAiContent> findTop5ByOrderByChgDtDesc();
}
