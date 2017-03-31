package com.spotlight.model.user;

import com.spotlight.util.Constants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserData implements Serializable {

    @Id
    @Column(name = Constants.ID)
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "first_name", length = 100)
    private String firstName;

    @NotEmpty
    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column
    private String photo;

    @NotEmpty
    @Column
    private String city;

    @NotNull
    @Column
    private Date date;

    @Column
    private Integer reviewsCount;

    @Size(max = 140, message = "The bio must be less than 140 characters")
    private String bio = "";

    @OneToOne
    private User user;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserData() {
    }

    public UserData(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }

    public UserData(String firstName, String lastName, String picture, String city,
                    Date date, String bio, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = picture;
        this.city = city;
        this.date = date;
        this.bio = bio;
        this.user = user;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, bio, city, photo, date, reviewsCount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserData other = (UserData) obj;
        return Objects.equals(this.firstName, other.firstName)
                && Objects.equals(this.lastName, other.lastName)
                && Objects.equals(this.bio, other.bio)
                && Objects.equals(this.photo, other.photo)
                && Objects.equals(this.city, other.city)
                && Objects.equals(this.reviewsCount, other.reviewsCount)
                && Objects.equals(this.date, other.date);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", photo='" + photo + '\'' +
                ", city='" + city + '\'' +
                ", date=" + date +
                ", reviewsCount=" + reviewsCount +
                ", bio='" + bio + '\'' +
                ", userDetail=" + user +
                '}';
    }
}
