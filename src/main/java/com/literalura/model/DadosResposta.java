package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.literalura.model.DadosLivro;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosResposta {

    private List<DadosLivro> results;

    public List<DadosLivro> getResults() {
        return results;
    }

    public void setResults(List<DadosLivro> results) {
        this.results = results;
    }
}