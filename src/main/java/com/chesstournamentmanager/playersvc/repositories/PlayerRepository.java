package com.chesstournamentmanager.playersvc.repositories;

import com.chesstournamentmanager.playersvc.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerRepository extends MongoRepository<Player, UUID> {
}
