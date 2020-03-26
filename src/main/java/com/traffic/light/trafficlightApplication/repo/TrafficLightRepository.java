package com.traffic.light.trafficlightApplication.repo;

import com.traffic.light.trafficlightApplication.entity.TrafficLightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrafficLightRepository extends JpaRepository<TrafficLightSchedule, Long>{
	List<TrafficLightSchedule> findAllByDayOfWeek(String dayOfWeek);
}
