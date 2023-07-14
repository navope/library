package ru.navope.rento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.navope.rento.dao.BookDAO;
import ru.navope.rento.dao.PersonDAO;
import ru.navope.rento.models.Book;
import ru.navope.rento.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book",bookDAO.getBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         @PathVariable("id") int id) {

        bookDAO.update(book, id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping()
    public String showBooks(Model model){
        model.addAttribute("books", bookDAO.getBooks());
        return "books/showBooks";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") int id){
        Book book = bookDAO.getBook(id);
        Person person = bookDAO.getPerson(id);
        model.addAttribute("book", book);
        model.addAttribute("person", person);
        return "books/show";
    }

    @PatchMapping("/{id}/free")
    public String toFree(@PathVariable("id") int id){
        bookDAO.toFree(id);
        return "redirect:/books/{id}";
    }


}
