package br.com.guntz.comments.moderation.common;

import br.com.guntz.comments.moderation.api.config.exception.InvalidUUIDException;

import java.util.UUID;

public class UUIDValidator {

    public static UUID validator(String commentId) {
        UUID uuid;

        try {
            uuid = UUID.fromString(commentId);
        } catch (Exception e) {
            throw new InvalidUUIDException(e);
        }

        return uuid;
    }
}
