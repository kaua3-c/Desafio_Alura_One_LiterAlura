package com.literalura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosAutor {

    @JsonAlias("name")
    private String nome;
    @JsonAlias("birth_year")
    private Integer dataNascimento;
    @JsonAlias("death_year")
    private Integer dataFalecimento;

    public String getNome() {
        return nome;
    }
    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public Integer getDataFalecimento() {
        return dataFalecimento;
    }

}