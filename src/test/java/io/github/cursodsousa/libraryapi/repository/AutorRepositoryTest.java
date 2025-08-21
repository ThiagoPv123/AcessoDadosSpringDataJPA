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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1952, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("fa6c2bc0-9448-48fe-b214-fbc2b35d0770");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("faae5c49-40f7-41e1-9f1d-6a00f302a8a2");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("1286a33e-74c2-4713-9d39-90a7039eaeb1");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Churros");
        autor.setNacionalidade("Indiano");
        autor.setDataNascimento(LocalDate.of(1982, 10, 21));

        Livro livro = new Livro();
        livro.setIsbn("92228-3321");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Scooby-Doo 2");
        livro.setDataPublicacao(LocalDate.of(1982, 9, 17));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("93328-3321");
        livro2.setPreco(BigDecimal.valueOf(299));
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setTitulo("Interestelar 2");
        livro2.setDataPublicacao(LocalDate.of(2018, 8, 3));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

    }

    @Test
    void listarLivrosAutor(){
        UUID id = UUID.fromString("a89d0368-8c94-4ebd-8328-9664e7d36ebc");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);

    }
}
