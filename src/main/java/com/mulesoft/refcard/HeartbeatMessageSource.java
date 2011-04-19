package com.mulesoft.refcard;


import org.mule.api.annotations.Schedule;

import java.util.Date;

public class HeartbeatMessageSource  {

    @Schedule(interval = 1000)
    public long pulse() {
        return new Date().getTime();

    }
}
