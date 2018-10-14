package com.macys.mtech.appconfig.controller;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.macys.mtech.appconfig.exception.ResourceNotFoundException;
import com.macys.mtech.appconfig.model.AppConfig;
import com.macys.mtech.appconfig.repository.AppConfigRepository;


@RestController
@RequestMapping("/api")
public class AppConfigController {

    @Autowired
    AppConfigRepository appConfigRepository;

    @GetMapping("/appconfigs")
    public List<AppConfig> getAllAppConfigs() {
        return appConfigRepository.findAll();
    }

    @GetMapping("/appconfigs/{id}")
    public AppConfig getAppConfigById(@PathVariable(value = "id") Long id) {
        return appConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AppConfig", "id", id));
    }

    @GetMapping("/appconfigs/app/{appName}/module/{moduleName}/configType/{configType}/configKey/{configKey}")
    public AppConfig getAppConfigByAppNameAndModuleAndConfigTypeAndConfigKey(@PathVariable(value = "appName") String appName,
    		@PathVariable(value = "moduleName") String module, 
    		@PathVariable(value = "configType") String configType,
    		@PathVariable(value = "configKey") String configKey) {
        return appConfigRepository.findByAppNameAndModuleAndConfigTypeAndConfigKey(appName, module, configType, configKey)
                .orElseThrow(() -> new ResourceNotFoundException("AppConfig", "appName, module, configType, configKey", appName + module+ configType + configKey));
    }

    @GetMapping("/appconfigs/app/{appName}/module/{moduleName}/configType/{configType}")
    public List<AppConfig> getAppConfigByAppNameAndModuleAndConfigType(@PathVariable(value = "appName") String appName,
    		@PathVariable(value = "moduleName") String module, 
    		@PathVariable(value = "configType") String configType) {
        return appConfigRepository.findByAppNameAndModuleAndConfigType(appName, module, configType)
        		.orElseThrow(() -> new ResourceNotFoundException("AppConfig", "appName, module, configType", appName + module+ configType));
    }
    
    @GetMapping("/appconfigs/app/{appName}/module/{moduleName}")
    public List<AppConfig> getAppConfigByAppNameAndModule(@PathVariable(value = "appName") String appName,
    		@PathVariable(value = "moduleName") String module) {
        return appConfigRepository.findByAppNameAndModule(appName, module)
        		.orElseThrow(() -> new ResourceNotFoundException("AppConfig", "appName, module", appName + module));
        		
    }

    @GetMapping("/appconfigs/app/{appName}")
    public List<AppConfig> getAppConfigByAppName(@PathVariable(value = "appName") String appName) {
        return appConfigRepository.findByAppName(appName)
                .orElseThrow(() -> new ResourceNotFoundException("AppConfig", "appName", appName));
    }
    
    
    @PostMapping("/appconfigs/app/{appName}/module/{moduleName}/configType/{configType}/configKey/{configKey}")
    public AppConfig createAppConfig(@PathVariable(value = "appName") String appName,
    		@PathVariable(value = "moduleName") String module, 
    		@PathVariable(value = "configType") String configType,
    		@PathVariable(value = "configKey") String configKey,
    		@Valid @RequestBody String configValue,
    		@RequestParam("userName") String userName) {
    	AppConfig appConfig = new AppConfig();
    	appConfig.setAppName(appName);
    	appConfig.setModule(module);
    	appConfig.setConfigType(configType);
    	appConfig.setConfigKey(configKey);
    	appConfig.setConfigValue(configValue);
    	
    	MDC.put("UPDATED_BY", userName);
    	
        return appConfigRepository.save(appConfig);
    }

    
    @PutMapping("/appconfigs/app/{appName}/module/{moduleName}/configType/{configType}/configKey/{configKey}")
    public AppConfig updateAppConfig(@PathVariable(value = "appName") String appName,
    		@PathVariable(value = "moduleName") String module, 
    		@PathVariable(value = "configType") String configType,
    		@PathVariable(value = "configKey") String configKey,
    		@Valid @RequestBody String configValue,
    		@RequestParam("userName") String userName) {
    	
    	AppConfig appConfig = appConfigRepository.findByAppNameAndModuleAndConfigTypeAndConfigKey(appName, module, configType, configKey)
    			.orElseThrow(() -> new ResourceNotFoundException("AppConfig", "appName, module, configType, configKey", appName + module+ configType + configKey));
    	
    	appConfig.setAppName(appName);
    	appConfig.setModule(module);
    	appConfig.setConfigType(configType);
    	appConfig.setConfigKey(configKey);
    	appConfig.setConfigValue(configValue);

    	MDC.put("UPDATED_BY", userName);
    	
        AppConfig updatedAppConfig = appConfigRepository.save(appConfig);
        return updatedAppConfig;
    }

    @DeleteMapping("/appconfigs/{id}")
    public ResponseEntity<?> deleteAppConfig(@PathVariable(value = "id") Long id) {
        AppConfig note = appConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AppConfig", "id", id));

        appConfigRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
