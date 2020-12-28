package tn.esprit.arctic.reservation.frontend.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tn.esprit.arctic.reservation.domain.Room;
import tn.esprit.arctic.reservation.frontend.ReservationApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReservationApplication.class)
@WebAppConfiguration
public class RoomControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    private String mapToJson(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
    private <T> T mapFromJson(String json, Class<T> myClass) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,myClass);
    }

    @Test
    public void getRooms() throws Exception{
        String uri="/rooms";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }
    @Test
    public void addRoom() throws Exception{
        String uri="/add/room";
        Room room = new Room();
        room.setName("Room 1");
        room.setDescription("Room 1 brief description");
        room.setAvailable(true);
        String json = mapToJson(room);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
        String content = mvcResult.getResponse().getContentAsString();
        Room returnedRoom = mapFromJson(content,Room.class);
        Assert.assertEquals("Room 1",returnedRoom.getName());
    }




}
