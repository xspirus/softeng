package com.example.demo.controller;

import com.example.demo.model.Password;
import com.example.demo.model.Volunteer;
import com.example.demo.repository.PasswordRepository;
import com.example.demo.repository.VolunteerRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.example.demo.repository.PasswordRepository.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VolunteerController {


    @Autowired
    VolunteerRepository VolunteerRepository;

    @Autowired
    PasswordRepository passwordRepository;



    @GetMapping("/volunteers")
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<Volunteer> index(){
        return VolunteerRepository.findAll();
    }




    @PostMapping("/volunteers")
    @ResponseBody
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Volunteer create(@RequestBody String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(body);
        String pp = root.path("p").toString();

        Volunteer vol = mapper.readerFor(Volunteer.class).readValue(root.path("v"));
        System.out.println(pp);
        Password p = new Password(pp);
        Volunteer vol1 = VolunteerRepository.save(vol);
        p.setVolunteer(vol1);
        passwordRepository.save(p);

        return vol1;
    }
}