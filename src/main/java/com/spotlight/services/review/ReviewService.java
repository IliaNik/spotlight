package com.spotlight.services.review;

import com.spotlight.dto.review.ReviewDTO;
import com.spotlight.model.Review;

import java.util.List;

/**
 * @author IliaNik on 03.03.2017.
 */
public interface ReviewService {
    Review save(Review review);

    Review get(Integer id);

    Review get(ReviewDTO reviewDTO);

    List<Review> getByUserId(Integer Id);

    List<Review> getByReviewId(Integer Id);

    List<Review> getByReviewIdAndUserId(Integer restaurantId, Integer userId);

    List<Review> getAll(Integer limit, Integer offset, String[] sort);

    List<Review> getByPartOfTitle(String title, Integer limit, Integer offset, String[] sort);

    void delete(Integer Id);

    Boolean isReviewExist(Integer reviewId);

    ReviewDTO toReviewDTO (Review review);

    ReviewDTO toReviewDTO (Review review, String[] fields);

    List<ReviewDTO> toReviewDto(List<Review> reviews);

    List<ReviewDTO> toReviewDto(List<Review> reviews, String[] fields);
}

