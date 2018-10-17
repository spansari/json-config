package com.macys.mtech.appconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;

import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"APP_NAME", "MODULE", "MODULE", "CONFIG_KEY"})
	}) 
@Audited
public class AppConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "APP_NAME", nullable=false)
	private String appName;
	
	@Column(name = "MODULE", nullable=false)
	private String module;
	
	@Column(name = "CONFIG_TYPE", nullable=false)
	private String configType;
	
	@Column(name = "CONFIG_KEY", nullable=false)
	private String configKey;
	
	@Column(name = "CONFIG_VALUE", nullable = false)
	private String configValue;

}
