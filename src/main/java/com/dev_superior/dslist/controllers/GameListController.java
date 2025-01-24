package com.dev_superior.dslist.controllers;


import com.dev_superior.dslist.dto.GameDTO;
import com.dev_superior.dslist.dto.GameListDTO;
import com.dev_superior.dslist.dto.GameMinDTO;
import com.dev_superior.dslist.dto.ReplacementDTO;
import com.dev_superior.dslist.services.GameListService;
import com.dev_superior.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class GameListController {

    @Autowired
    GameListService gameListService;

    @Autowired
    GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll(){
        return gameListService.findAll();
    }

    @GetMapping("/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId){
        return gameService.findByList(listId);
    }

    @PostMapping("/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body){
         gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }

}
