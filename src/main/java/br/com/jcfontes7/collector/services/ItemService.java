package br.com.jcfontes7.collector.services;

import br.com.jcfontes7.collector.models.Item;
import br.com.jcfontes7.collector.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findNameContaining(String name){
        return itemRepository.findByNameContaining(name);
    }

    public Item findById(String id) {
        return itemRepository.findOne(id);
    }

    public void save(Item item){
        itemRepository.save(item);
    }

    public void delete(String id){
        Item item = itemRepository.findOne(id);

        if(item != null) {
            itemRepository.delete(item);
        }
    }

    public void update(Item item) {
        Item saved = itemRepository.findOne(item.getId());

        if(saved != null) {
            saved.update(item);
            itemRepository.save(saved);
        }
    }
}
