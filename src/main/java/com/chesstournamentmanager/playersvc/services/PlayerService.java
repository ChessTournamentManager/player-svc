package com.chesstournamentmanager.playersvc.services;

import com.chesstournamentmanager.playersvc.models.Player;
import com.chesstournamentmanager.playersvc.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Read methods

    public Iterable<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayer(UUID id) {
        return playerRepository.findById(id);
    }

    // Update methods

    public void addNewPlayer(Player player) {
        playerRepository.save(player);
    }

    // Update methods

    @Transactional
    public void updatePlayer(UUID id, String firstName, String lastName, int rating) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Player with id " + id + " does not exist"
                ));

        if (!firstName.isBlank() &&
                !Objects.equals(player.getFirstName(), firstName)) {
            player.setFirstName(firstName);
        }

        if (!lastName.isBlank() &&
                !Objects.equals(player.getLastName(), lastName)) {
            player.setLastName(lastName);
        }

        if (rating > Player.MinimumRating &&
                rating <= Player.MaximumRating &&
                !Objects.equals(player.getRating(), rating)) {
            player.setRating(rating);
        }
    }

    // Delete methods

    public void deletePlayer(UUID id) {
        boolean exists = playerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Player with id " + id + " does not exist");
        }

        playerRepository.deleteById(id);
    }

    // Validation methods

    public String validatePlayer(Player player) {
        if (player.getFirstName().isBlank()) {
            return "No first name was provided";
        }

        if (player.getLastName().isBlank()) {
            return "No last name was provided";
        }

        return "";
    }
}
