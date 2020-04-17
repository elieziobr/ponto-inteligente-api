package com.curso.pontointeligente.api.repositories;

import com.curso.pontointeligente.api.entities.Empresa;
import com.curso.pontointeligente.api.entities.Funcionario;
import com.curso.pontointeligente.api.enums.PerfilEnum;
import com.curso.pontointeligente.api.utils.GeradorEntidade;
import com.curso.pontointeligente.api.utils.PasswordUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    private static final String EMAIL = "eliezio@email.com";
    private static final String CPF = "89545419172";

    @BeforeEach
    void setUp() {
        Empresa empresa = empresaRepository.save(GeradorEntidade.obterDadosEmpresa());
        funcionarioRepository.save(GeradorEntidade.obterDadosFuncionario(empresa));
    }

    @AfterEach
    void tearDown() {
        this.empresaRepository.deleteAll();
    }

    @Test
    void findByCpf() {
        Funcionario funcionario = funcionarioRepository.findByCpf(CPF);

        Assertions.assertEquals(CPF, funcionario.getCpf());
    }

    @Test
    void findByEmail() {
        Funcionario funcionario = funcionarioRepository.findByEmail(EMAIL);

        Assertions.assertEquals(EMAIL, funcionario.getEmail());
    }

    @Test
    void findByCpfOrEmail() {
        Funcionario funcionario = funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);

        Assertions.assertNotNull(funcionario);
    }


}
