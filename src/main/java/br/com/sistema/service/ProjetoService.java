package br.com.sistema.service;

import br.com.sistema.model.Projeto;

import java.util.List;

public interface ProjetoService {

    public List<Projeto> findAll();
    public Projeto findById(Long id);
    public String validarProjeto(Projeto projeto);

    public boolean save(Projeto projeto);
    public boolean deleteById(Long id);

}
