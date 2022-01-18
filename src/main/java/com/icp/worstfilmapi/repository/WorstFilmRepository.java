package com.icp.worstfilmapi.repository;

import com.icp.worstfilmapi.entity.Film;
import com.icp.worstfilmapi.entity.Producer;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorstFilmRepository extends JpaRepository<Film, Long> {

}
