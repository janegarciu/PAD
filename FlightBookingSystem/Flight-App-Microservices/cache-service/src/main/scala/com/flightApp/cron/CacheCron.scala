package com.flightApp.cron

import org.springframework.cache.annotation.CacheEvict
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CacheCron {

  @CacheEvict(allEntries = true, value = Array("flightCache"))
  @Scheduled(fixedDelay = 10000, initialDelay = 10000) def reportCacheEvict(): Unit = {
    System.out.println("Clearing cache")
  }

}
