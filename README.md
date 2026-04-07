# Sentinel API - Gestão de Inventário

API REST desenvolvida em Java 17 com Spring Boot 3 para controle e monitoramento de estoque. O projeto foca em validação de dados, tratamento de erros global e documentação automática.

## Tecnologias Utilizadas
* Java 17 & Maven
* Spring Boot 3 (Web, Data JPA, Validation)
* H2 Database (Banco de dados em memória)
* OpenAPI 3 / Swagger UI (Documentação interativa)
* Lombok

## Funcionalidades
- Cadastro de produtos com SKU único.
- Listagem e busca por ID.
- Atualização de dados com validação de consistência.
- Exclusão de registros com retorno de status apropriado.
- Global Exception Handling: Respostas de erro padronizadas em JSON para o frontend.

## Instruções para Execução
1. Clone o repositório: `git clone https://github.com/SEU_USUARIO/sentinel-api.git`
2. Importe o projeto em sua IDE como um projeto Maven.
3. Execute a classe `SentinelApplication`.
4. A documentação interativa estará disponível em: `http://localhost:8081/swagger-ui/index.html`

## Testes e Qualidade
A API foi testada para garantir retornos HTTP precisos:
- 201 Created para novas inserções.
- 400 Bad Request para dados mal formatados ou inválidos.
- 404 Not Found para recursos inexistentes.
- 409 Conflict para violação de unicidade de SKU.
