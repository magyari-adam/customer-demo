package hu.dpc.demo.dto;

import hu.dpc.demo.dto.enums.AuditAction;
import hu.dpc.demo.dto.enums.AuditActionStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CustomerAuditDto {

    private Long id;

    @NotNull
    private AuditAction action;

    private Long customerId;

    private String request;

    @NotNull
    private AuditActionStatus status;

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
}
