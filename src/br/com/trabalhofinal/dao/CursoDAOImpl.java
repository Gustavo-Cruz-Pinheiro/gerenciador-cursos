package br.com.trabalhofinal.dao;

import br.com.trabalhofinal.modelo.Curso;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class CursoDAOImpl implements CursoDAO {

    private Connection conn;

    public CursoDAOImpl() {
    }

    @Override
    public void adicionar(Curso curso) {
        String sql = "INSERT INTO cursos (titulo, descricao, qtd_horas, data_inicio, data_fim, nome_empresa) VALUES (?, ?, ?, ?, ?, ?)";
        try ( Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getTitulo());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getQtd_horas());
            stmt.setDate(4, java.sql.Date.valueOf(curso.getData_inicio()));
            stmt.setDate(5, java.sql.Date.valueOf(curso.getData_fim()));
            stmt.setString(6, curso.getNome_empresa());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT id, titulo, descricao, qtd_horas, data_inicio, data_fim, nome_empresa FROM cursos";
        try ( Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                int qtd_horas = rs.getInt("qtd_horas");
                LocalDate data_inicio = rs.getDate("data_inicio").toLocalDate();
                LocalDate data_fim = rs.getDate("data_fim").toLocalDate();
                String nome_empresa = rs.getString("nome_empresa");
                
                Curso curso = new Curso(id, titulo, descricao, qtd_horas, data_inicio, data_fim, nome_empresa);
                cursos.add(curso);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return cursos;
    }

    @Override
    public Curso buscarPorId(int id) {
        String sql = "SELECT * FROM cursos WHERE id = ?";

        try(Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Curso cur = new Curso();
                
                cur.setId(rs.getInt("id"));
                cur.setTitulo(rs.getString("titulo"));
                cur.setDescricao(rs.getString("descricao"));
                cur.setQtd_horas(rs.getInt("qtd_horas"));
                cur.setData_inicio(rs.getDate("data_inicio").toLocalDate());
                cur.setData_fim(rs.getDate("data_fim").toLocalDate());
                cur.setNome_empresa(rs.getString("nome_empresa"));
                
                rs.close();
                
                return cur;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Mensagem " + e.getMessage());
        }
        
        return new Curso();
    }
    
    @Override
    public List<Curso> buscarPorTitulo(String tituloBusca) {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos WHERE titulo LIKE ?";

        try(Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, '%' + tituloBusca + '%');
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                int qtd_horas = rs.getInt("qtd_horas");
                LocalDate data_inicio = rs.getDate("data_inicio").toLocalDate();
                LocalDate data_fim = rs.getDate("data_fim").toLocalDate();
                String nome_empresa = rs.getString("nome_empresa");
                
                Curso curso = new Curso(id, titulo, descricao, qtd_horas, data_inicio, data_fim, nome_empresa);
                cursos.add(curso);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Mensagem " + e.getMessage());
        }
        
        return cursos;
    }

    @Override
    public boolean remover(int id) {
        String sql = "DELETE FROM cursos WHERE id = ?";

        try(Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            
            return stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Mensagem " + e.getMessage());
            
            return false;
        }
    }
    
    @Override
    public void alterar(Curso curso) {
        String sql = "UPDATE cursos SET titulo = ?, descricao = ?, qtd_horas = ?, data_inicio = ?, data_fim = ?, nome_empresa = ? WHERE id = ?";

        try(Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, curso.getTitulo());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getQtd_horas());
            stmt.setDate(4, java.sql.Date.valueOf(curso.getData_inicio()));
            stmt.setDate(5, java.sql.Date.valueOf(curso.getData_fim()));
            stmt.setString(6, curso.getNome_empresa());
            stmt.setInt(7, curso.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Mensagem " + e.getMessage());
        }
    }
    
    @Override
    public void contabilizarHoras() {
        String sql = "SELECT SUM(qtd_horas) FROM cursos";
        try ( Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            rs.next();
            
            int totalHoras = rs.getInt("SUM(qtd_horas)");
            
            rs.close();
            
            JOptionPane.showMessageDialog(null, "Total de Horas = " + totalHoras);           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Mensagem " + e.getMessage());
        }
    }
    
    @Override
    public List<Curso> listagemPorIntervalo(int inicio, int fim) {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT id, titulo, descricao, qtd_horas, data_inicio, data_fim, nome_empresa FROM cursos WHERE YEAR(data_inicio) >= ? AND YEAR(data_fim) <= ?";
        try ( Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inicio);
            stmt.setInt(2, fim);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                int qtd_horas = rs.getInt("qtd_horas");
                LocalDate data_inicio = rs.getDate("data_inicio").toLocalDate();
                LocalDate data_fim = rs.getDate("data_fim").toLocalDate();
                String nome_empresa = rs.getString("nome_empresa");
                
                Curso curso = new Curso(id, titulo, descricao, qtd_horas, data_inicio, data_fim, nome_empresa);
                cursos.add(curso);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return cursos;
    }
    
    @Override
    public int contabilizarHorasPorPeriodo(int inicio, int fim) {
        String sql = "SELECT SUM(qtd_horas) FROM cursos WHERE YEAR(data_inicio) >= ? AND YEAR(data_fim) <= ?";
        try ( Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql); ) {
            stmt.setInt(1, inicio);
            stmt.setInt(2, fim);
            
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            
            int totalHoras = rs.getInt("SUM(qtd_horas)");
            
            rs.close();
            
            return totalHoras;            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Mensagem " + e.getMessage());
        }
        
        return -1;
    }
    
    public List<Curso> listagemFinalizados() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT id, titulo, descricao, qtd_horas, data_inicio, data_fim, nome_empresa FROM cursos WHERE data_fim <= CURDATE()";
        try ( Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                int qtd_horas = rs.getInt("qtd_horas");
                LocalDate data_inicio = rs.getDate("data_inicio").toLocalDate();
                LocalDate data_fim = rs.getDate("data_fim").toLocalDate();
                String nome_empresa = rs.getString("nome_empresa");

                Curso curso = new Curso(id, titulo, descricao, qtd_horas, data_inicio, data_fim, nome_empresa);
                cursos.add(curso);
            } if(!rs.isAfterLast()) {
                JOptionPane.showMessageDialog(null, "Nenhum curso/capacitacao cadastrada foi finalizada!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return cursos;
    }
    
    @Override
    public List<Curso> listagemPorEmpresa(String empresa) {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT id, titulo, descricao, qtd_horas, data_inicio, data_fim, nome_empresa FROM cursos WHERE nome_empresa LIKE ?";
        try ( Connection conn = new ConnectionFactory().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, '%' + empresa + '%');
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                int qtd_horas = rs.getInt("qtd_horas");
                LocalDate data_inicio = rs.getDate("data_inicio").toLocalDate();
                LocalDate data_fim = rs.getDate("data_fim").toLocalDate();
                String nome_empresa = rs.getString("nome_empresa");
                
                Curso curso = new Curso(id, titulo, descricao, qtd_horas, data_inicio, data_fim, nome_empresa);
                cursos.add(curso);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return cursos;
    }
}
