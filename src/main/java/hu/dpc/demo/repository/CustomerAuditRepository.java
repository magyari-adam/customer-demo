package hu.dpc.demo.repository;

import hu.dpc.demo.model.CustomerAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAuditRepository extends JpaRepository<CustomerAudit, Long> {
}
