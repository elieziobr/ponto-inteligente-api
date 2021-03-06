package com.curso.pontointeligente.api.services.impl;

import com.curso.pontointeligente.api.entities.Lancamento;
import com.curso.pontointeligente.api.repositories.LancamentoRepository;
import com.curso.pontointeligente.api.services.LancamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Override
    public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
        log.info("Buscando Lançamento de Funcionario por ID {}", funcionarioId);
        return lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
    }

    @Override
    public Optional<Lancamento> buscarPorId(Long id) {
        log.info("Buscando Lançamento por ID {}", id);
        return lancamentoRepository.findById(id);
    }

    @Override
    public Lancamento persistir(Lancamento lancamento) {
        log.info("Persistindo Lançamento {}", lancamento);
        return lancamentoRepository.save(lancamento);
    }

    @Override
    public void remover(Long id) {
        log.info("Excluindo Lançamento por ID {}", id);
        lancamentoRepository.deleteById(id);
    }
}
