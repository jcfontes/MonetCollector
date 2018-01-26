package br.com.jcfontes7.collector.repositories;

import br.com.jcfontes7.collector.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findByNameContaining(String name);

    @Query("{'category.name': ?0}")
    List<Item> findByCategoryName(String categoryName);

}
