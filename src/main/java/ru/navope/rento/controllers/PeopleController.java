package ru.navope.rento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.navope.rento.dao.BookDAO;
import ru.navope.rento.dao.PersonDAO;
import ru.navope.rento.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person",personDAO.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         @PathVariable("id") int id) {

        personDAO.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

    @GetMapping()
    public String showPeople(Model model){
        model.addAttribute("people", personDAO.getPeople());
        return "people/showPeople";
    }

    @GetMapping("/{id}")
    public String showPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("books", bookDAO.getPersonBooks(id));
        return "people/show";
    }
}
