package com.traffic.light.trafficlightApplication.workflow;


import com.traffic.light.trafficlightApplication.common.TrafficLightEvent;
import com.traffic.light.trafficlightApplication.common.TrafficLightState;
import com.traffic.light.trafficlightApplication.entity.TrafficHour;
import com.traffic.light.trafficlightApplication.entity.TrafficLightSchedule;
import com.traffic.light.trafficlightApplication.repo.TrafficLightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.state.State;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrafficSignalTimerTest {

    @Mock
    TrafficLightRepository repository;
    @Mock
    TrafficHour trafficHour;

    @Mock
    TrafficLightSchedule trafficLightSchedule;

    @Mock
    StateContext<TrafficLightState, TrafficLightEvent> context;

    @Mock
    State<TrafficLightState, TrafficLightEvent> state;

    @InjectMocks
    TrafficSignalTimer timer;

    @Before

    public void setup() {
        when(trafficLightSchedule.getTrafficHour()).thenReturn(trafficHour);
        LocalDateTime now = LocalDateTime.now();
        int currentTime = now.getHour()* 100 + now.getMinute();

        when(trafficLightSchedule.getFromHour()).thenReturn(currentTime - 100);
        when(trafficLightSchedule.getEndHour()).thenReturn(currentTime + 100);

        when(repository.findAllByDayOfWeek(anyString())).thenReturn(Arrays.asList(trafficLightSchedule));
        when(context.getSource()).thenReturn(state);
    }

    @Test
    public void test_Red_timer() throws InterruptedException {
        when(trafficHour.getRedDuration()).thenReturn(5);
        when(state.getId()).thenReturn(TrafficLightState.RED);

        timer.execute(context);

        CountDownLatch completedThreadCounter = new CountDownLatch(1);
        completedThreadCounter.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void test_Green_timer() throws InterruptedException {
        //WHEN
        when(trafficHour.getGreenDuration()).thenReturn(2);
        when(state.getId()).thenReturn(TrafficLightState.GREEN);

        //THEN
        timer.execute(context);

        //VERIFY
        CountDownLatch completedThreadCounter = new CountDownLatch(1);
        completedThreadCounter.await(2, TimeUnit.SECONDS);
    }


    @Test
    public void test_Amber_timer() throws InterruptedException {
        when(trafficHour.getAmberDuration()).thenReturn(2);
        when(state.getId()).thenReturn(TrafficLightState.AMBER);

        timer.execute(context);

        CountDownLatch completedThreadCounter = new CountDownLatch(1);
        completedThreadCounter.await(2, TimeUnit.SECONDS);
    }
}
