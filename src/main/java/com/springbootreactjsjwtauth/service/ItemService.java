package com.springbootreactjsjwtauth.service;

import com.springbootreactjsjwtauth.entity.common.Item;
import com.springbootreactjsjwtauth.repository.ItemModel;
import com.springbootreactjsjwtauth.utils.exceptions.ContentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import static com.springbootreactjsjwtauth.utils.LinkConfig.*;

@Service
public class ItemService {

    @Autowired
    private ItemModel itemModel;

    public void save(String name, int quantity) {
        Item item = new Item();

        item.setName(name);
        item.setQuantity(quantity);

        itemModel.save(item);
    }

    public void update(long id, String name, int quantity) {
        Item item = findById(id);

        if (!name.isEmpty())
            item.setName(name);

        if (quantity > 0)
            item.setQuantity(quantity);

        itemModel.save(item);
    }

    public void deleteById(long id) {
        itemModel.deleteById(id);
    }

    private Item findById(long id) {
        return itemModel.findById(id);
    }

    public Item checkItemById(long id) {
        Item item = findById(id);

        if (item == null) {
            throw new ContentNotFoundException(ERROR_ITEM_DOESNT_EXIST);
        }

        return item;
    }

    public void checkItem(String name) {
        Iterable<Item> itemList = itemModel.findAll();

        for (Item item : itemList) {
            if (item.getName().equalsIgnoreCase(name)) {
                throw new DuplicateKeyException(ERROR_ITEM_ALREADY_EXIST + "'" + name + "'.");
            }
        }
    }

    public void checkItemUpdate(long id, String name) {
        Iterable<Item> itemList = itemModel.findAll();

        for (Item item : itemList) {
            if (item.getId() != id) {
                if (item.getName().equalsIgnoreCase(name)) {
                    throw new DuplicateKeyException(ERROR_ITEM_ALREADY_EXIST + name + " .");
                }
            }
        }
    }

    public Iterable<Item> checkRecentItem() {
        Iterable<Item> item = findAllOrderByCreatedatDesc();

        if (!item.iterator().hasNext()) {
            throw new ContentNotFoundException(ERROR_ITEM_NOT_ADDED);
        }

        return item;
    }

    private Iterable<Item> findAllOrderByCreatedatDesc() {
        return itemModel.findAll();
    }

}
