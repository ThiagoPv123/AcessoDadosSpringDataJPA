package io.github.cursodsousa.libraryapi.repository;


import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("92108-1321");
        livro.setPreco(BigDecimal.valueOf(199));
        livro.setGenero(GeneroLivro.TERROR);
        livro.setTitulo("Invocação do mal");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository.findById(UUID.fromString("f36f942d-3cfa-4bb9-a61b-5512383a31de")).orElse(null);
        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("92108-1321");
        livro.setPreco(BigDecimal.valueOf(190));
        livro.setGenero(GeneroLivro.TERROR);
        livro.setTitulo("Invocação do mal 2");
        livro.setDataPublicacao(LocalDate.of(2024, 1, 14));

        Autor autor = new Autor();
        autor.setNome("AUAU");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2006, 5, 18));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("c91d25e4-3242-462f-855f-3a67a6233768");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("78e1fab3-1819-43d0-a9ca-31f9c0fbcb3b");
        Autor thiago = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(thiago);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("49b2e9e7-8fbc-4758-8dd6-fe8ccbf1bf36");
        repository.deleteById(id);
    }

    @Test
    void buscarLivroTest() {
        UUID id = UUID.fromString("a7350c5c-ba46-41ad-8977-6ee86f4506b0");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");

        System.out.println(livro.getTitulo());
        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("Invocação do mal");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbnTest() {
        List<Livro> lista = repository.findByIsbn("92108-1321");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloAndPreco() {
        String titulo = "Invocação do mal 2";
        var preco = BigDecimal.valueOf(190);
        List<Livro> lista = repository.findByTituloAndPreco(titulo, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros(){
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest(){
        var resultado = repository.findByGenero(GeneroLivro.TERROR, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPositionalParamTest(){
        var resultado = repository.findByGenero(GeneroLivro.TERROR, "preco");
        resultado.forEach(System.out::println);
    }
}