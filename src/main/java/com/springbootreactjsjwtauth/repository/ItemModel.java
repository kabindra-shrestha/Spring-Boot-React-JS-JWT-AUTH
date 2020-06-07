package com.springbootreactjsjwtauth.repository;

import com.springbootreactjsjwtauth.entity.common.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemModel extends JpaRepository<Item, Long> {

    Item findById(long id);

    Item findByName(String name);

    /*Iterable<Item> findAllOrderByCreatedatDesc();*/

}
