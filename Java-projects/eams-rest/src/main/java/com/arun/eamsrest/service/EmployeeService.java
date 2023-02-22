package com.arun.eamsrest.service;


import com.arun.eamsrest.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


}
