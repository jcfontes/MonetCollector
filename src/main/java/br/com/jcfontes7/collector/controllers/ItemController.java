package br.com.jcfontes7.collector.controllers;

import br.com.jcfontes7.collector.controllers.parameters.ItemParameter;
import br.com.jcfontes7.collector.models.Item;
import br.com.jcfontes7.collector.presenters.ItemPresenter;
import br.com.jcfontes7.collector.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping("/filter")
    public List<ItemPresenter> findByContainingName(@RequestParam("name") String name) {
        List<Item> items = this.service.findNameContaining(name);

        if (items != null && !items.isEmpty()) {
            List<ItemPresenter> presenters = new ArrayList<>();
            for (Item item : items) {
                presenters.add(new ItemPresenter(item));
            }
            return presenters;
        }
        return null;
    }

    @GetMapping
    public List<ItemPresenter> findAll() {
        List<Item> items = this.service.findAll();

        if (items != null && !items.isEmpty()) {
            List<ItemPresenter> presenters = new ArrayList<>();
            for (Item item : items) {
                presenters.add(new ItemPresenter(item));
            }
            return presenters;
        }
        return null;
    }

    @GetMapping("/{id}")
    public ItemPresenter findById(@PathVariable("id") String id) {
        Item item = this.service.findById(id);
        return new ItemPresenter(item);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ItemParameter item) {
        this.service.save(item.convert());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id, @RequestBody ItemParameter parameter) {
        this.service.update(parameter.convert(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.service.delete(id);
    }
}