package tn.esprit.arctic.reservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.arctic.reservation.domain.Room;
import tn.esprit.arctic.reservation.persistance.RoomRepository;

import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public Iterable<Room> getAllRooms(){
        return  roomRepository.findAll();
    }
    public Room addRoom(Room room){
        return roomRepository.save(room);
    }
    public Room updateRoom(Room room){
        return roomRepository.save(room);
    }
    public boolean deleteRoom(int id){
        roomRepository.deleteById(id);
        return true;
    }
    public Optional<Room> findById(int id){
        return roomRepository.findById(id);
    }
}
