package com.spotlight.repositories;

import com.spotlight.model.Review;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author IliaNik on 03.03.2017.
 */
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    Review findById(Integer Id);

    List<Review> findByUserId(Integer Id);

    List<Review> findBySpotId(Integer Id);

    List<Review> findBySpotIdAndUserId(Integer spotId, Integer userId);

    @Query("select r from Restaurant r where r.title limit :limit offset :offset ")
    List<Review> findAll(@Param("limit")Integer limit,
                       @Param("offset")Integer offset, Sort sort);

    @Query("select r from Restaurant r where r.title like %:title% limit :limit offset :offset ")
    List<Review> findByPartOfTitle(@Param("title")String title, @Param("limit")Integer limit,
                                 @Param("offset")Integer offset, Sort sort);
}
