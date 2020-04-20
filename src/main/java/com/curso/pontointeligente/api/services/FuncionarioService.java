package com.curso.pontointeligente.api.services;

import com.curso.pontointeligente.api.entities.Funcionario;

import java.util.Optional;

public interface FuncionarioService {

    /**
     * Persiste um funcionario na base de dados
     * @param funcionario
     * @return
     */
    Funcionario persistir(Funcionario funcionario);

    /**
     * Busca um funcionario por cpf
     * @param cpf
     * @return
     */
    Optional<Funcionario> buscarPorCpf(String cpf);

    /**
     * Busca um funcionario por email
     * @param email
     * @return
     */
    Optional<Funcionario> buscarPorEmail(String email);

    /**
     * Busca um funcionario por Id
     * @param id
     * @return
     */
    Optional<Funcionario> buscaPorId(Long id);
}
