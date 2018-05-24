package com.sloopeach.timeline.core.dao;

import com.sloopeach.timeline.core.config.ApplicationConfig;
import com.sloopeach.timeline.core.domain.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ApplicationConfig.class})
public class EventDAOMySQLTest {

    @Autowired
    EventDAOMySQL eventDAOMySQL;

    @Test
    public void testSave(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime testDate = LocalDateTime.parse("2001-06-16 00:00:00", formatter);
        System.out.println(testDate);
        Event testEvent  = new Event( "TestEvent1", "Some event ", testDate);
        System.out.println(testEvent.getDate());
        eventDAOMySQL.save(testEvent);
    }
}
