package kz.ermek.dao;

import kz.ermek.models.Book;
import kz.ermek.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from book where id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book(name, author, year_of_publication) values (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear_of_publication());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("update book set name=?, author=?, year_of_publication=? where id=?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear_of_publication(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id=?", id);
    }

    //to join tables and get person whom belong this book with id
    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("select person.* from book join person on book.person_id = person.id " +
                        "where book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    //освобождает книгу
    public void release(int id) {
        jdbcTemplate.update("update book set person_id=null where id=?", id);
    }

    //назначает книгу
    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("update book set person_id=? where id=?", selectedPerson.getId(), id);
    }


}
