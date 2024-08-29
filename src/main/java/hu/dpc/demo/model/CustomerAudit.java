package hu.dpc.demo.model;

import hu.dpc.demo.model.enums.AuditAction;
import hu.dpc.demo.model.enums.AuditActionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class CustomerAudit {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(nullable = false)
    private AuditAction action;

    private Long customerId;

    private String request;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditActionStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public AuditAction getAction() {
        return action;
    }

    public void setAction(AuditAction action) {
        this.action = action;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public AuditActionStatus getStatus() {
        return status;
    }

    public void setStatus(AuditActionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void updateCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
