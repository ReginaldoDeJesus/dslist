package com.dev_superior.dslist.services;

import com.dev_superior.dslist.dto.GameListDTO;
import com.dev_superior.dslist.dto.GameMinDTO;
import com.dev_superior.dslist.entities.Game;
import com.dev_superior.dslist.entities.GameList;
import com.dev_superior.dslist.projections.GameMinProjection;
import com.dev_superior.dslist.repositories.GameListRepository;
import com.dev_superior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(GameListDTO::new).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destination){
        List<GameMinProjection> list = gameRepository.searchByList(listId);
        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destination,obj);

        int min = sourceIndex < destination ? sourceIndex : destination;
        int max = sourceIndex < destination ? destination : sourceIndex;

        for (int i = min; i < max ; i++) {

            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);

        }
    }
}
