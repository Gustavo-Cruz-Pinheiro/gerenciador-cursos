/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.trabalhofinal.view;

/**
 *
 * @author Gustavo
 */

import br.com.trabalhofinal.controller.CursoController;
import br.com.trabalhofinal.modelo.Curso;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListagemCursosView extends JFrame {
    private JList<String> cursosList;
    private DefaultListModel<String> listModel;
    private CursoController cc;
        
    public ListagemCursosView() {
        cc = new CursoController();
        List<Curso> cursos = cc.getCursos();
        setTitle("Listagem de Cursos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1370, 700);

        JPanel panel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        for (Curso c : cursos) {            
            String item = "Id: " + c.getId() + 
            " - Titulo: " + c.getTitulo() +
            " - Descricao: " + c.getDescricao() +
            " - Quantidade de horas: " + c.getQtd_horas() +
            " - Data de inicio: " + c.getData_inicio() +
            " - Data de finalizacao: " + c.getData_fim() +
            " - Nome da empresa que disponibiliza o curso: " + c.getNome_empresa();
            listModel.addElement(item);
        }

        cursosList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(cursosList);

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCursoSelecionado();
            }
        });

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCursoSelecionado();
            }
        });
        
        JButton btnTotalHoras = new JButton("Calcular Horas");
        btnTotalHoras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularHoras();
            }
        });
        
        JButton btnTotalHorasPorAno = new JButton("Calcular Horas Intervalo");
        btnTotalHorasPorAno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularHorasPorAno();
            }
        });
        
        JButton btnCursosFinalizados = new JButton("Listar Cursos Finalizados");
        btnCursosFinalizados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListagemCursosFinalizadosView().setVisible(true);
                dispose();  
            }
        });
        
        JButton btnCursosPorAno = new JButton("Listar Cursos por Intervalo");
        btnCursosPorAno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCursosPorAno();
            }
        });
        
        JButton btnCadastrarCurso = new JButton("Cadastrar Curso");
        btnCadastrarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroCursoView().setVisible(true);
                dispose();  
            }
        });
        
        JButton btnBuscarPorTitulo = new JButton("Buscar por Titulo");
        btnBuscarPorTitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorTitulo();
            }
        });
        
        JButton btnBuscarPorEmpresa = new JButton("Buscar por Empresa");
        btnBuscarPorEmpresa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorEmpresa();
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnExcluir);
        btnPanel.add(btnAtualizar);
        btnPanel.add(btnTotalHoras);
        btnPanel.add(btnTotalHorasPorAno);
        btnPanel.add(btnCursosFinalizados);
        btnPanel.add(btnCursosPorAno);
        btnPanel.add(btnCadastrarCurso);
        btnPanel.add(btnBuscarPorTitulo);
        btnPanel.add(btnBuscarPorEmpresa);

        panel.add(btnPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private void excluirCursoSelecionado() {
        int selectedIndex = cursosList.getSelectedIndex();
        if (selectedIndex != -1) {
            String item = cursosList.getSelectedValue();
            String[] parts = item.split(" - ");
            String idPart = parts[0];
            int id = Integer.parseInt(idPart.substring(4));
            cc.removerCurso(id);
            listModel.remove(selectedIndex);
            JOptionPane.showMessageDialog(this, "Curso removido com sucesso!");
        }
    }

    private void atualizarCursoSelecionado() {
        int selectedIndex = cursosList.getSelectedIndex();
        if (selectedIndex != -1) {
            String item = cursosList.getSelectedValue();
            String[] parts = item.split(" - ");
            String idPart = parts[0];
            int id = Integer.parseInt(idPart.substring(4));

            EditarCursoView editarView = new EditarCursoView(id);
            editarView.setVisible(true);
            JOptionPane.showMessageDialog(this, "Editar curso com ID: " + id);
            dispose();
        }
    }
    
    private void calcularHoras() {
        this.cc = new CursoController();
        
        cc.contabilizarHorasCursos();
    }
    
    private void calcularHorasPorAno() {
        this.cc = new CursoController();
        
        int inicio = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano inicial"));
        int fim = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano final"));
        
        JOptionPane.showMessageDialog(this,  "Total de Horas(" + inicio + " a " + fim + ")= " + cc.contabilizarHorasCursosPorPeriodo(inicio, fim));
    }
    
    private void listarCursosPorAno() {
        this.cc = new CursoController();
        
        int inicio = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano inicial"));
        int fim = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano final"));
        
        ListarCursoPorAnosView listarPorAnoView = new ListarCursoPorAnosView(inicio, fim);
        listarPorAnoView.setVisible(true);
        dispose();
    }
    
    private void buscarPorTitulo() {
        this.cc = new CursoController();
        
        String titulo = JOptionPane.showInputDialog("Digite o titulo");
        
        ListarCursoPorTituloView listarPorTituloView = new ListarCursoPorTituloView(titulo);
        listarPorTituloView.setVisible(true);
        dispose();
    }
    
    private void buscarPorEmpresa() {
        this.cc = new CursoController();
        
        String empresa = JOptionPane.showInputDialog("Digite o nome empresa");
        
        ListarCursoPorEmpresaView listarPorEmpresaView = new ListarCursoPorEmpresaView(empresa);
        listarPorEmpresaView.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        ListagemCursosView listCurView = new ListagemCursosView();
        listCurView.setVisible(true);
    }
}
