package com.spotlight.web.review;

import com.spotlight.dto.error.ErrorDTO;
import com.spotlight.dto.review.ReviewDTO;
import com.spotlight.model.Review;
import com.spotlight.services.review.ReviewService;
import com.spotlight.util.ReviewFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static com.spotlight.util.Constants.DEFAULT_LIMIT;
import static com.spotlight.util.Constants.DEFAULT_OFFSET;

/**
 * @author IliaNik on 30.03.2017.
 */
@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewService reviewService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewDTO reviewDTO,
                                          UriComponentsBuilder ucBuilder) {
        LOG.info(reviewDTO.toString());

        final Review review = reviewService.get(reviewDTO);
        final Integer reviewId = reviewService.save(review).getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/reviews/{id}").buildAndExpand(reviewId).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getReviews(@RequestParam(name = "limit", required = false) Integer limit,
                                      @RequestParam(name = "offset", required = false) Integer offset,
                                      @RequestParam(name = "fields", required = false) String[] fields,
                                      @RequestParam(name = "sort", required = false) String[] sort,
                                      @RequestParam(name = "q", required = false) String q) {
        if (limit == null) {
            limit = DEFAULT_LIMIT;
        }
        if (offset == null) {
            offset = DEFAULT_OFFSET;
        }
        if (limit >= 0 && limit <= 100) {
            LOG.error("Bad limit");
            return new ResponseEntity<>(new ErrorDTO("Bad limit",
                    "Limit must be from 0 to 100"), HttpStatus.BAD_REQUEST);
        }
        if (sort == null) {
            sort = new String[]{ReviewFields.DATE.getValue()};
        }
        final List<Review> reviews = (q == null) ? reviewService.getAll(limit, offset, sort) :
                reviewService.getByPartOfTitle(q, limit, offset, sort);

        if (reviews == null) {
            LOG.error("Spots  not found.");
            return new ResponseEntity<>(new ErrorDTO("Unable to get",
                    "Spots not found"), HttpStatus.NOT_FOUND);
        }

        final List<ReviewDTO> spotDTOList = (fields == null) ? reviewService.toReviewDto(reviews) :
                reviewService.toReviewDto(reviews, fields);

        return new ResponseEntity<>(spotDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSpot(@PathVariable("id") Integer id) {
        final Review review = reviewService.get(id);
        if (review == null) {
            LOG.error("Review with id {} not found.", id);
            return new ResponseEntity<>(new ErrorDTO("Unable to get",
                    "Spot with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateReview(@PathVariable("id") Integer id,
                                        @RequestBody ReviewDTO reviewDTO) {
        String text = reviewDTO.getText();
        if( text.length() <= 100){
            return new ResponseEntity<>(new ErrorDTO("Bad text:",
                    " Text must be not less then 100."), HttpStatus.BAD_REQUEST);
        }
        final Review review = reviewService.get(id);
        if (review == null) {
            LOG.error("Unable to update. Review with id {} not found.", id);
            return new ResponseEntity<>(new ErrorDTO("Unable to update:",
                    " Review with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        review.setText(text);
        reviewService.save(review);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReview(@PathVariable("id") Integer id) {
        if (reviewService.isReviewExist(id)) {
            LOG.error("Unable to delete. Review with id {} not found.", id);
            return new ResponseEntity<>(new ErrorDTO("Unable to Delete",
                    " Review with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
