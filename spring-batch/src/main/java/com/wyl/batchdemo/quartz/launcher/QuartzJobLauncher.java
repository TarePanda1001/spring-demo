package com.wyl.batchdemo.quartz.launcher;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author weiyilin
 * @date 2019/5/9 下午2:53
 */
@Slf4j
public class QuartzJobLauncher extends QuartzJobBean {

    /*方式一*/
//	private String jobName;
//	private JobLauncher jobLauncher;
//	private JobLocator jobLocator;
//
//	public String getJobName() {
//		return jobName;
//	}
//
//	public void setJobName(String jobName) {
//		this.jobName = jobName;
//	}
//
//	public JobLauncher getJobLauncher() {
//		return jobLauncher;
//	}
//
//	public void setJobLauncher(JobLauncher jobLauncher) {
//		this.jobLauncher = jobLauncher;
//	}
//
//	public JobLocator getJobLocator() {
//		return jobLocator;
//	}
//
//	public void setJobLocator(JobLocator jobLocator) {
//		this.jobLocator = jobLocator;
//	}
//
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        /*方式二*/
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        String jobName = jobDataMap.getString("jobName");
        JobLauncher jobLauncher = (JobLauncher) jobDataMap.get("jobLauncher");
        JobLocator jobLocator = (JobLocator) jobDataMap.get("jobLocator");
        log.info("jobName : " + jobName);
        log.info("jobLauncher : " + jobLauncher);
        log.info("jobLocator : " + jobLocator);
        JobKey key = context.getJobDetail().getKey();
        log.info(key.getName() + " : " + key.getGroup());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("Current time : " + sf.format(new Date()));

        try {
            org.springframework.batch.core.Job job = jobLocator.getJob(jobName);
            JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
