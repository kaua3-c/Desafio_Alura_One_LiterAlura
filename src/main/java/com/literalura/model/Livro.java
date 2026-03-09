package com.literalura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @ManyToOne
    private Autor autor;
    private String idioma;
    private Integer downloads;

    public Livro() {}

    private Livro(String titulo, String idioma, Autor autor, Integer downloads) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.autor = autor;
        this.downloads = downloads;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id:" + id +
                "| titulo:'" + titulo  +
                "| idioma:'" + idioma  +
                "| downloads:" + downloads +
                '}';
    }
}
