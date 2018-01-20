package br.com.jcfontes7.collector.controllers;

import br.com.jcfontes7.collector.controllers.parameters.ItemParameter;
import br.com.jcfontes7.collector.models.Item;
import br.com.jcfontes7.collector.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/filter")
    public List<Item> findByContainingName(@RequestParam("name") String name){
        return itemService.findNameContaining(name);
    }

    @GetMapping
    public List<Item> findAll(){
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item findByName(@PathVariable("id") String id) {
        return itemService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Item item){
        itemService.save(item);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id, @RequestBody ItemParameter parameter){
        Item item = parameter.convert(id);
        itemService.update(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        itemService.delete(id);
    }
}