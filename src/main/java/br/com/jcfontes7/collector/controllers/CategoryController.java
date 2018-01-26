package br.com.jcfontes7.collector.controllers;

import br.com.jcfontes7.collector.controllers.parameters.CategoryParameter;
import br.com.jcfontes7.collector.models.Category;
import br.com.jcfontes7.collector.presenters.CategoryPresenter;
import br.com.jcfontes7.collector.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/filter")
    public List<CategoryPresenter> findByContainingName(@RequestParam("name") String name) {
        List<Category> categories = this.service.findNameContaining(name);

        if (categories != null && !categories.isEmpty()) {
            List<CategoryPresenter> presenters = new ArrayList<>();
            for (Category category : categories) {
                presenters.add(new CategoryPresenter(category));
            }
            return presenters;
        }
        return null;
    }

    @GetMapping
    public List<CategoryPresenter> findAll() {
        List<Category> categories = this.service.findAll();

        if (categories != null && !categories.isEmpty()) {
            List<CategoryPresenter> presenters = new ArrayList<>();
            for (Category category : categories) {
                presenters.add(new CategoryPresenter(category));
            }
            return presenters;
        }
        return null;
    }

    @GetMapping("/{id}")
    public CategoryPresenter findById(@PathVariable("id") String id) {
        Category category = this.service.findById(id);
        return category != null ? new CategoryPresenter(category) : null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody CategoryParameter category) {
        this.service.save(category.convert());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id, @RequestBody CategoryParameter parameter) {
        this.service.update(parameter.convert(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.service.delete(id);
    }
}