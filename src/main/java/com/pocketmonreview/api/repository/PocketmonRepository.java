package com.pocketmonreview.api.repository;

import com.pocketmonreview.api.models.Pocketmon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PocketmonRepository extends JpaRepository<Pocketmon, Integer> {
}
