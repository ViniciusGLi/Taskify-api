package com.taskifi.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskifi.api.model.Projeto;
import com.taskifi.api.repository.ProjetoRepository;

@RestController
@RequestMapping(value = "/projetos")
public class ProjetoController {

    @PostMapping
    public Projeto cadastrarNovoProjeto(@RequestBody Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @GetMapping
    public Page<Projeto> listarProjetos(Pageable paginacao) {
        return projetoRepository.findAll(paginacao);
    }

    @GetMapping("/{idProjeto}")
    public Optional<Projeto> buscarProjetoPeloId(@PathVariable("idProjeto") Long idProjeto) {
        return projetoRepository.findById(idProjeto);
    }

    @PutMapping("/{idProjeto}")
    public Projeto atualizarProjeto(
            @PathVariable("idProjeto") Long idProjeto,
            @RequestBody Projeto Projeto) {
        Optional<Projeto> projetoExistente = projetoRepository.findById(idProjeto);

        if (projetoExistente.isPresent()) {
            projetoExistente.get().setNome(Projeto.getNome());
            projetoExistente.get().setDescricao(Projeto.getDescricao());

            return projetoRepository.save(projetoExistente.get());
        }

        return null;
    }

    @DeleteMapping("/{idProjeto}")
    public String deletarUsuarioPeloId(@PathVariable("idProjeto") Long idprojeto) {
        projetoRepository.deleteById(idprojeto);

        return "Projeto deletado com sucesso!";
    }

    @Autowired
    private ProjetoRepository projetoRepository;

}
