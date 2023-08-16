
package br.com.trabalhofinal.modelo;
import java.time.LocalDate;

public class Curso {
    private int id;
    private String titulo;
    private String descricao;
    private int qtd_horas;
    private LocalDate data_inicio;
    private LocalDate data_fim;
    private String nome_empresa;

    public Curso(int id, String titulo, String descricao, int qtd_horas, LocalDate data_inicio, LocalDate data_fim, String nome_empresa) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.qtd_horas = qtd_horas;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.nome_empresa = nome_empresa;
    }

    public Curso(String titulo, String descricao, int qtd_horas, LocalDate data_inicio, LocalDate data_fim) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.qtd_horas = qtd_horas;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public Curso() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtd_horas() {
        return qtd_horas;
    }

    public void setQtd_horas(int qtd_horas) {
        this.qtd_horas = qtd_horas;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }
}
