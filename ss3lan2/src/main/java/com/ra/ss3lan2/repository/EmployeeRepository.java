package com.ra.ss3lan2.repository;

import com.ra.ss3lan2.model.dto.EmployeeDTO;
import com.ra.ss3lan2.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByPhoneNumber(String phoneNumber);
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeeBySalary(@Param("salary") Double salary);

    @Query("SELECT new com.ra.ss3lan2.model.dto.EmployeeDTO(e.id, e.name, e.email) FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTOs();

    List<EmployeeInfo> findAllBy();

    Page<Employee> findByPhoneNumberContaining(String phoneNumber, Pageable pageable);
}