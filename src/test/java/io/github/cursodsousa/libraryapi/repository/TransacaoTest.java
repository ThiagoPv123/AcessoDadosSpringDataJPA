package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacaoTest {

    @Autowired
    TransacaoService transaçaoService;

    @Test
    void transacaoSimples(){

        transaçaoService.executar();
    }

    @Test
    void transacaoEstadoManaged(){

        transaçaoService.atualizacaoSemAtualizar();
    }
}
