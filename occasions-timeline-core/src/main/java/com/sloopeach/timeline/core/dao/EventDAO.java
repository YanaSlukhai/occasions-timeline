package com.sloopeach.timeline.core.dao;

import com.sloopeach.timeline.core.domain.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDAO {
   public Event save(Event event);
   public Event getByName(String name);
   public Event getByID(Integer id);

}
