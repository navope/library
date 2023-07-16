package ru.navope.rento.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.navope.rento.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople(){
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id){
        return jdbcTemplate.query("select * from person where id = ?",new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> getPerson(String fullName){
        return jdbcTemplate.query("select * from person where full_name = ?",new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person){
        jdbcTemplate.update("insert into person(full_name, year_birth) values(?, ?)",
                person.getFullName(), person.getYearBirth());
    }

    public void update(Person updatePerson, int id){
        jdbcTemplate.update("update person set full_name=?, year_birth=? where id=?",
                updatePerson.getFullName(), updatePerson.getYearBirth(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from person where id = ?",id);
    }

}
