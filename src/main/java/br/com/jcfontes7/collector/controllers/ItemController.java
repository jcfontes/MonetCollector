package br.com.jcfontes7.collector.controllers;

import br.com.jcfontes7.collector.controllers.parameters.ItemParameter;
import br.com.jcfontes7.collector.models.Item;
import br.com.jcfontes7.collector.presenters.CategoryPresenter;
import br.com.jcfontes7.collector.presenters.ItemPresenter;
import br.com.jcfontes7.collector.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping("/filter")
    public ResponseEntity<List<ItemPresenter>> findByContainingName(@RequestParam("name") String name) {
        List<Item> items = this.service.findNameContaining(name);

        if (items != null && !items.isEmpty()) {
            List<ItemPresenter> itemsPresenter = items.stream()
                    .map(ItemPresenter::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(itemsPresenter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ItemPresenter>> findAll() {
        List<Item> items = this.service.findAll();

        if (items != null && !items.isEmpty()) {
            List<ItemPresenter> itemsPresenter = items.stream()
                    .map(ItemPresenter::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(itemsPresenter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPresenter> findById(@PathVariable("id") String id) {
        Item item = this.service.findById(id);

        return item != null ? new ResponseEntity<>(new ItemPresenter(item), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ItemParameter item) {
        this.service.save(item.convert());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody ItemParameter parameter) {
        this.service.update(parameter.convert(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.service.delete(id);
    }
}