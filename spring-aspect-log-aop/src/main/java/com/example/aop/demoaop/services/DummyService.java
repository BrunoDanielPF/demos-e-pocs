package com.example.aop.demoaop.services;

import com.example.aop.demoaop.interfaces.AfterLog;
import com.example.aop.demoaop.interfaces.Log;
import org.springframework.stereotype.Service;

@Service
public class DummyService {

    @Log
    public void dummyStuff(){
        System.out.println("In Service");
    }

    @AfterLog
    public void checkStuff() {
        System.out.println("Checking stuff");
    }
}
