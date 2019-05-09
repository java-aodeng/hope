package com.hope.model;

import java.time.LocalDateTime;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-09 17:15
 **/
public class TestEntity {
    private String name;

    public LocalDateTime getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(LocalDateTime dateTimes) {
        this.dateTimes = dateTimes;
    }

    private LocalDateTime dateTimes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
