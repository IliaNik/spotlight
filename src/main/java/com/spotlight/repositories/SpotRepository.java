package com.spotlight.repositories;

import com.spotlight.model.spot.Spot;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author IliaNik on 03.03.2017.
 */
public interface SpotRepository extends CrudRepository<Spot, Integer> {

    Spot findById(Integer id);

    List<Spot> findByTitle(String title);

    @Query("select r from Spot r where r.title limit :limit offset :offset ")
    List<Spot> findAll(@Param("limit")Integer limit,
                       @Param("offset")Integer offset, Sort sort);

    @Query("select r from Spot r where r.title like %:title% limit :limit offset :offset ")
    List<Spot> findByPartOfTitle(@Param("title")String title, @Param("limit")Integer limit,
                                 @Param("offset")Integer offset, Sort sort);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Spot s WHERE s.title = :title")
    Boolean existsByTitle(@Param("title")String title);
}

