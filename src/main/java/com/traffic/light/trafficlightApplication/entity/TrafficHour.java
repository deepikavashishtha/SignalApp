package com.traffic.light.trafficlightApplication.entity;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity 
@Table(name="TRAFFIC_HOUR")
@ToString(includeFieldNames = true)
@Getter
public class TrafficHour implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="RED_DURATION")
	private int redDuration;

	@Column(name="GREEN_DURATION")
	private int greenDuration;

	@Column(name="AMBER_DURATION")
	private int amberDuration;
}
