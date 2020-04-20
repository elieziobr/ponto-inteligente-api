package com.curso.pontointeligente.api.controller;

import com.curso.pontointeligente.api.dtos.CadastroPJDto;
import com.curso.pontointeligente.api.entities.Empresa;
import com.curso.pontointeligente.api.entities.Funcionario;
import com.curso.pontointeligente.api.enums.PerfilEnum;
import com.curso.pontointeligente.api.response.Response;
import com.curso.pontointeligente.api.services.EmpresaService;
import com.curso.pontointeligente.api.services.FuncionarioService;
import com.curso.pontointeligente.api.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequestMapping("/api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPJController {
    private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private EmpresaService empresaService;

    /**
     * Cadastra um funcionário pessoa física no sistema
     * @param cadastroPJDto
     * @param result
     * @return
     * @throws NoSuchAlgorithmException
     */
    @PostMapping
    public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto, BindingResult result) throws NoSuchAlgorithmException {
        log.info("Cadastrando PJ: {}", cadastroPJDto.toString());
        Response<CadastroPJDto> response = new Response<>();

        validarDadosExistentes(cadastroPJDto, result);
        Funcionario funcionario = converterDtoParaFuncionario(cadastroPJDto, result);

        if (result.hasErrors()) {
            log.error("Erro validando dados de cadatro PF {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Empresa> empresa = empresaService.buscarPorCnpj(cadastroPJDto.getCnpj());
        empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
        funcionarioService.persistir(funcionario);

        response.setData(converterEntidadeParaDto(funcionario));
        return ResponseEntity.ok(response);

    }

    /**
     * Verifica se a empresa está cadastrada e se o funcionário não existe na base de dados
     * @param dto
     * @param result
     */
    private void validarDadosExistentes(CadastroPJDto dto, BindingResult result) {
        Optional<Empresa> empresa = empresaService.buscarPorCnpj(dto.getCnpj());

        if (!empresa.isPresent()) {
            result.addError(new ObjectError("empresa", "Empresa não cadastrada"));
        }

        this.funcionarioService.buscarPorCpf(dto.getCpf())
                .ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

        this.funcionarioService.buscarPorEmail(dto.getEmail())
                .ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
    }

    private CadastroPJDto converterEntidadeParaDto(Funcionario funcionario) {
        CadastroPJDto dto = new CadastroPJDto();

        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setEmail(funcionario.getEmail());
        dto.setCpf(funcionario.getCpf());
        dto.setCnpj(funcionario.getEmpresa().getCnpj());
        funcionario.getQtdHorasAlmocoOpt().ifPresent(qtdHorasAlmoco -> dto.setQtdHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
        funcionario.getQtdHorasTrabalhoDiaOpt().ifPresent(
                qtdHorasTrabDia -> dto.setQtdHorasTrabalhoDia(Optional.of(Float.toString(qtdHorasTrabDia))));
        funcionario.getValorHoraOpt()
                .ifPresent(valorHora -> dto.setValorHora(Optional.of(valorHora.toString())));

        return dto;
    }

    /**
     * Converte os dados do DTO para o Entidade
     * @param dto
     * @param result
     * @return
     * @throws NoSuchAlgorithmException
     */
    private Funcionario converterDtoParaFuncionario(CadastroPJDto dto, BindingResult result) throws NoSuchAlgorithmException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setCpf(dto.getCpf());
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(PasswordUtils.gerarBCrypt(dto.getSenha()));
        dto.getQtdHorasAlmoco()
                .ifPresent(qtdHorasAlmoco -> funcionario.setQtdHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));
        dto.getQtdHorasTrabalhoDia()
                .ifPresent(qtdHorasTrabDia -> funcionario.setQtdHorasTrabalhoDia(Float.valueOf(qtdHorasTrabDia)));
        dto.getValorHora().ifPresent(valorHora -> funcionario.setValorHora(new BigDecimal(valorHora)));

        return funcionario;
    }
}
