package hu.dpc.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.dpc.demo.converter.CustomerAuditMapper;
import hu.dpc.demo.dto.CustomerAuditDto;
import hu.dpc.demo.dto.CustomerDto;
import hu.dpc.demo.model.CustomerAudit;
import hu.dpc.demo.model.enums.AuditAction;
import hu.dpc.demo.model.enums.AuditActionStatus;
import hu.dpc.demo.repository.CustomerAuditRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerAuditService {

    private final CustomerAuditRepository customerAuditRepository;

    private final CustomerAuditMapper customerAuditMapper;

    private final ObjectMapper objectMapper;

    public CustomerAuditService(CustomerAuditRepository customerAuditRepository, CustomerAuditMapper customerAuditMapper, ObjectMapper objectMapper) {
        this.customerAuditRepository = customerAuditRepository;
        this.customerAuditMapper = customerAuditMapper;
        this.objectMapper = objectMapper;
    }

    public void saveAudit(Long id, CustomerDto customerDto, AuditActionStatus status, AuditAction action) {
        final CustomerAudit customerAudit = new CustomerAudit();
        customerAudit.setCustomerId(id);
        customerAudit.setStatus(status);
        customerAudit.setAction(action);
        if (customerDto != null) {
            try {
                customerAudit.setRequest(objectMapper.writeValueAsString(customerDto));
            } catch (JsonProcessingException e) {
                //ignored
            }
        }

        customerAuditRepository.save(customerAudit);
    }

    public Page<CustomerAuditDto> getCustomerAudits(Pageable pageable) {
        final Page<CustomerAudit> customerAuditPage = customerAuditRepository.findAll(pageable);
        final Page<CustomerAuditDto> customerAuditDtoPage = customerAuditPage.map(customerAuditMapper::toCustomerAuditDto);
        return customerAuditDtoPage;
    }
}
