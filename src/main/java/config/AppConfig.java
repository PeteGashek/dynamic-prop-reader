package config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import proploader.DynamicPropertiesFileReaderTask;

import java.util.HashMap;

/**
 * Created by ghost on 3/25/18.
 */
@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public PropertiesFactoryBean CoreDynamicPropertiesBean(){
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new FileSystemResource("/Users/ghost/Desktop/dynaProp.properties"));// osx path /Users/ghost/Desktop/dynaProp.properties
        return propertiesFactoryBean;
    }

    @Bean
    public HashMap DynamicPropertiesMap(){
        return new HashMap();
    }

    @Bean
    public DynamicPropertiesFileReaderTask DynamicPropertiesFileReaderTask(){
        DynamicPropertiesFileReaderTask dynamicPropertiesFileReaderTask = new DynamicPropertiesFileReaderTask();
        dynamicPropertiesFileReaderTask.setDynamicPropertiesMap(DynamicPropertiesMap());
        return dynamicPropertiesFileReaderTask;
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean DynamicPropertiesFileReaderTaskJobDetail(){
        MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        methodInvokingJobDetailFactoryBean.setTargetBeanName("DynamicPropertiesFileReaderTask");
        methodInvokingJobDetailFactoryBean.setTargetMethod("start");
        return methodInvokingJobDetailFactoryBean;

    }
    @Bean
    public SimpleTriggerFactoryBean DynamicPropertiesFileReaderTaskTrigger(){
        SimpleTriggerFactoryBean simpleTriggerBean = new SimpleTriggerFactoryBean();
        simpleTriggerBean.setJobDetail(DynamicPropertiesFileReaderTaskJobDetail().getObject());
        simpleTriggerBean.setRepeatInterval(600L);//misec
        simpleTriggerBean.setStartDelay(0L);
        return  simpleTriggerBean;
    }

    @Bean
    SchedulerFactoryBean SchedulerFactoryBean (){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobDetails(new JobDetail[]{DynamicPropertiesFileReaderTaskJobDetail().getObject()});
        schedulerFactoryBean.setTriggers(new Trigger[]{DynamicPropertiesFileReaderTaskTrigger().getObject()});

        return schedulerFactoryBean;
    }
}
