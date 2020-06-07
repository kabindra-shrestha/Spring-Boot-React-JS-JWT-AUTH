package com.springbootreactjsjwtauth.service;

import com.springbootreactjsjwtauth.entity.common.ItemCode;
import com.springbootreactjsjwtauth.entity.common.Lot;
import com.springbootreactjsjwtauth.entity.common.Users;
import com.springbootreactjsjwtauth.repository.ItemCodeModel;
import com.springbootreactjsjwtauth.utils.exceptions.ContentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.springbootreactjsjwtauth.utils.LinkConfig.ERROR_ITEM_CODE_DOESNT_EXIST;

@Service
public class ItemCodeService {

    @Autowired
    private ItemCodeModel itemCodeModel;

    public ItemCode checkItemCodeById(long id) {
        ItemCode itemCode = findById(id);

        if (itemCode == null) {
            throw new ContentNotFoundException(ERROR_ITEM_CODE_DOESNT_EXIST);
        }

        return itemCode;
    }

    public void save(String secretKey, String viewKey, Lot lot, Users user) {
        ItemCode itemCode = new ItemCode();

        itemCode.setSecretKey(secretKey);
        itemCode.setViewKey(viewKey);
        itemCode.setLotid(lot);
        itemCode.setUserid(user);

        itemCodeModel.save(itemCode);
    }

    public void update(long id, String secretKey, String viewKey, Lot lot, Users user) {
        ItemCode itemCode = findById(id);

        if (secretKey != null && secretKey.isEmpty())
            itemCode.setSecretKey(secretKey);

        if (viewKey != null && viewKey.isEmpty())
            itemCode.setViewKey(viewKey);

        if (lot != null)
            itemCode.setLotid(lot);

        if (user != null)
            itemCode.setUserid(user);

        itemCodeModel.save(itemCode);
    }

    public void deleteById(long id) {
        itemCodeModel.deleteById(id);
    }

    public ItemCode findById(long id) {
        ItemCode itemCode = itemCodeModel.findById(id);

        return itemCode;
    }

}
