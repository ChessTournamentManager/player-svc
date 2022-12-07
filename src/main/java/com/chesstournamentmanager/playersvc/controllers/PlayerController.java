package com.chesstournamentmanager.playersvc.controllers;

import com.chesstournamentmanager.playersvc.models.Player;
import com.chesstournamentmanager.playersvc.models.PlayerRequestModel;
import com.chesstournamentmanager.playersvc.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping
    public Iterable<Player> getPlayers() {
        return playerService.getPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayer(
            @PathVariable UUID id) {
        Optional<Player> player = playerService.getPlayer(id);
        if (player.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Player with id " + id + " does not exist"
            );
        }
        return player.get();
    }


    @PostMapping
    public Player addPlayer(@RequestBody PlayerRequestModel playerRequestModel) {
        Player player = convertToEntity(playerRequestModel);

        String message = playerService.validatePlayer(player);
        if (!message.isEmpty())
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    message
            );
        }

        playerService.addNewPlayer(player);
        Optional<Player> returnedPlayer = playerService.getPlayer(player.getId());
        if (returnedPlayer.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "The player was not added successfully. Ask the developers to fix this issue."
            );
        }
        return returnedPlayer.get();
    }

    @PutMapping("/{id}")
    public Player updatePlayer(
            @PathVariable UUID id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) int rating) {
        playerService.updatePlayer(id, firstName, lastName, rating);
        Optional<Player> returnedPlayer= playerService.getPlayer(id);
        if (returnedPlayer.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "The player was not updated successfully. Ask the developers to fix this issue."
            );
        }
        return returnedPlayer.get();
    }


    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable UUID id) {
        playerService.deletePlayer(id);
        return "Player with ID " + id + " has been deleted";
    }

    private Player convertToEntity(PlayerRequestModel playerRequestModel) {
        return new Player(
                playerRequestModel.getFirstName(),
                playerRequestModel.getLastName()
        );
    }
}
