package br.com.guntz.comments.moderation.domain.model;

import br.com.guntz.comments.moderation.common.UUIDValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Moderation {

    private UUID commentId;

    private String text;

    private Boolean approved = true;

    private String reason;

    public Moderation(String commentId, String text) {
        this.commentId = UUIDValidator.validator(commentId);
        this.text = text;
    }

}
