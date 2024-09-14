package com.pmp.spring_aop.data;

import org.springframework.stereotype.Repository;

@Repository
public class DataRepo1 {

    public int[] getData() {
        return new int[] { 1, 2, 3 };
    }
}
