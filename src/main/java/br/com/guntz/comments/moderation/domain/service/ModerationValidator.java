package br.com.guntz.comments.moderation.domain.service;

import br.com.guntz.comments.moderation.domain.model.Moderation;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class ModerationValidator {

    private final List<String> prohibitedWords = new ArrayList<>();

    public void validator(Moderation moderation) {
        populateWords();

        if (isProhibited(moderation)) {
            moderation.setApproved(false);
            moderation.setReason("prohibited because it contains foul language or hate speech");
        }
    }

    private boolean isProhibited(Moderation moderation) {
        String word = StringUtils.stripAccents(moderation.getText()).toLowerCase();

        for (String prohibitedWord : getProhibitedWords()) {
            prohibitedWord = StringUtils.stripAccents(prohibitedWord).toLowerCase();

            if (word.contains(prohibitedWord)) {
                return true;
            }
        }

        return false;
    }

    private void populateWords() {
        prohibitedWords.add("ódio");
        prohibitedWords.add("xingamento");
        prohibitedWords.add("raiva");
        prohibitedWords.add("cretino");
        prohibitedWords.add("idiota");
        prohibitedWords.add("retardado");
    }
}
