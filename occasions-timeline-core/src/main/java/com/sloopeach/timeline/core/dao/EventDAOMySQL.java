package com.sloopeach.timeline.core.dao;

import com.sloopeach.timeline.core.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class EventDAOMySQL implements EventDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Event save(final Event event) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection)-> {
                String sql = "INSERT INTO EVENT (NAME, DATE, DESCRIPTION) VALUES (?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, event.getName());
                ps.setObject(2, event.getDate().toString());
                ps.setString(3, event.getDescription());
                return ps;
        }, keyHolder);

        event.setId(keyHolder.getKey().intValue());
        return event;

    }

    public Event getByID(Integer id) {
        String sql = "SELECT EVENT.ID, EVENT.NAME, EVENT.DATE, EVENT.DESCRIPTION "
                +    "FROM EVENT WHERE EVENT.ID = ? ";

        Event event =  (Event)jdbcTemplate.queryForObject(
                sql, new Object[]{id}, new BeanPropertyRowMapper(Event.class));

        return event;
    }

    public Event getByName(String name) {
        String sql = "SELECT EVENT.ID, EVENT.NAME, EVENT.DATE, EVENT.DESCRIPTION "
                +    "FROM EVENT WHERE EVENT.NAME = ? ";

        Event event =  (Event)jdbcTemplate.queryForObject(
                sql, new Object[]{name}, new BeanPropertyRowMapper(Event.class));

        return event;
    }

    public List<Event> findAll() {
        List <Event> events = jdbcTemplate.query("SELECT * FROM EVENT", new BeanPropertyRowMapper(Event.class));
        return events;
    }
}
