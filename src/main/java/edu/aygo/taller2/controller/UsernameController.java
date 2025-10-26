package edu.aygo.taller2.controller;

import edu.aygo.taller2.group.StateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Component
public class UsernameController {

    private final StateHandler stateHandler;

    @Autowired
    public UsernameController(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @PutMapping("/username")
    public ResponseEntity<Map<String, String>> saveName(@RequestBody String name) throws Exception {
        return ResponseEntity.ok().body(stateHandler.sendMessage(name));
    }
}
