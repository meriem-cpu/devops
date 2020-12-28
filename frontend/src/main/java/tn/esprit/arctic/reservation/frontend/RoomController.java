package tn.esprit.arctic.reservation.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.arctic.reservation.domain.Room;
import tn.esprit.arctic.reservation.services.RoomService;

import java.util.Optional;

@RestController
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/rooms")
    public @ResponseBody
    Iterable<Room> getRooms(){
        return roomService.getAllRooms();
    }
    @PostMapping("/add/room")
    public @ResponseBody Room addRoom(@RequestBody Room room){
        return roomService.addRoom(room);
    }
    @PutMapping("update/room")
    public @ResponseBody Room updateRoom (@RequestBody Room room){
        return roomService.updateRoom(room);
    }
    @DeleteMapping("/delete/room/{id}")
    public boolean deleteRoom(@PathVariable String id){
        roomService.deleteRoom(Integer.parseInt(id));
        return true;
    }
    @GetMapping("/room/{id}")
    public @ResponseBody
    Optional<Room> getRoom(@PathVariable String id){
        return roomService.findById(Integer.parseInt(id));
    }
}
