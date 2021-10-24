package com.jbc.model;

import java.sql.Date;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jbc.util.generalUtil.TimeZoneUtil;
import com.jbc.util.modelUtil.ModelEntityUtil;
import com.jbc.util.serviceUtil.ModelActionUtil;
import com.jbc.util.serviceUtil.UserTypeUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@code Logger} {@code Entity} that is used for creating logs in the system,
 * the logs time is based on a timezone.
 * <p>
 * <li>Timezone is based off the {@link com.jbc.util.generalUtil.TimeZoneUtil}
 * {@code enum}.</li>
 * <p>
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalUtil#TimeZoneUtil
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "logs")
@ApiModel(value = "Log", description = "Logger class, for Log type responses.")
public class Logger implements Comparable<Logger> {

	/* attributes */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@ApiModelProperty(value = "Log ID.")
	private long id;

	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Who the action affected.")
	private ModelEntityUtil entity;

	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "What was the action.")
	private ModelActionUtil action;

	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Who did the action.\nnull=executiveEntity is not a user.")
	private UserTypeUtil executiveEntity;

	@ApiModelProperty(value = "ID of who did the action.\n0=executiveEntity is not a user.\n-1=executiveEntity doesn't exists.")
	private long executiveId;
	@ApiModelProperty(value = "ID of who the action affected.")
	private long entityId;
	@ApiModelProperty(value = "Attributes before the action.")
	private String oldAttributes;
	@ApiModelProperty(value = "Attributes after the action.")
	private String newAttributes;
	@ApiModelProperty(value = "When the action happened (date).")
	private Date date;
	@ApiModelProperty(value = "When the action happened (time).")
	private LocalTime time;

	/* constructor */
	public Logger(long executiveId, UserTypeUtil executiveEntity, long entityId, ModelEntityUtil entity,
			ModelActionUtil action, String oldAttributes, String newAttributes) {
		this.executiveId = executiveId;
		this.executiveEntity = executiveEntity;
		this.entityId = entityId;
		this.entity = entity;
		this.action = action;
		this.oldAttributes = oldAttributes;
		this.newAttributes = newAttributes;
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of(TimeZoneUtil.ISRAEL.toString()));
		date = Date.valueOf(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		time = LocalTime.parse(now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	}

	@Override
	public int compareTo(Logger o) {
		if (o.getDate().after(getDate()))
			return 1;
		if (o.getDate().equals(getDate()) && o.getTime().isAfter(getTime()))
			return 1;
		return -1;
	}

}
