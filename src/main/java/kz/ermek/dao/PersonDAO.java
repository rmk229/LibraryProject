package kz.ermek.dao;

import kz.ermek.models.Book;
import kz.ermek.models.Person;
import kz.ermek.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from person where id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(fio, year) values(?, ?)", person.getFio(),
                person.getYear());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update person set fio=?, year=? where id=?",
                updatedPerson.getFio(), updatedPerson.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }

    public Optional<Person> getPersonByFio(String fio) {
        return jdbcTemplate.query("select * from person where fio=?",
                        new Object[]{fio}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public List<Book> getBooksById(int id){
        return jdbcTemplate.query("select * from book where person_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
