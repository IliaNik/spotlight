package com.spotlight.model;

import com.spotlight.model.spot.Spot;
import com.spotlight.model.user.User;
import com.spotlight.util.Constants;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * @author IliaNik on 20.02.2017.
 */
@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name = Constants.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    private Integer id;

    @NotNull
    @Column(name = "title", length = 100)
    private String title;

    @NotNull
    @Length.List({
            @Length(min = 40, message = "The text must be at least 40 characters"),
            @Length(max = 100000, message = "The text must be less than 100000 characters")
    })
    @Column(name = "text")
    private String text;

    @NotNull
    @Min(0)
    @Max(10)
    @Column(name = "rating")
    private Integer rating;

    @NotNull
    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "review_id",referencedColumnName = "spot_id")
    private Spot spot;

    @ManyToOne
    @JoinColumn(name = "review_id",referencedColumnName = "user_id")
    private User user;

    public Review() {
    }

    public Review(String title, String text, Integer rating, Date date, Spot spot, User user) {
        this.title = title;
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.spot = spot;
        this.user = user;
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

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) &&
                Objects.equals(title, review.title) &&
                Objects.equals(text, review.text) &&
                Objects.equals(rating, review.rating) &&
                Objects.equals(date, review.date) &&
                Objects.equals(spot, review.spot) &&
                Objects.equals(user, review.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, rating, date, spot, user);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                ", spot=" + spot +
                ", user=" + user +
                '}';
    }
}

