package com.curso.pontointeligente.api.services;

import com.curso.pontointeligente.api.entities.Empresa;

import java.util.Optional;

public interface EmpresaService {

    /**
     * Retorna uma empresa de um cnpj
     * @param cnpj
     * @return
     */
    Optional<Empresa> buscarPorCnpj(String cnpj);

    Empresa persistir(Empresa empresa);
}
