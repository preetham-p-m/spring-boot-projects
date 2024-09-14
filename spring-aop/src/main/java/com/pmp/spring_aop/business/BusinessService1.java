package com.pmp.spring_aop.business;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.pmp.spring_aop.aop.annotation.TrackTime;
import com.pmp.spring_aop.data.DataRepo1;

@Service
public class BusinessService1 {

    private DataRepo1 dataService1;

    public BusinessService1(DataRepo1 dataService1) {
        this.dataService1 = dataService1;
    }

    @TrackTime
    public int calculateMax() {
        int[] data = dataService1.getData();
        return Arrays.stream(data).max().orElse(0);
    }

}
