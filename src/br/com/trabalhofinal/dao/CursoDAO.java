package br.com.trabalhofinal.dao;

import br.com.trabalhofinal.modelo.Curso;
import java.util.List;

public interface CursoDAO {
    public void adicionar(Curso curso);
    public List<Curso> listarTodos();
    public Curso buscarPorId(int id);
    public List<Curso> buscarPorTitulo(String titulo);
    public boolean remover(int id);
    public void alterar(Curso curso);
    public void contabilizarHoras();
    public List<Curso> listagemPorIntervalo(int inicio, int fim);
    public int contabilizarHorasPorPeriodo(int inicio, int fim);
    public List<Curso> listagemFinalizados();
    public List<Curso> listagemPorEmpresa(String empresa);
}
