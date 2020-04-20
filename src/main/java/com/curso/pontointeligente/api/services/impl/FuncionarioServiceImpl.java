package com.curso.pontointeligente.api.services.impl;

import com.curso.pontointeligente.api.entities.Funcionario;
import com.curso.pontointeligente.api.repositories.FuncionarioRepository;
import com.curso.pontointeligente.api.services.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @Override
    public Funcionario persistir(Funcionario funcionario) {
        log.info("Persistindo o Funcinario {}", funcionario);
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Optional<Funcionario> buscarPorCpf(String cpf) {
        log.info("Buscando Funcionário por CPF {}", cpf);
        return Optional.ofNullable(funcionarioRepository.findByCpf(cpf));
    }

    @Override
    public Optional<Funcionario> buscarPorEmail(String email) {
        log.info("Buscando Funcionário por Email {}", email);
        return Optional.ofNullable(funcionarioRepository.findByEmail(email));
    }

    @Override
    public Optional<Funcionario> buscaPorId(Long id) {
        log.info("Buscando Funcionário por ID {}", id);
        return funcionarioRepository.findById(id);
    }
}
