package com.wyl.webdemo.aspect.rabbitmq.listener;


import com.rabbitmq.client.Channel;
import com.wyl.webdemo.entity.local.OpLog;
import com.wyl.webdemo.entity.local.OperationRecord;
import com.wyl.webdemo.service.local.OpLogService;
import com.wyl.webdemo.service.local.OperationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.wyl.webdemo.common.constant.RabbitmqQueues.DIRECT_QUEUE;

@Component
@Slf4j
@EnableScheduling
@Async
@RabbitListener(queues = DIRECT_QUEUE)
public class Receiver {

    /**
     *  用来存储操作记录
     */
    private static List<OperationRecord> operationRecordList = new ArrayList<>();

    /**
     * 用来存储消息index
     */
    private static List<Long> tagList = new ArrayList<>();

    @Autowired
    private OperationRecordService operationRecordService;

    @Autowired
    private OpLogService opLogService;

    @Resource
    private RedisTemplate redisTemplate;

    private Channel channel;

    @RabbitHandler
    public void process(OperationRecord operationRecord, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) Long tag){
        operationRecordList.add(operationRecord);
        tagList.add(tag);
        if(this.channel == null) {
            this.channel = channel;
            log.info("channel={}", channel);
        }
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void batchSaveOpRecord(){
        log.info("[task] " + Thread.currentThread().getName() + "rabbitmq thread");
        if(CollectionUtils.isEmpty(operationRecordList)){
            return;
        }
        try {
            operationRecordService.insertBatch(operationRecordList);
            for (Long index : tagList) {
                // 确认消息
                channel.basicAck(index, false);
            }
            log.info("[task] " + Thread.currentThread().getName() + "execute ack tagList={}", operationRecordList, tagList);
            operationRecordList.clear();
            tagList.clear();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void batchSaveOpRecord4Redis(){
        // 取出当前redis所有的op
        List<OpLog> redisOpRecords = redisTemplate.opsForList().range("operation_records", 0, -1);
        if(CollectionUtils.isEmpty(redisOpRecords)){
            return;
        }
        System.out.println(redisTemplate.opsForList().range("operation_records", 0, -1));
        // 记录当前取出记录的大小
        Integer currentSize = redisOpRecords.size();
        log.info("[task] " + Thread.currentThread().getName() + "execute insert opList={}", redisOpRecords);
        opLogService.insertBatch(redisOpRecords);
        redisTemplate.opsForList().trim("operation_records", currentSize, -1);
        System.out.println(redisTemplate.opsForList().range("operation_records", 0, -1));
    }



}
