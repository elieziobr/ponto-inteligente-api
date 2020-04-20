package com.curso.pontointeligente.api.services;

import com.curso.pontointeligente.api.entities.Funcionario;
import com.curso.pontointeligente.api.repositories.FuncionarioRepository;
import com.curso.pontointeligente.api.utils.GeradorEntidade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class FuncionarioServiceTest {

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @BeforeEach
    void setUp() {
        BDDMockito.given(funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
        BDDMockito.given(funcionarioRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Funcionario()));
        BDDMockito.given(funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
        BDDMockito.given(funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
    }

    @Test
    void persistir() {
        Funcionario funcionario = funcionarioService.persistir(new Funcionario());

        Assertions.assertNotNull(funcionario);
    }

    @Test
    void buscarPorCpf() {
        Optional<Funcionario> funcionario = funcionarioService.buscarPorCpf(GeradorEntidade.CPF);

        Assertions.assertTrue(funcionario.isPresent());
    }

    @Test
    void buscaPorEmail() {
        Optional<Funcionario> funcionario = funcionarioService.buscarPorEmail(GeradorEntidade.EMAIL);

        Assertions.assertTrue(funcionario.isPresent());
    }

    @Test
    void buscaPorId() {
        Optional<Funcionario> funcionario = funcionarioService.buscaPorId(1L);

        Assertions.assertTrue(funcionario.isPresent());
    }
}
