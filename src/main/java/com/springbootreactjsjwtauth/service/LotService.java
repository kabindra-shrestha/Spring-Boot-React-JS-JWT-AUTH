package com.springbootreactjsjwtauth.service;

import com.springbootreactjsjwtauth.entity.common.Lot;
import com.springbootreactjsjwtauth.repository.LotModel;
import com.springbootreactjsjwtauth.utils.exceptions.ContentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.springbootreactjsjwtauth.utils.LinkConfig.ERROR_LOT_DOESNT_EXIST;

@Service
public class LotService {

    @Autowired
    private LotModel lotModel;

    public Lot checkLotById(long id) {
        Lot lot = findById(id);

        if (lot == null) {
            throw new ContentNotFoundException(ERROR_LOT_DOESNT_EXIST);
        }

        return lot;
    }

    public Lot checkLotByItemId(long id) {
        Lot lot = findByItemId(id);

        if (lot == null) {
            throw new ContentNotFoundException(ERROR_LOT_DOESNT_EXIST);
        }

        return lot;
    }

    public Lot checkLotByLotName(int name) {
        Lot lot = findByLotName(name);

        if (lot == null) {
            throw new ContentNotFoundException(ERROR_LOT_DOESNT_EXIST);
        }

        return lot;
    }

    public void save(int name) {
        Lot lot = new Lot();

        lot.setName(name);

        lotModel.save(lot);
    }

    public void update(long id, int name) {
        Lot lot = findById(id);

        if (name >= 0)
            lot.setName(name);

        lotModel.save(lot);
    }

    public void deleteById(long id) {
        lotModel.deleteById(id);
    }

    public Lot findById(long id) {
        Lot lot = lotModel.findById(id);

        return lot;
    }

    public Lot findByItemId(long id) {
        Lot lot = lotModel.findByItemid(id);

        return lot;
    }

    public Lot findByLotName(int id) {
        Lot lot = lotModel.findByName(id);

        return lot;
    }

}
