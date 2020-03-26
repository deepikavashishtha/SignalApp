package com.traffic.light.trafficlightApplication.workflow;

import com.traffic.light.trafficlightApplication.common.TrafficLightEvent;
import com.traffic.light.trafficlightApplication.common.TrafficLightState;
import com.traffic.light.trafficlightApplication.entity.TrafficHour;
import com.traffic.light.trafficlightApplication.entity.TrafficLightSchedule;
import com.traffic.light.trafficlightApplication.repo.TrafficLightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class TrafficSignalTimer implements Action<TrafficLightState, TrafficLightEvent> {
    private static final int _1000 = 1000;

    @Value("${trafficlight.timer.interval:2000}")
    private int defaultInverval;
    @Autowired
    private TrafficLightRepository trafficLightRepository;

    @Override
    public void execute(StateContext<TrafficLightState, TrafficLightEvent> context) {
        final String today = getCurrentDay();
        List<TrafficLightSchedule> scheduleOfTheDay = trafficLightRepository.findAllByDayOfWeek(today);
        TrafficLightSchedule currentScheduleOfToday = getCurrenntSchedule(scheduleOfTheDay);
        TrafficHour trafficHour = currentScheduleOfToday.getTrafficHour();

        int timeToKeepLightOn = defaultInverval;

        switch (context.getSource().getId().name()) {
            case "GREEN" : timeToKeepLightOn = trafficHour.getGreenDuration(); break;
            case "RED" : timeToKeepLightOn = trafficHour.getRedDuration(); break;
            case "AMBER" : timeToKeepLightOn = trafficHour.getAmberDuration(); break;
            default:
        }

        try {
            log.info("Today is {}, Traffic hour is {}, Keeping {} Light on for {} seconds" , today , trafficHour.getName(), context.getSource().getId() , timeToKeepLightOn);
            Thread.sleep(timeToKeepLightOn * _1000);
        } catch (InterruptedException e) {
            log.error("Unable to sleep....", e);
        }
    }

    private String getCurrentDay() {
        return LocalDateTime.now().getDayOfWeek().name();
    }
    private TrafficLightSchedule getCurrenntSchedule(List<TrafficLightSchedule> scheduleOfTheDay)
    {
        LocalDateTime now = LocalDateTime.now();
        int currentTime = now.getHour()* 100 + now.getMinute();
        return scheduleOfTheDay.stream().filter(th -> currentTime >= th.getFromHour() && currentTime < th.getEndHour()).findFirst().get();
    }
}