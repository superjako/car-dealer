package com.sg.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

    @Scheduled(cron="*/6 * * * * ?")
    public void process() {
    }

}
