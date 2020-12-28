package tn.esprit.arctic.reservation.persistance;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.arctic.reservation.domain.Room;

public interface RoomRepository extends CrudRepository<Room,Integer> {

}
