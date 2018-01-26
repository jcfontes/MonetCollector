package br.com.jcfontes7.collector.services;

import br.com.jcfontes7.collector.models.Category;
import br.com.jcfontes7.collector.models.Item;
import br.com.jcfontes7.collector.repositories.CategoryRepository;
import br.com.jcfontes7.collector.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Item> findAll() {
        return this.repository.findAll();
    }

    public List<Item> findNameContaining(String name){
        return this.repository.findByNameContaining(name);
    }

    public Item findById(String id) {
        return this.repository.findOne(id);
    }

    public void save(Item item){
        Category category = this.categoryRepository.findOne(item.getCategory().getId());
        item.setCategory(category);
        this.repository.save(item);
    }

    public void delete(String id){
        Item item = this.repository.findOne(id);

        if(item != null) {
            this.repository.delete(item);
        }
    }

    public void update(Item item) {
        Item saved = this.repository.findOne(item.getId());

        if(saved != null) {
            saved.update(item);
            this.repository.save(saved);
        }
    }
}
