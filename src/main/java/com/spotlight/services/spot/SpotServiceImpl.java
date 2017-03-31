package com.spotlight.services.spot;

import com.spotlight.dto.spot.LocationDTO;
import com.spotlight.dto.spot.SpotDTO;
import com.spotlight.model.spot.Spot;
import com.spotlight.repositories.SpotRepository;
import com.spotlight.util.SpotFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author IliaNik on 04.03.2017.
 */
@Service("spotService")
@Transactional
public class SpotServiceImpl implements SpotService {

    @Autowired
    private SpotRepository spotRepository;


    @Override
    public Spot save(Spot review) {
        return spotRepository.save(review);
    }

    @Override
    public Spot get(Integer id) {
        return spotRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        spotRepository.delete(id);
    }

    @Override
    public List<Spot> getAll(Integer limit, Integer offset, String[] sort) {
        return spotRepository.findAll(limit, offset, new Sort(Sort.Direction.ASC, sort));
    }

    @Override
    public List<Spot> getByPartOfTitle(String title, Integer limit, Integer offset, String[] sort) {
        return spotRepository.findByPartOfTitle(title, limit, offset, new Sort(Sort.Direction.ASC, sort));
    }

    @Override
    public Boolean isSpotExist(Integer spotId) {
        return spotRepository.exists(spotId);
    }

    @Override
    public Boolean isSpotExist(String title) {
        return spotRepository.existsByTitle(title);
    }

    @Override
    public void updateSpot(Integer userId, SpotDTO spotDTO) {
        Spot spot = spotRepository.findById(userId);

        if (spotDTO.getTitle() != null) {
            spot.setTitle(spotDTO.getTitle());
        }
        if (spotDTO.getRating() != null) {
            spot.setRating(spotDTO.getRating());
        }
        if (spotDTO.getInfo() != null) {
            spot.setInfo(spotDTO.getInfo());
        }
        if (spotDTO.getTitle() != null) {
            spot.setTitle(spotDTO.getTitle());
        }

        spotRepository.save(spot);
    }

    @Override
    public SpotDTO toSpotDTO(Spot spot) {
        SpotDTO spotDTO = new SpotDTO();
        spotDTO.setId(spot.getId());
        spotDTO.setTitle(spot.getTitle());
        spotDTO.setRating(spot.getRating());
        spotDTO.setInfo(spot.getInfo());
        return spotDTO;
    }

    @Override
    public SpotDTO toSpotDTO(Spot spot, String[] fields) {
        List<String> fieldsList = Arrays.asList(fields);

        SpotDTO spotDTO = new SpotDTO();

        if (fieldsList.contains(SpotFields.ID.getValue())) {
            spotDTO.setId(spot.getId());
        }
        if (fieldsList.contains(SpotFields.TITLE.getValue())) {
            spotDTO.setTitle(spot.getTitle());
        }
        if (fieldsList.contains(SpotFields.RATING.getValue())) {
            spotDTO.setRating(spot.getRating());
        }
        if (fieldsList.contains(SpotFields.INFO.getValue())) {
            spotDTO.setInfo(spot.getInfo());
        }
        if (fieldsList.contains(SpotFields.LOCATION.getValue())) {
            spotDTO.setLocation(new LocationDTO(spot.getLocation()));
        }

        return spotDTO;
    }

    @Override
    public List<SpotDTO> toSpotDto(List<Spot> spots) {
        final List<SpotDTO> spotDTOList = new ArrayList<>();
        for (Spot spot : spots) {
            spotDTOList.add(toSpotDTO(spot));
        }
        return spotDTOList;
    }

    @Override
    public List<SpotDTO> toSpotDto(List<Spot> spots, String[] fields) {

        List<String> fieldsList = Arrays.asList(fields);

        final List<SpotDTO> spotDTOList = new ArrayList<>();
        for (Spot spot : spots) {

            SpotDTO spotDTO = new SpotDTO();

            if (fieldsList.contains(SpotFields.ID.getValue())) {
                spotDTO.setId(spot.getId());
            }
            if (fieldsList.contains(SpotFields.TITLE.getValue())) {
                spotDTO.setTitle(spot.getTitle());
            }
            if (fieldsList.contains(SpotFields.RATING.getValue())) {
                spotDTO.setRating(spot.getRating());
            }
            if (fieldsList.contains(SpotFields.INFO.getValue())) {
                spotDTO.setInfo(spot.getInfo());
            }
            if (fieldsList.contains(SpotFields.LOCATION.getValue())) {
                spotDTO.setLocation(new LocationDTO(spot.getLocation()));
            }
            spotDTOList.add(spotDTO);
        }
        return spotDTOList;
    }
}
