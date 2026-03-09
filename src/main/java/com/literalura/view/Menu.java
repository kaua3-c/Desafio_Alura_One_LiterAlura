package com.literalura.view;

import com.literalura.model.*;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LivroRepository;
import com.literalura.service.ConsumoApi;
import com.literalura.service.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
@Component
public class Menu {
    private final ConsumoApi consumoApi;
    private final ConverteDados conversor;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private Scanner leitura = new Scanner(System.in);
    private String endereco ="https://gutendex.com/books/";

    private String menuOpcoes = """
                1 - Consultar Livros
                2 - Listar Autores cadastrados
                3 - Listar Livros cadastrados
                4 - Listar autor por Data
                5 - Listar livro por idioma
               
                0 - Sair                                 
                """;

    public Menu(ConsumoApi consumoApi, ConverteDados conversor, LivroRepository repositorioLivro, AutorRepository autorRepository) {
        this.consumoApi = consumoApi;
        this.conversor = conversor;
        this.livroRepository = repositorioLivro;
        this.autorRepository = autorRepository;
    }

    public void exibirMenu(){

        int opcao = -1;
        while(opcao != 0)
        {
            System.out.println(menuOpcoes);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao)
            {
                case 1:
                    System.out.println("Consultar livro");
                    buscaLivroNome();
                    break;
                case 2:
                    System.out.println("listar todos os autores");
                    obterAutores();
                    break;
                case 3:
                    System.out.println("Listar todos os livros");
                    obterTodosLivros();
                    break;
                case 4:
                    System.out.println("Listar autor por data de nascimento");
                    buscarAutorPorDataNascimento();
                case 5:
                    System.out.println("Listar livro por idioma");
                    buscarLivroPorIdioma();
                case 0:
                    break;
                default:
                    System.out.println("opção invalida");

            }
        }
    }

    private void buscaLivroNome() {

        DadosLivro dadosLivro = obterLivro();

        if (dadosLivro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        // pega o primeiro autor da lista
        DadosAutor dadosAutor = dadosLivro.getAutores().isEmpty()
                ? null
                : dadosLivro.getAutores().get(0);

        if (dadosAutor == null) {
            System.out.println("Autor não encontrado.");
            return;
        }

        // verifica se autor já existe no banco
        Autor autor = autorRepository
                .findByNomeIgnoreCase(dadosAutor.getNome())
                .orElseGet(() -> {
                    Autor novoAutor = new Autor();
                    novoAutor.setNome(dadosAutor.getNome());
                    novoAutor.setDataNascimento(dadosAutor.getDataNascimento());
                    novoAutor.setDataFalecimento(dadosAutor.getDataFalecimento());
                    return autorRepository.save(novoAutor);
                });

        // cria o livro
        Livro livro = new Livro();
        livro.setTitulo(dadosLivro.getTitulo());
        livro.setAutor(autor);
        livro.setDownloads(dadosLivro.getQuantideDownload());
        String idioma = dadosLivro.getIdioma().isEmpty()
                ? "desconhecido"
                : dadosLivro.getIdioma().get(0);

        livro.setIdioma(idioma);

        livroRepository.save(livro);

        System.out.println("Livro salvo com sucesso!");
    }

   private DadosLivro obterLivro(){
       System.out.println("Qual o nome do livro desejado?");
        String nomeLivro = leitura.nextLine();
        var json = consumoApi.obterDados(endereco + "?search=" + nomeLivro.replace(" ", "+"));

       DadosResposta resposta = conversor.obterDados(json, DadosResposta.class);
       if (resposta == null || resposta.getResults().isEmpty()) {
           return null;
       }

       return resposta.getResults().get(0);
   }
   private void obterAutores(){
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);

    }
    private void obterTodosLivros()
    {
        List <Livro>  livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }
    public void buscarAutorPorDataNascimento(){
        System.out.println("Digite o ano para pesquisar autores vivos:");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = autorRepository.autoresVivosNoAno(ano);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
        } else {
            autores.forEach(System.out::println);
        }
    }
    public void buscarLivroPorIdioma(){
        System.out.println("""
            Escolha o idioma:
            es - espanhol
            en - inglês
            fr - francês
            pt - português
            """);
        String idioma = leitura.nextLine();
        List<Livro> livros = livroRepository.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado nesse idioma.");
        } else {
            livros.forEach(System.out::println);
        }
    }
}





