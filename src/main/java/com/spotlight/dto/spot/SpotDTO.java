package com.spotlight.dto.spot;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author IliaNik on 28.03.2017.
 */
public class SpotDTO implements Serializable {

    private Integer id;

    private String title;

    @Min(0)
    @Max(10)
    private Integer rating;

    private String info;

    private LocationDTO location;

    public SpotDTO() {
    }

    public SpotDTO(Integer id, String title, Integer rating, String info, LocationDTO location) {
        this.id = id;
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

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SpotDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", info='" + info + '\'' +
                ", location=" + location +
                '}';
    }

    public static Builder create() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String title;
        private Integer rating;
        private String info;
        private LocationDTO location;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder rating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Builder location(LocationDTO location) {
            this.location = location;
            return this;
        }

        public SpotDTO get() {
            SpotDTO spotDTO = new SpotDTO();
            spotDTO.setId(id);
            spotDTO.setTitle(title);
            spotDTO.setRating(rating);
            spotDTO.setInfo(info);
            spotDTO.setLocation(location);
            return spotDTO;
        }
    }
}
