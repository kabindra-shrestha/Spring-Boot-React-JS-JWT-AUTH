package com.springbootreactjsjwtauth.repository;

import com.springbootreactjsjwtauth.entity.common.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotModel extends JpaRepository<Lot, Long> {

    Lot findById(long id);

    Lot findByItemid(long id);

    Lot findByName(int name);

}
