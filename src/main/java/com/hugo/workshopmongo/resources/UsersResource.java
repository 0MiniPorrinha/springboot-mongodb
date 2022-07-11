package com.hugo.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugo.workshopmongo.domain.User;
import com.hugo.workshopmongo.dto.UserDTO;
import com.hugo.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UsersResource {
    
    @Autowired
    private UserService service;

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();

        //map(x -> new UserDTO(x)) = map(UserDTO::new)
        List<UserDTO>listDTO = list.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User user = service.findById(id);

        return ResponseEntity.ok().body(new UserDTO(user));
    }
}
