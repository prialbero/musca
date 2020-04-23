package com.example.gotproject.controller;

import com.example.gotproject.Mapa;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapaController {
    final static Logger logger = Logger.getLogger(MapaController.class);


    @PostMapping(path="/mapa",consumes="application/json")
    public synchronized ResponseEntity<String> crearMapa(@RequestBody Mapa m) {
        Mapa.crearMapa(m.getDimX(), m.getDimY(),m.getSalaTrono());
        return new ResponseEntity(HttpStatus.OK);
    }


}


