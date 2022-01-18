package com.icp.worstfilmapi.repository;

import com.icp.worstfilmapi.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorstStudioRepository  extends JpaRepository<Studio, Long> {
    Studio findByName(String name);
}
