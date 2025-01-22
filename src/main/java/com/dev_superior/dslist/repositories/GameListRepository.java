package com.dev_superior.dslist.repositories;

import com.dev_superior.dslist.entities.Game;
import com.dev_superior.dslist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository<GameList, Long> {
}
