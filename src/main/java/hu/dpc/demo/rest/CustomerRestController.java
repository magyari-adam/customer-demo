package hu.dpc.demo.rest;

import hu.dpc.demo.dto.CustomerAuditDto;
import hu.dpc.demo.dto.CustomerDto;
import hu.dpc.demo.model.enums.AuditAction;
import hu.dpc.demo.model.enums.AuditActionStatus;
import hu.dpc.demo.service.CustomerAuditService;
import hu.dpc.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    private CustomerService customerService;

    private CustomerAuditService customerAuditService;

    public CustomerRestController(CustomerService customerService, CustomerAuditService customerAuditService) {
        this.customerService = customerService;
        this.customerAuditService = customerAuditService;
    }

    @PostMapping
    @ResponseBody
    public CustomerDto postCreateCustomer(@Valid @RequestBody CustomerDto customerDto) {
        AuditActionStatus auditActionStatus = AuditActionStatus.SUCCESS;
        try {
            return customerService.createCustomer(customerDto);
        } catch (Exception e) {
            auditActionStatus = AuditActionStatus.FAILURE;
            throw e;
        } finally {
            customerAuditService.saveAudit(null, customerDto, auditActionStatus, AuditAction.CREATE);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CustomerDto getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @GetMapping
    @ResponseBody
    public Page<CustomerDto> getCustomers(@PageableDefault(size = 10) Pageable pageable) {
        return customerService.getCustomers(pageable);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public CustomerDto putUpdateCustomer(@Valid @RequestBody CustomerDto customerDto, @PathVariable Long id) {
        AuditActionStatus auditActionStatus = AuditActionStatus.SUCCESS;
        try {
            if (id != customerDto.getId()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "path/body id mismatch");
            }

            return customerService.updateCustomer(id, customerDto);
        } catch (Exception e) {
            auditActionStatus = AuditActionStatus.FAILURE;
            throw e;
        } finally {
            customerAuditService.saveAudit(id, customerDto, auditActionStatus, AuditAction.UPDATE);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        AuditActionStatus auditActionStatus = AuditActionStatus.SUCCESS;
        try {
            customerService.deleteCustomer(id);
        } catch (Exception e) {
            auditActionStatus = AuditActionStatus.FAILURE;
            throw e;
        } finally {
            customerAuditService.saveAudit(id, null, auditActionStatus, AuditAction.DELETE);
        }
    }

    @GetMapping("/audit")
    public Page<CustomerAuditDto> getCustomerAudits(@PageableDefault(size = 100, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return customerAuditService.getCustomerAudits(pageable);
    }
}
