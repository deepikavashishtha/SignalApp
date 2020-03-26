package com.traffic.light.trafficlightApplication;

import com.traffic.light.trafficlightApplication.common.TrafficLightEvent;
import com.traffic.light.trafficlightApplication.common.TrafficLightState;
import com.traffic.light.trafficlightApplication.workflow.TrafficLightConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
@Import({ TrafficLightConfiguration.class })
@EnableJpaRepositories
@Slf4j
@Profile("!test")
public class TrafficlightApplication implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private StateMachine<TrafficLightState, TrafficLightEvent> signalSystem;

	public static void main(String[] args) {
		SpringApplication.run(TrafficlightApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		log.info("STARTING: Signal Light System");
		signalSystem.start();
		signalSystem.sendEvent(TrafficLightEvent.KICK_OFF);
	}
}

