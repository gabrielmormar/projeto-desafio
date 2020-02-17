package desafio.empresas.controllers;

import desafio.empresas.DTO.EmpresaNewDTO;
import desafio.empresas.domain.Empresa;
import desafio.empresas.services.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping(value = "/empresas")
@Api(value = "Empresa Controller")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    /**
     * GET - Retorna lista de todas empresas cadastradas
     *
     * @return
     */
    @GetMapping
    @ApiOperation(value = "Retorna lista de todas empresas cadastradas")
    public ResponseEntity<List<Empresa>> findAll() {
        List<Empresa> listaEmpresas = empresaService.findAll();
        return ResponseEntity.ok().body(listaEmpresas);
    }

    /**
     * GET - Buscar empresa pelo id
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Busca empresa pelo id")
    public ResponseEntity<Empresa> findById(@PathVariable Long id) {
        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.ok().body(empresa);
    }


    /**
     * POST - Criacao de nova empresa
     *
     * @param empresaDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "Criação de nova empresa")
    public ResponseEntity<Empresa> createEmpresa(@Valid @RequestBody EmpresaNewDTO empresaDTO) {
        Empresa obj = empresaService.fromDTO(empresaDTO);
        empresaService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * PUT - Atualiza Empresa pelo id
     *
     * @param empresa
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Atualização de uma empresa")
    public ResponseEntity<Object> updateEmpresa(@Valid @RequestBody EmpresaNewDTO empresa, @PathVariable Long id) {
        Empresa empresaFromDto = empresaService.fromDTO(empresa, id);
        Empresa obj = empresaService.update(empresaFromDto);
        return ResponseEntity.ok().body("Atualizado");
    }

    /**
     * DELETE - Deleta empresa pelo id
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Deleta empresa pelo id")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET - Filtra empresas pelo nome ou cnpj
     *
     * @param nome
     * @param cnpj
     * @param page
     * @param linesPerPage
     * @param orderBy
     * @param direction
     * @return
     */
    @GetMapping(value = "/filtro")
    @ApiOperation(value = "Filtra empresas pelo nome ou cnpj")
    public ResponseEntity<Page<Empresa>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
                                                  @RequestParam(value = "cnpj", defaultValue = "") String cnpj,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                  @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                  @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nomeDecoded = nome.isEmpty() && !cnpj.isEmpty() ? "-1" : decodeParam(nome);
        String cnpjDecoded = cnpj.isEmpty() && !nome.isEmpty() ? "-1" : decodeParam(cnpj);
        Page<Empresa> pageEmpresas = empresaService.searchFilter(nomeDecoded, cnpjDecoded, page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(pageEmpresas);
    }

    /**
     * Método auxiliar para decodificar query string
     *
     * @param str
     * @return
     */
    private static String decodeParam(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
