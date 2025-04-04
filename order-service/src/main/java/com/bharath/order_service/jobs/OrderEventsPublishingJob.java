package com.bharath.order_service.jobs;

import com.bharath.order_service.domain.OrderEventService;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
class OrderEventsPublishingJob {
    private static final Logger log = LoggerFactory.getLogger(OrderEventsPublishingJob.class);

    private final OrderEventService orderEventService;

    OrderEventsPublishingJob(OrderEventService orderEventService) {
        this.orderEventService = orderEventService;
    }

    //If there is no shedlock then this job will run in all the instances of the application. We might have multiple instances and use
    //load balancer to distribute the load. So if we have 3 instances then this job will run in all the 3 instances. This should not
    //happen. So we need to use shedlock to make sure that this job runs only in one instance.
    @Scheduled(cron = "*/5 * * * * *")
    @SchedulerLock(name = "publishOrderEvents")
    public void publishOrderEvents() {
        LockAssert.assertLocked();
        log.info("Publishing Order Events at {}", Instant.now());
        orderEventService.publishOrderEvents();
    }
}