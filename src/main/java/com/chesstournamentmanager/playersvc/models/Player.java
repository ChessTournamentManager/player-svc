package com.chesstournamentmanager.playersvc.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "player")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    public static final int MINIMUM_RATING = 100;
    public static final int STANDARD_RATING = 400;
    public static final int MAXIMUM_RATING = 4000;
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private int rating;
    private LocalDateTime createdAt;

    public Player(String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = STANDARD_RATING;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
