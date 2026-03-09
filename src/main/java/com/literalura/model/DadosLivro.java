package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosLivro {

    @JsonAlias("title")
    private String titulo;

    @JsonProperty("download_count")
    private Integer quantideDownload;

    @JsonAlias("authors")
    private List<DadosAutor> autores;

    @JsonAlias("languages")
    private List<String> idioma;

    public String getTitulo() {
        return titulo;
    }

    public Integer getQuantideDownload() {
        return quantideDownload;
    }

    public List<DadosAutor> getAutores() {
        return autores;
    }

    public List<String> getIdioma() {
        return idioma;
    }
}

