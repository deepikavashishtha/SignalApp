package com.traffic.light.trafficlightApplication.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.ToString;


@Entity 
@Table(name="TRAFFIC_LIGHT_SCHEDULE")
@ToString(includeFieldNames = true)
@Getter
public class TrafficLightSchedule implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name="traffic_hours_id", referencedColumnName = "ID", nullable = false)
	private TrafficHour trafficHour;

	@Column(name="day_of_week")
	private String dayOfWeek;

	@Column(name="FROMHOUR")
	private int fromHour;

	@Column(name="ENDHOUR")
	private int endHour;

}
