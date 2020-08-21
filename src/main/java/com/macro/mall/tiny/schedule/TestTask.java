package com.macro.mall.tiny.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
    private Logger LOGGER = LoggerFactory.getLogger(TestTask.class);

    /**
     * 启动时执行一次，之后每隔2秒执行一次
     */
    @Scheduled(fixedRate = 1000 * 20)
    private void cancelTimeOutOrderTask() {
        LOGGER.info("cancelTimeOutOrderTask 每隔20秒执行一次，测试执行");
    }
}
