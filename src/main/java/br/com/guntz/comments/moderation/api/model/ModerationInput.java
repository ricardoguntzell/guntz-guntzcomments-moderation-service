package br.com.guntz.comments.moderation.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModerationInput {

    @NotBlank
    private String commentId;

    @NotBlank
    private String text;

}
