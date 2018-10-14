package com.macys.mtech.appconfig.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.macys.mtech.appconfig.model.AppConfig;

@Repository
public interface AppConfigRepository extends  RevisionRepository<AppConfig, Long, Integer>, JpaRepository<AppConfig, Long> {

	Optional<AppConfig> findByAppNameAndModuleAndConfigTypeAndConfigKey(String appName, String module, String configType, String configKey);
	
	Optional<List<AppConfig>> findByAppNameAndModuleAndConfigType(String appName, String module, String configType);
	
	Optional<List<AppConfig>> findByAppNameAndModule(String appName, String module);
	
	Optional<List<AppConfig>> findByAppName(String appName);
}
