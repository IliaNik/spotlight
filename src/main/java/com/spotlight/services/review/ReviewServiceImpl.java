package com.spotlight.services.review;

import com.spotlight.dto.review.ReviewDTO;
import com.spotlight.model.Review;
import com.spotlight.model.spot.Spot;
import com.spotlight.model.user.User;
import com.spotlight.repositories.ReviewRepository;
import com.spotlight.services.spot.SpotService;
import com.spotlight.services.user.UserService;
import com.spotlight.util.ReviewFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author IliaNik on 03.03.2017.
 */
@Service("reviewService")
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SpotService spotService;

    @Autowired
    private UserService userService;


    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review get(Integer id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review get(ReviewDTO reviewDTO) {
        Review review = new Review();
        if (reviewDTO.getTitle() != null) {
            review.setTitle(reviewDTO.getTitle());
        }
        if (reviewDTO.getText() != null) {
            review.setText(reviewDTO.getText());
        }
        if (reviewDTO.getRating() != null) {
            review.setRating(reviewDTO.getRating());
        }
        if (reviewDTO.getDate() != null) {
            review.setDate(reviewDTO.getDate());
        }

        User user = userService.get(reviewDTO.getUserId());
        Spot spot = spotService.get(reviewDTO.getSpotId());

        review.setUser(user);
        review.setSpot(spot);
        return review;
    }

    @Override
    public List<Review> getByUserId(Integer id) {
        return reviewRepository.findByUserId(id);
    }

    @Override
    public List<Review> getByReviewId(Integer id) {
        return reviewRepository.findBySpotId(id);
    }

    @Override
    public List<Review> getByReviewIdAndUserId(Integer restaurantId, Integer userId) {
        return reviewRepository.findBySpotIdAndUserId(restaurantId, userId);
    }

    public List<Review> getAll(Integer limit, Integer offset, String[] sort) {
        return reviewRepository.findAll(limit, offset, new Sort(Sort.Direction.ASC, sort));
    }

    public List<Review> getByPartOfTitle(String title, Integer limit, Integer offset, String[] sort) {
        return reviewRepository.findByPartOfTitle(title, limit, offset, new Sort(Sort.Direction.ASC, sort));
    }

    @Override
    public void delete(Integer id) {
        reviewRepository.delete(id);
    }

    @Override
    public Boolean isReviewExist(Integer reviewId) {
        return reviewRepository.exists(reviewId);
    }


    @Override
    public ReviewDTO toReviewDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setDate(review.getDate());
        reviewDTO.setUserId(review.getUser().getId());
        reviewDTO.setSpotId(review.getSpot().getId());
        return reviewDTO;
    }

    @Override
    public ReviewDTO toReviewDTO(Review review, String[] fields) {
        List<String> fieldsList = Arrays.asList(fields);

        ReviewDTO reviewDTO = new ReviewDTO();

        if (fieldsList.contains(ReviewFields.ID.getValue())) {
            reviewDTO.setId(review.getId());
        }
        if (fieldsList.contains(ReviewFields.TITLE.getValue())) {
            reviewDTO.setTitle(review.getTitle());
        }
        if (fieldsList.contains(ReviewFields.TEXT.getValue())) {
            reviewDTO.setText(review.getText());
        }
        if (fieldsList.contains(ReviewFields.RATING.getValue())) {
            reviewDTO.setRating(review.getRating());
        }
        if (fieldsList.contains(ReviewFields.DATE.getValue())) {
            reviewDTO.setDate(review.getDate());
        }
        if (fieldsList.contains(ReviewFields.USER_ID.getValue())) {
            reviewDTO.setUserId(review.getUser().getId());
        }
        if (fieldsList.contains(ReviewFields.SPOT_ID.getValue())) {
            reviewDTO.setSpotId(review.getSpot().getId());
        }
        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> toReviewDto(List<Review> reviews) {
        final List<ReviewDTO> spotDTOList = new ArrayList<>();
        for (Review review : reviews) {
            spotDTOList.add(toReviewDTO(review));
        }
        return spotDTOList;
    }

    @Override
    public List<ReviewDTO> toReviewDto(List<Review> reviews, String[] fields) {

        List<String> fieldsList = Arrays.asList(fields);

        final List<ReviewDTO> spotDTOList = new ArrayList<>();
        for (Review review : reviews) {

            ReviewDTO reviewDTO = new ReviewDTO();

            if (fieldsList.contains(ReviewFields.ID.getValue())) {
                reviewDTO.setId(review.getId());
            }
            if (fieldsList.contains(ReviewFields.TITLE.getValue())) {
                reviewDTO.setTitle(review.getTitle());
            }
            if (fieldsList.contains(ReviewFields.TEXT.getValue())) {
                reviewDTO.setText(review.getText());
            }
            if (fieldsList.contains(ReviewFields.RATING.getValue())) {
                reviewDTO.setRating(review.getRating());
            }
            if (fieldsList.contains(ReviewFields.DATE.getValue())) {
                reviewDTO.setDate(review.getDate());
            }
            if (fieldsList.contains(ReviewFields.USER_ID.getValue())) {
                reviewDTO.setUserId(review.getUser().getId());
            }
            if (fieldsList.contains(ReviewFields.SPOT_ID.getValue())) {
                reviewDTO.setSpotId(review.getSpot().getId());
            }
            spotDTOList.add(reviewDTO);
        }
        return spotDTOList;
    }
}

