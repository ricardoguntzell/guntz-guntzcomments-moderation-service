package br.com.guntz.comments.moderation.api.controller;

import br.com.guntz.comments.moderation.api.model.ModerationInput;
import br.com.guntz.comments.moderation.api.model.ModerationOutput;
import br.com.guntz.comments.moderation.domain.model.Moderation;
import br.com.guntz.comments.moderation.domain.service.ModerationValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@AllArgsConstructor
@RestController
@RequestMapping("/api/moderate")
public class ModerationController {

    private final ModerationValidator moderationValidator;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<ModerationOutput> validatedComment(@Valid @RequestBody ModerationInput input) {
        Moderation moderation = new Moderation(input.getCommentId(), input.getText());

        moderationValidator.validator(moderation);

        return ResponseEntity.ok(convertToModerationOutput(moderation));
    }

    private ModerationOutput convertToModerationOutput(Moderation moderation) {
        return ModerationOutput.builder()
                .approved(moderation.getApproved())
                .reason(moderation.getReason())
                .build();
    }

}
