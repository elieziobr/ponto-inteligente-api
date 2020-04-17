package com.curso.pontointeligente.api.utils;

import com.curso.pontointeligente.api.entities.Empresa;
import com.curso.pontointeligente.api.entities.Funcionario;
import com.curso.pontointeligente.api.entities.Lancamento;
import com.curso.pontointeligente.api.enums.PerfilEnum;
import com.curso.pontointeligente.api.enums.TipoEnum;

import java.util.Date;

public class GeradorEntidade {

    public static final String EMAIL = "eliezio@email.com";
    public static final String CPF = "89545419172";

    public static Empresa obterDadosEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("Empresa para Cadastro de Teste");
        empresa.setCnpj("20115888000115");
        return empresa;
    }

    public static Funcionario obterDadosFuncionario(Empresa empresa) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome("Marcus Antônio");
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(PasswordUtils.gerarBCrypt("123456"));
        funcionario.setCpf(CPF);
        funcionario.setEmail(EMAIL);
        funcionario.setEmpresa(empresa);

        return funcionario;
    }

    public static Lancamento obterDadosLancamentos(Funcionario funcionario) {
        Lancamento lancameto = new Lancamento();
        lancameto.setData(new Date());
        lancameto.setTipo(TipoEnum.INICIO_ALMOCO);
        lancameto.setFuncionario(funcionario);
        return lancameto;
    }
}
