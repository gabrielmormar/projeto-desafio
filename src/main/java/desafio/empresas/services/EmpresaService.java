package desafio.empresas.services;

import desafio.empresas.DTO.EmpresaNewDTO;
import desafio.empresas.domain.Empresa;
import desafio.empresas.domain.Endereco;
import desafio.empresas.enums.TipoEmpresa;
import desafio.empresas.exceptions.ObjectNotFoundException;
import desafio.empresas.repositories.EmpresaRepository;
import desafio.empresas.repositories.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Empresa findById(Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.orElseThrow(() -> new ObjectNotFoundException("não encontrado"));
    }

    public Page<Empresa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return empresaRepository.findAll(pageRequest);
    }

    public void delete(Long id) {
        Optional<Empresa> empresa = Optional.ofNullable(empresaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("não encontrado")));
        empresaRepository.deleteById(empresa.get().getId());
    }

    public Empresa save(Empresa empresa) {
        empresa.setId(null);
        Empresa emp = empresaRepository.save(empresa);
        enderecoRepository.saveAll(emp.getEnderecos());
        return emp;
    }

    public Empresa update(Empresa empresa) {
        Empresa empresa1 = empresaRepository.save(empresa);
        return empresa1;
    }

    public List<Empresa> findAll() {
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas;
    }

    public Empresa fromDTO(EmpresaNewDTO obj, Long id) {
        Empresa empresa = new Empresa(null, obj.getNome(), TipoEmpresa.toEnum(obj.getTipoEmpresa()),
                obj.getCnpj(), obj.getRazaoSocial(), obj.getEmail());
        empresa.setId(id);
        Empresa empUp = empresaRepository.findById(empresa.getId()).orElseThrow(() -> new ObjectNotFoundException("não encontrado"));
        Endereco endereco = new Endereco(empUp.getEnderecos().get(0).getId(), obj.getLogradouro(), obj.getNumero(), obj.getComplemento(),
                obj.getBairro(), obj.getEstado(), obj.getCidade(), obj.getCep(), empresa);
        enderecoRepository.save(endereco);
        return empresa;
    }

    public Empresa fromDTO(EmpresaNewDTO obj) {
        Empresa empresa = new Empresa(null, obj.getNome(), TipoEmpresa.toEnum(obj.getTipoEmpresa()),
                obj.getCnpj(), obj.getRazaoSocial(), obj.getEmail());
        Endereco endereco = new Endereco(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(),
                obj.getBairro(), obj.getEstado(), obj.getCidade(), obj.getCep(), empresa);
        empresa.getEnderecos().add(endereco);
        return empresa;
    }

    public Page<Empresa> searchFilter(String nome, String cnpj,Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<Empresa> empresasPage =  empresaRepository.findByNomeContainingOrCnpjContaining(nome, cnpj, pageRequest);
        return empresasPage;
    }
}
