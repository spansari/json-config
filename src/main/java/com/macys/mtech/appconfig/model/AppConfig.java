package com.macys.mtech.appconfig.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
@Audited
public class AppConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "APP_NAME")
	private String appName;
	
	@Column(name = "MODULE")
	private String module;
	
	@Column(name = "CONFIG_TYPE")
	private String configType;
	
	@Column(name = "CONFIG_KEY")
	private String configKey;
	
	@Column(name = "CONFIG_VALUE", nullable = true)
	private String configValue;

}
