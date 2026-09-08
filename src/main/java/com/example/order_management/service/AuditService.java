package com.example.order_management.service;

import com.example.order_management.entity.AuditLog;
import com.example.order_management.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Transactional()
    public void saveAudit(String action,String message){
        AuditLog auditLog = new AuditLog();

        auditLog.setAction(action);
        auditLog.setMessage(message);
        auditLog.setCreatedAt(LocalDateTime.now());

        auditLogRepository.save(auditLog);
        try {
            throw new RuntimeException("Audit failed");
        } catch (RuntimeException e) {

            System.out.println("Audit failed, but continuing order transaction");

        }

    }
}
