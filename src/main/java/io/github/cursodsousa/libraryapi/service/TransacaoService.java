package io.github.cursodsousa.libraryapi.service;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import io.github.cursodsousa.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    AutorRepository autorRepository;
    @Autowired
    LivroRepository livroRepository;

    /// livro(titulo, ...., nome_arquivo) -> id.png
    @Transactional
    public void salvarLivroComFoto(){
        // salva o livro
        // repository.save(livro);

        // pega o id do livro = livro.getId()
        // var id = livro.getId();

        // salvar foto do livro -> bucket na nuvem
        // bucketService.salvar(livro.getFoto(), id + ".png");

        // atualizar o nome do arquivo que foi salvo
        // livro.setNomeArquivoFoto(id + ".png");
        //repository.save(livro);
    }
    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("2912bb72-d774-4940-8854-0f219cbacfab")).orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024, 6, 1));


    }
    @Transactional
    public void executar(){
        // salva o autor
        Autor autor = new Autor();
        autor.setNome("Francisco");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2006, 5, 18));

        autorRepository.save(autor);

        if(autor.getNome().equals("Francisca")){
            throw new RuntimeException("Rollback!");
        }
        // salva o livro
        Livro livro = new Livro();
        livro.setIsbn("92108-1321");
        livro.setPreco(BigDecimal.valueOf(190));
        livro.setGenero(GeneroLivro.TERROR);
        livro.setTitulo("Livro do Francisco");
        livro.setDataPublicacao(LocalDate.of(2024, 1, 14));



        livro.setAutor(autor);

        livroRepository.save(livro);
    }
}
