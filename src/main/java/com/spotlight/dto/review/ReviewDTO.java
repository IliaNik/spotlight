package com.spotlight.dto.review;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * @author IliaNik on 30.03.2017.
 */
public class ReviewDTO implements Serializable {

    private Integer id;
    private String title;

    @Length.List({
            @Length(min = 40, message = "The text must be at least 40 characters"),
            @Length(max = 100000, message = "The text must be less than 100000 characters")
    })
    private String text;

    @Min(0)
    @Max(10)
    private Integer rating;
    private Date date;
    private Integer spotId;
    private Integer userId;

    public ReviewDTO() {
    }

    public ReviewDTO(Integer id, String title, String text, Integer rating, Date date, Integer spotId, Integer userId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.spotId = spotId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                ", spotId=" + spotId +
                ", userId=" + userId +
                '}';
    }

    public static ReviewDTOBuilder create() {
        return new ReviewDTOBuilder();
    }

    public static final class ReviewDTOBuilder {
        private Integer id;
        private String title;
        private String text;
        private Integer rating;
        private Date date;
        private Integer spotId;
        private Integer userId;

        private ReviewDTOBuilder() {
        }

        public ReviewDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ReviewDTOBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ReviewDTOBuilder text(String text) {
            this.text = text;
            return this;
        }

        public ReviewDTOBuilder rating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public ReviewDTOBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public ReviewDTOBuilder spotId(Integer spotId) {
            this.spotId = spotId;
            return this;
        }

        public ReviewDTOBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public ReviewDTO get() {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setId(id);
            reviewDTO.setTitle(title);
            reviewDTO.setText(text);
            reviewDTO.setRating(rating);
            reviewDTO.setDate(date);
            reviewDTO.setSpotId(spotId);
            reviewDTO.setUserId(userId);
            return reviewDTO;
        }
    }
}
