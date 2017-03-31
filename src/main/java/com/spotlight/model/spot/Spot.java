package com.spotlight.model.spot;

import com.spotlight.util.Constants;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author IliaNik on 20.02.2017.
 */

@Entity
@Table(name = "spots")
public class Spot {

    @Id
    @Column(name = Constants.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    private Integer id;

    @Column(name = "title", length = 100)
    private String title;

    @NotNull
    @Min(0)
    @Max(10)
    @Column(name = "rating")
    private Integer rating;

    @Column
    private String info;

    @NotNull
    @Embedded
    private Location location;

    public Spot() {
    }

    public Spot(String title, Integer rating, String info, Location location) {
        this.title = title;
        this.rating = rating;
        this.info = info;
        this.location = location;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spot)) return false;
        Spot spot = (Spot) o;
        return Objects.equals(id, spot.id) &&
                Objects.equals(title, spot.title) &&
                Objects.equals(rating, spot.rating) &&
                Objects.equals(info, spot.info) &&
                Objects.equals(location, spot.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, rating, info, location);
    }

    @Override
    public String toString() {
        return "Spot{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", info='" + info + '\'' +
                ", location=" + location +
                '}';
    }
}
