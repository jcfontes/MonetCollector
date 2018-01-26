package br.com.jcfontes7.collector.services;

import br.com.jcfontes7.collector.models.Category;
import br.com.jcfontes7.collector.models.Item;
import br.com.jcfontes7.collector.repositories.CategoryRepository;
import br.com.jcfontes7.collector.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Category> findAll() {
        return this.repository.findAll();
    }

    public List<Category> findNameContaining(String name){
        return this.repository.findByNameContaining(name);
    }

    public Category findById(String id) {
        return this.repository.findOne(id);
    }

    public void save(Category category){
        this.repository.save(category);
    }

    public void update(Category category) {
        Category saved = this.repository.findOne(category.getId());
        List<Item> items = this.itemRepository.findByCategoryName(saved.getName());

        if(saved != null) {
            saved.update(category);
            this.repository.save(saved);
        }

        for (Item item : items) {
            item.setCategory(category);
            this.itemRepository.save(item);
        }
    }

    public void delete(String id){
        Category category = this.repository.findOne(id);

        if(category != null) {
            this.repository.delete(category);
        }

        List<Item> items = this.itemRepository.findByCategoryName(category.getName());
        for (Item item : items) {
            item.setCategory(category);
            this.itemRepository.delete(item);
        }
    }
}
