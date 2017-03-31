package com.spotlight.services.spot;

import com.spotlight.dto.spot.SpotDTO;
import com.spotlight.model.spot.Spot;

import java.util.List;

/**
 * @author IliaNik on 04.03.2017.
 */
public interface SpotService {

    Spot save(Spot user);

    Spot get(Integer id);

    List<Spot> getAll(Integer limit, Integer offset, String[] sort);

    List<Spot> getByPartOfTitle(String title, Integer limit, Integer offset, String[] sort);

    void delete(Integer Id);

    Boolean isSpotExist(Integer spotId);

    Boolean isSpotExist(String username);

    void updateSpot(Integer userId, SpotDTO spotDTO);

    SpotDTO toSpotDTO (Spot spot);

    SpotDTO toSpotDTO (Spot spot, String[] fields);

    List<SpotDTO> toSpotDto(List<Spot> spots);

    List<SpotDTO> toSpotDto(List<Spot> spots, String[] fields);
}
