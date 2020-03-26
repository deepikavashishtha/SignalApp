package com.traffic.light.trafficlightApplication.workflow;

import com.traffic.light.trafficlightApplication.common.TrafficLightEvent;
import com.traffic.light.trafficlightApplication.common.TrafficLightState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@EnableStateMachine(name = "SignalSystem")
@Configuration
public class TrafficLightConfiguration extends EnumStateMachineConfigurerAdapter<TrafficLightState, TrafficLightEvent>{
	@Autowired
	private TrafficSignalTimer timerAction;

	@Override
	public void configure(StateMachineConfigurationConfigurer<TrafficLightState, TrafficLightEvent> config) throws Exception {
		super.configure(config);
		config.withConfiguration()
				.taskScheduler(new ConcurrentTaskScheduler());
	}

	@Override
	public void configure(StateMachineStateConfigurer<TrafficLightState, TrafficLightEvent> states) throws Exception {
		super.configure(states);
		states
				.withStates()
				.initial(TrafficLightState.CLEAR)
				.state(TrafficLightState.RED)
				.state(TrafficLightState.AMBER)
				.state(TrafficLightState.GREEN)
				.end(TrafficLightState.CLEAR);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<TrafficLightState, TrafficLightEvent> transitions) throws Exception {
		super.configure(transitions);

		transitions.withExternal()
				.source(TrafficLightState.CLEAR)
				.target(TrafficLightState.RED)
				.event(TrafficLightEvent.KICK_OFF)
				.and()
				.withExternal()
				.source(TrafficLightState.RED)
				.target(TrafficLightState.GREEN)
				.action(timerAction)
				.and()
				.withExternal()
				.source(TrafficLightState.GREEN)
				.target(TrafficLightState.AMBER)
				.action(timerAction)
				.and()
				.withExternal()
				.source(TrafficLightState.AMBER)
				.target(TrafficLightState.RED)
				.action(timerAction)
				.and()
				.withExternal()
				.event(TrafficLightEvent.SHUT_DOWN)
				.target(TrafficLightState.CLEAR);
	}

}


