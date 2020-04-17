package com.curso.pontointeligente.api.repositories;

import com.curso.pontointeligente.api.entities.Empresa;
import com.curso.pontointeligente.api.entities.Funcionario;
import com.curso.pontointeligente.api.entities.Lancamento;
import com.curso.pontointeligente.api.utils.GeradorEntidade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class LancamentoRepositoryTest {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    private Long funcionarioId;

    @BeforeEach
    void setUp() {
        Empresa empresa = empresaRepository.save(GeradorEntidade.obterDadosEmpresa());

        Funcionario funcionario = funcionarioRepository.save(GeradorEntidade.obterDadosFuncionario(empresa));

        funcionarioId = funcionario.getId();

        lancamentoRepository.save(GeradorEntidade.obterDadosLancamentos(funcionario));
        lancamentoRepository.save(GeradorEntidade.obterDadosLancamentos(funcionario));
    }

    @AfterEach
    void tearDown() {
        empresaRepository.deleteAll();
    }

    @Test
    void buscarPorFuncionarioId() {
        List<Lancamento> lancamentos = lancamentoRepository.findByFuncionarioId(funcionarioId);

        Assertions.assertEquals(2, lancamentos.size());
    }

    @Test
    void buscarPorId() {
        PageRequest page = PageRequest.of(0, 10);

        Page<Lancamento> lancamentos = lancamentoRepository.findByFuncionarioId(funcionarioId, page);

        Assertions.assertEquals(2, lancamentos.getTotalElements());
    }
}
