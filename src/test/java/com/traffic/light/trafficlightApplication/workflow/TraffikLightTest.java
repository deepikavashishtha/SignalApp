package com.traffic.light.trafficlightApplication.workflow;

import com.traffic.light.trafficlightApplication.TestTrafficLightApplication;
import com.traffic.light.trafficlightApplication.common.TrafficLightEvent;
import com.traffic.light.trafficlightApplication.common.TrafficLightState;
import com.traffic.light.trafficlightApplication.entity.TrafficHour;
import com.traffic.light.trafficlightApplication.entity.TrafficLightSchedule;
import com.traffic.light.trafficlightApplication.repo.TrafficLightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestTrafficLightApplication.class, TrafficLightConfiguration.class, TrafficSignalTimer.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class TraffikLightTest {

    @Autowired
    private StateMachine<TrafficLightState, TrafficLightEvent> stateMachine;

    @MockBean
    TrafficLightRepository trafficLightRepository;

    @Mock
    TrafficLightSchedule trafficLightSchedule;
    @Mock
    TrafficHour trafficHour;

    @Test
    public void testInitialStateIsRed() throws Exception {
        assertThat(stateMachine.getInitialState().getId(), is(TrafficLightState.CLEAR));
    }

    @Test
    public void testInitial() throws Exception {
        assertThat(stateMachine.getStates().size(), is(4));
    }

    @Test
    public void test_statemachine_states() throws Exception {

        LocalDateTime now = LocalDateTime.now();
        int currentTime = now.getHour()* 100 + now.getMinute();

        when(trafficLightSchedule.getFromHour()).thenReturn(currentTime - 100);
        when(trafficLightSchedule.getEndHour()).thenReturn(currentTime + 100);
        when(trafficLightSchedule.getTrafficHour()).thenReturn(trafficHour);

        when(trafficLightRepository.findAllByDayOfWeek(anyString())).thenReturn(Arrays.asList(trafficLightSchedule));
        when(trafficHour.getName()).thenReturn("NORMAL");
        when(trafficHour.getRedDuration()).thenReturn(1);
        when(trafficHour.getGreenDuration()).thenReturn(2);
        when(trafficHour.getAmberDuration()).thenReturn(4);


        new Thread(new Runnable() {
            @Override
            public void run() {
                stateMachine.start();
                stateMachine.sendEvent(TrafficLightEvent.KICK_OFF);
            }
        } ).start();

        Thread.sleep(1000);
        assertThat(stateMachine.getState().getId(), is(TrafficLightState.RED));
        Thread.sleep(2000);
        assertThat(stateMachine.getState().getId(), is(TrafficLightState.GREEN));
        Thread.sleep(4000);
        assertThat(stateMachine.getState().getId(), is(TrafficLightState.AMBER));
        stateMachine.sendEvent(TrafficLightEvent.SHUT_DOWN);
        stateMachine.stop();
    }
}
