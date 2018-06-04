package com.sloopeach.timeline.core.dao;

import com.sloopeach.timeline.core.config.ApplicationConfig;
import com.sloopeach.timeline.core.domain.Event;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.TestClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ApplicationConfig.class})
public class EventDAOMySQLTest extends TestCase {

    @Autowired
    EventDAOMySQL eventDAOMySQL;

    Event testEvent;

    @Override
    @Before
    public void setUp() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime testDate = LocalDateTime.parse("2001-06-16 00:00:00", formatter);
        testEvent  = new Event( "TestEvent", "Some event ", testDate);
        System.out.println("Setting it up!");
    }

    @Test
    @Transactional
    public void testSave(){
        System.out.println(testEvent.getDescription());
        eventDAOMySQL.save(testEvent);
        assertEquals(eventDAOMySQL.getByName("TestEvent").getDescription(),"Some event " );
    }

    @Test
    @Transactional
    public void testFindAll(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime testDate = LocalDateTime.parse("2001-06-16 00:00:00", formatter);
        Event testEvent1  = new Event( "TestEvent1", "Some other event ", testDate);
        eventDAOMySQL.save(testEvent);
        eventDAOMySQL.save(testEvent1);

        List<Event> testEvents  = eventDAOMySQL.findAll();
        System.out.println(testEvents.get(1).getName());
        assertEquals(testEvents.size(), 2);
    }




}
