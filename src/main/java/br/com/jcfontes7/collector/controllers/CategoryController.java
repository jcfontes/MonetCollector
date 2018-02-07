package br.com.jcfontes7.collector.controllers;

import br.com.jcfontes7.collector.controllers.parameters.CategoryParameter;
import br.com.jcfontes7.collector.models.Category;
import br.com.jcfontes7.collector.presenters.CategoryPresenter;
import br.com.jcfontes7.collector.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/filter")
    public ResponseEntity<List<CategoryPresenter>> findByContainingName(@RequestParam("name") String name) {
        List<Category> categories = this.service.findNameContaining(name);

        if (categories != null && !categories.isEmpty()) {
            List<CategoryPresenter> categoriesPresenter = categories.stream()
                    .map(CategoryPresenter::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(categoriesPresenter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CategoryPresenter>> findAll() {
        List<Category> categories = this.service.findAll();

        if (categories != null && !categories.isEmpty()) {
            List<CategoryPresenter> categoriesPresenter = categories.stream()
                    .map(CategoryPresenter::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(categoriesPresenter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryPresenter> findById(@PathVariable("id") String id) {
        Category category = this.service.findById(id);

        return category != null ? new ResponseEntity<>(new CategoryPresenter(category), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody CategoryParameter category) {
        this.service.save(category.convert());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody CategoryParameter parameter) {
        this.service.update(parameter.convert(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.service.delete(id);
    }
}