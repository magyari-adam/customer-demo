package hu.dpc.demo.service;

import hu.dpc.demo.converter.CustomerMapper;
import hu.dpc.demo.dto.CustomerDto;
import hu.dpc.demo.exception.EntityNotFoundException;
import hu.dpc.demo.model.Customer;
import hu.dpc.demo.repository.CustomerRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private CustomerMapper customerMapper;

    private CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerDto createCustomer(@Valid CustomerDto customer) {
        customer.setId(null);
        return customerMapper.toCustomerDto(customerRepository.save(customerMapper.toCustomer(customer)));
    }

    public CustomerDto getCustomer(@NotNull Long id) {
        final Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return customerMapper.toCustomerDto(optionalCustomer.get());
        } else {
            throw new EntityNotFoundException("Customer with id " + id + " not found");
        }
    }

    public Page<CustomerDto> getCustomers(Pageable pageable) {
        final Page<Customer> customerPage = customerRepository.findAll(pageable);
        final Page<CustomerDto> customerDtoPage = customerPage.map(customerMapper::toCustomerDto);
        return customerDtoPage;
    }

    public CustomerDto updateCustomer(Long id, @Valid CustomerDto customer) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer with id " + customer.getId() + " not found");
        }

        return customerMapper.toCustomerDto(customerRepository.save(customerMapper.toCustomer(customer)));
    }

    public void deleteCustomer(@NotNull Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer with id " + id + " not found");
        }

        customerRepository.deleteById(id);
    }
}
