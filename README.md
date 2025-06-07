# Moderation Service - Microservice

Guntz moderation é um microsserviço responsável por toda inteligência de identificar palavras de baixo calão, ou termos utilizados em discurso de ódio.<br>
Uma vez identificado que se trata de uma palavra proíbida, ele reprova o comentário.<br>
Esse microsserviço se comunica de forma reativa com o microsserviço (Guntz Comment), que por sua vez realiza o ato de armazenar ou não os cometários e também fazer sua gestão

## ✨ Funcionalidades

- 💾 **Armazenamento**: Persiste apenas comentários aprovados
- 🔍 **Consulta**: Busca por ID e listagem com páginação
- ⚡ **Comunicação Resiliente**: Tratamento de erros em diversas camadas com Spring Validation e também com tratamento de exceções, inclusive entre serviços 

## 🛠️ Stack Tecnológica

- **Java 17** - Linguagem de programação
- **Spring Boot 3.x** - Framework principal
- **Spring Validation** - Tratamento de erros entre cliente e serviço
- **Spring Doc OpenAPI WebMvc UI** - Documentação da api de forma descomplicada
- **Lombok** - Facilitador de escrita de código limpo
- **UUID Generator** - Gerenciador de Id com UUID
- **Maven** - Gerenciador de dependências
- **Log Slf4j** - Log com Slf4j

## 🚀 Início Rápido

### Pré-requisitos

- ☕ JDK 17+
- 🐘 Maven
- 🔧 Git

### Instalação e Execução

1. **Clone o repositório**
   ```bash
   git clone https://github.com/ricardoguntzell/guntz-guntzcomments-moderation-service.git moderation-service
   ```
2. **Inicie o CommentService**
   ```bash
   cd moderation-service
   ./mvnw spring-boot:run
   ```
   > 🌐 Serviço disponível em: http://localhost:8081

### Verificação Rápida

## 📖 Documentação da API
- http://localhost:8081/swagger-ui/index.html

### 🛡️ ModerationService (porta 8081)

#### Moderar Comentário
```http
POST /api/moderate
Content-Type: application/json

{
  "text": "Texto para moderação",
  "commentId": "uuid-do-comentario"
}
```

**Resposta:**
```json
{
  "approved": true,
  "reason": "Comentário aprovado: nenhuma palavra proibida encontrada"
}
```

## ⚙️ Configurações e Regras

### Validações
- **IDs**: Devem ser UUIDs válidos
- **Timeout**: 5 segundos para comunicação entre serviços
- **Palavras Proibidas**: `["ódio", "xingamento"]` (configurável)

### Tratamento de Erros

| Cenário | Código HTTP | Descrição |
|---------|-------------|-----------|
| Comentário rejeitado | `422` | Contém palavras proíbidas |
| Timeout de moderação | `504` | Serviço de moderação não responde |
| Erro de integração | `502` | Falha na comunicação entre serviços |
| Comentário não encontrado | `404` | ID não existe na base |

### Fluxo de Dados

1. **Recepção**: CommentService recebe requisição
2. **Moderação**: Envia para ModerationService via HTTP
3. **Validação**: Verifica palavras proibidas
4. **Decisão**: Aprova ou rejeita baseado na validação
5. **Persistência**: Armazena apenas se aprovado