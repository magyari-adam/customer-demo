package hu.dpc.demo.converter;

import hu.dpc.demo.dto.CustomerAuditDto;
import hu.dpc.demo.model.CustomerAudit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAuditMapper {
    CustomerAuditDto toCustomerAuditDto(CustomerAudit customerAudit);
}
