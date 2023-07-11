package br.com.example.cache.controller;

import br.com.example.cache.cache.CacheStore;
import br.com.example.cache.model.Employee;
import br.com.example.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    CacheStore<Employee> employeeCache;

    @GetMapping("/employee/{id}")
    public Employee searchEmployeeByID(@PathVariable String id) {
        System.out.println("Searching Employee by ID  : " + id);

        //Search Employee record in Cache
        Employee cachedEmpRecord = employeeCache.get(id);
        if(cachedEmpRecord != null) {
            System.out.println("Employee record found in cache : " + cachedEmpRecord.getName());
            return cachedEmpRecord;
        }

        //Fetch Employee details from backend service
        Employee EmpRecordFromBackendService = employeeService.getEmployeeByID(id);

        //Store Employee record in Cache
        employeeCache.add(id, EmpRecordFromBackendService);

        return EmpRecordFromBackendService;
    }

}