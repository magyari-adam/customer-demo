package hu.dpc.demo.model;

import hu.dpc.demo.model.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @PositiveOrZero
    @Column(nullable = false)
    private int age;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Length(max = 200)
    @Column(length = 200)
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
