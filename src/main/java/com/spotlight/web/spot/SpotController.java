package com.spotlight.web.spot;

import com.spotlight.dto.error.ErrorDTO;
import com.spotlight.dto.spot.SpotDTO;
import com.spotlight.model.spot.Spot;
import com.spotlight.services.spot.SpotService;
import com.spotlight.util.SpotFields;
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
 * @author IliaNik on 28.03.2017.
 */

@RestController
@RequestMapping(value = "/spots")
public class SpotController {

    private static final Logger LOG = LoggerFactory.getLogger(SpotController.class);

    @Autowired
    private SpotService spotService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createSpot(@Valid @RequestBody Spot spot,
                                        UriComponentsBuilder ucBuilder) {
        LOG.info(spot.toString());

        final String title = spot.getTitle();
        if (spotService.isSpotExist(title)) {
            LOG.error("Unable to create. A Spot with title {} already exist", title);
            return new ResponseEntity<>(new ErrorDTO("Unable to create",
                    " A Spot with title " + title + " already exist."), HttpStatus.CONFLICT);
        }

        final Integer spotId = spotService.save(spot).getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/spots/{id}").buildAndExpand(spotId).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getSpots(@RequestParam(name = "limit", required = false) Integer limit,
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
            sort = new String[]{SpotFields.RATING.getValue()};
        }
        final List<Spot> spots = (q == null) ? spotService.getAll(limit, offset, sort) :
                spotService.getByPartOfTitle(q, limit, offset, sort);

        if (spots == null) {
            LOG.error("Spots  not found.");
            return new ResponseEntity<>(new ErrorDTO("Unable to get",
                    "Spots not found"), HttpStatus.NOT_FOUND);
        }

        final List<SpotDTO> spotDTOList = (fields == null) ? spotService.toSpotDto(spots) :
                spotService.toSpotDto(spots, fields);

        return new ResponseEntity<>(spotDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSpot(@PathVariable("id") Integer id) {
        final Spot spot = spotService.get(id);
        if (spot == null) {
            LOG.error("Spot with id {} not found.", id);
            return new ResponseEntity<>(new ErrorDTO("Unable to get",
                    "Spot with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(spot, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSpot(@PathVariable("id") Integer id,
                                        @Valid @RequestBody SpotDTO spotDTO) {
        final Spot spot = spotService.get(id);
        if (spot == null) {
            LOG.error("Unable to update. Spot with id {} not found.", id);
            return new ResponseEntity<>(new ErrorDTO("Unable to update:",
                    " Spot with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        spotService.updateSpot(id, spotDTO);
        return new ResponseEntity<>(spot, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSpot(@PathVariable("id") Integer id) {
        if (spotService.isSpotExist(id)) {
            LOG.error("Unable to delete. Spot with id {} not found.", id);
            return new ResponseEntity<>(new ErrorDTO("Unable to Delete",
                    " Spot with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        spotService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
