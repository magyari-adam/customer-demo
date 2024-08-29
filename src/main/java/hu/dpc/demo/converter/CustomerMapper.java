package hu.dpc.demo.converter;

import hu.dpc.demo.dto.CustomerDto;
import hu.dpc.demo.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDto customerDto);

    CustomerDto toCustomerDto(Customer customer);
}
