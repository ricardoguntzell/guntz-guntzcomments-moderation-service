package br.com.guntz.comments.moderation.api.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Guntz Moderation - Microsserviço")
                        .description("Guntz Moderation é um microsserviço responsável por toda inteligência de identificar palavras de baixo calão, ou termos utilizados em discurso de ódio.\n\n" +
                                "Uma vez identificado que se trata de uma palavra proíbida, ele reprova o comentário.\n\n" +
                                "Esse microsserviço se comunica de forma reativa com o microsserviço (Guntz Comment), que por sua vez realiza o ato de armazenar ou não os cometários e também fazer sua gestão")
                        .contact(new Contact()
                                .name("Guntz")
                                .email("rricardoguntzell@gmail.com"))
                        .license(new License()
                                .name("Guntz - Github")
                                .url("https://github.com/ricardoguntzell/guntz-guntzcomments-moderation-service")));
    }
}
