package com.icp.worstfilmapi.repository;

import com.icp.worstfilmapi.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorstProducerRepository  extends JpaRepository<Producer, Long> {

    Producer findByName(String name);


}
