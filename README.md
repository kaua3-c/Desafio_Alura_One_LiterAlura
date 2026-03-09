# 📚 Literalura

Aplicação Java desenvolvida com Spring Boot que consome a API pública Gutendex para buscar livros, armazená-los em banco de dados e permitir consultas sobre autores e obras.

Este projeto foi desenvolvido como prática de consumo de API, persistência com JPA/Hibernate e consultas em banco de dados.

## 🚀 Tecnologias utilizadas

Java 17+

Spring Boot

Spring Data JPA

Hibernate

PostgreSQL

API Gutendex

Maven

## 📡 API utilizada

O projeto consome dados da API pública:

https://gutendex.com

Ela fornece informações sobre livros do Project Gutenberg, incluindo:

título
autor
idiomas
número de downloads
datas de nascimento e morte dos autores

## ⚙️ Funcionalidades

A aplicação roda via menu interativo no console e permite:

1️⃣ Buscar livro pelo título
Busca um livro na API e salva no banco de dados com suas informações.

2️⃣ Listar livros registrados
Mostra todos os livros armazenados no banco.

3️⃣ Listar autores registrados
Exibe os autores cadastrados juntamente com seus livros.

4️⃣ Listar autores vivos em determinado ano
Permite informar um ano e retorna os autores que estavam vivos naquele período.

5️⃣ Listar livros por idioma
Permite filtrar livros por idioma.

Idiomas disponíveis:

en → inglês
es → espanhol
fr → francês
pt → português


## 🧠 Modelo de dados
Autor

id

nome

dataNascimento
dataFalecimento
lista de livros
Livro

id

título
idioma
downloads
autor

Relacionamento:

Autor 1 --- N Livro

## 🔐 Variáveis de ambiente necessárias

Antes de executar o projeto, configure as seguintes variáveis:

Variável	Descrição	Exemplo


DB_HOST:	Endereço do banco	localhost:5432 


DB_NAME:	Nome do banco	challenge_literalura 


DB_USER:	Usuário do banco	postgres 


DB_PASSWORD:	Senha do banco	1234 
