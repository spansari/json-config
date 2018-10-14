package com.macys.mtech.appconfig.model.listener;

import java.time.LocalDateTime;

import org.hibernate.envers.RevisionListener;
import org.slf4j.MDC;

import com.macys.mtech.appconfig.model.AuditRevisionEntity;

public class AuditRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity auditRevisionEntity = (AuditRevisionEntity) revisionEntity;
        auditRevisionEntity.setUpdatedBy(MDC.get("UPDATED_BY"));
        auditRevisionEntity.setUpdatedAt(LocalDateTime.now());
    }
}