package com.curso.pontointeligente.api.repositories;

import com.curso.pontointeligente.api.entities.Empresa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class EmpresaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;
    private static final String CNPJ = "20115888000115";

    @BeforeEach
    void setUp() {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("Empresa de Teste");
        empresa.setCnpj(CNPJ);
        empresaRepository.save(empresa);
    }

    @AfterEach
    void tearDown() {
        empresaRepository.deleteAll();
    }

    @Test
    void findByCnpj() {
        Empresa empresa = empresaRepository.findByCnpj(CNPJ);
        Assertions.assertEquals(CNPJ, empresa.getCnpj());
    }
}
