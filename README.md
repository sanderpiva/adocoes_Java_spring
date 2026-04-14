# 🐾 Projeto Viva Animais - Gestão de Adoções (Spring Boot)

Este projeto é uma evolução tecnológica para a arquitetura **Spring Boot 3**, focado na gestão e observação detalhada de adoções de animais. O sistema utiliza uma API REST para fornecer dados a um frontend dinâmico, garantindo performance e organização.

## 🚀 Tecnologias e Evolução
Diferente da versão anterior, este projeto utiliza o ecossistema Spring para maior robustez:
* **Backend:** Java 17+ com **Spring Boot 3.x**.
* **Persistência:** **Spring Data JPA** para mapeamento objeto-relacional.
* **Produtividade:** Opção de usar o **Lombok** para redução de código boilerplate (Getters/Setters).
* **Banco de Dados:** **MySQL Driver** com estrutura de chaves estrangeiras otimizada.
* **Frontend:** HTML5, CSS3 e JavaScript (Fetch API) para consumo assíncrono de dados.

---

## 📋 Funcionalidades e considerações importantes
O sistema foi projetado para permitir uma análise criteriosa dos dados, especialmente no relatório de devoluções:
* **Busca dos dados** Filtro dinâmico via JavaScript que permite localizar registros pelo **Nome do Animal**, por exemplo.
* **Relatórios Detalhados:** Uso de **DTOs (Data Transfer Objects)** para consolidar informações de múltiplas tabelas (Adoção, Animal e Adotante) em uma única visão clara.
* **Interface Limpa:** Tabelas com espaçamento otimizado e tipografia moderna para facilitar a leitura.
* **Validação dos dados/inputs:** Os dados de entrada são validados antes de qualquer ação (frontend - backend).
* **Base de Dados:** Todos os dados usados são ficiticios. Não tem vinculo com nenhuma pessoa ou animal real.

---

## 📸 Preview do Sistema

![java](https://github.com/user-attachments/assets/f5adb95e-e8ec-401b-889e-9feda2387fd3)

*Tabelas dinâmicas alimentadas por API REST.*

---

## 🛠️ Estrutura do Banco de Dados
O banco `adocoes` utiliza integridade referencial com as seguintes tabelas principais:
* `adocoes`: Tabela central com status (ATIVA/DEVOLVIDA).
* `animais`: Identificados por `animais_id`.
* `adotantes`: Identificados por `adotantes_id`.

---

## 📂 Como Executar o Projeto
1.  **Banco de Dados:** Certifique-se de que o MySQL está rodando e crie o schema conforme o script SQL.
2.  **Configuração:** Ajuste as credenciais do banco em `src/main/resources/application.properties`.
3.  **Build:** Execute o projeto como **Spring Boot App** na sua IDE (Eclipse/VS Code) ou via terminal:
    ```bash
    mvn spring-boot:run
    ```
4.  **Acesso:** O sistema estará disponível em `http://localhost:8080`.

---
**Autor:** Sander Gustavo Piva  
*Desenvolvido com foco em organização, arquitetura limpa e padronização visual.*
