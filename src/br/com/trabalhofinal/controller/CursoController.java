package br.com.trabalhofinal.controller;

import br.com.trabalhofinal.dao.CursoDAOImpl;
import br.com.trabalhofinal.modelo.Curso;
import java.util.List;
import br.com.trabalhofinal.dao.CursoDAO;

public class CursoController {
    private Curso curso;
    private List<Curso> cursos;
    private CursoDAO dao;

    public CursoController() {
        novoCurso();
        
        dao = new CursoDAOImpl();
        
        carregarLista();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public void novoCurso() {
        this.curso = new Curso();
    }
    
    public void cadastrarCurso() {
        dao.adicionar(this.curso);
        
        novoCurso();
        
//        carregarLista();
    }
    
    public List<Curso> getCursos() {
        return cursos;
    }
    
    public void carregarLista() {
        cursos = dao.listarTodos();
    }
    
    public boolean removerCurso(int id) {
        boolean removido = dao.remover(id);
        
        carregarLista();
        
        return removido;
    }
    
    public void alterarCurso() {
        dao.alterar(this.curso);

        carregarLista();
    }
    
    public void buscarCursoPorId(int id) {
        this.curso = dao.buscarPorId(id);
    }
    
    public List<Curso> buscarCursoPorTitulo(String titulo) {
        return dao.buscarPorTitulo(titulo);
    }
    
    public void contabilizarHorasCursos() {
        dao.contabilizarHoras();
    }
    
    public List<Curso> listagemCursosPorIntervalo(int inicio, int fim) {
        return dao.listagemPorIntervalo(inicio, fim);
    }
    
    public int contabilizarHorasCursosPorPeriodo(int inicio, int fim) {
        return dao.contabilizarHorasPorPeriodo(inicio, fim);
    }
    
    public List<Curso> listagemCursosFinalizados() {
        return dao.listagemFinalizados();
    }
    
    public List<Curso> listagemCursosPorEmpresa(String empresa) {
        return dao.listagemPorEmpresa(empresa);
    }
}
