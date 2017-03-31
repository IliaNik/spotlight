package com.spotlight.repositories.user;

import com.spotlight.model.user.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author IliaNik on 23.02.2017.
 */
@Repository
public interface UserDataRepository extends CrudRepository<UserData, Integer> {

    UserData findById(Integer Id);
}
