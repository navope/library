package ru.navope.rento.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.navope.rento.models.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks(){
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBook(int id){
        return jdbcTemplate.query("select * from book where id = ?",new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public List<Book> getPersonBooks(int id){
        return jdbcTemplate.query("select * from book where book.person_id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Book book){
        jdbcTemplate.update("insert into book(name, author, year, person_id) values(?, ?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear(), book.getPersonId());
    }

    public void update(Book updateBook, int id){
        jdbcTemplate.update("update book set name=?, author=?, year=?, person_id=? where id=?",
                updateBook.getName(), updateBook.getAuthor(), updateBook.getYear(), updateBook.getPersonId(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from book where id = ?",id);
    }
}
