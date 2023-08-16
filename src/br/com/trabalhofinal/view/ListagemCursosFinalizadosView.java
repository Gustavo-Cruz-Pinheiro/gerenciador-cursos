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

public class ListagemCursosFinalizadosView extends JFrame {
    private JList<String> cursosList;
    private DefaultListModel<String> listModel;
    private CursoController cc;
        
    public ListagemCursosFinalizadosView() {
        cc = new CursoController();
        List<Curso> cursos = cc.listagemCursosFinalizados();
        setTitle("Listagem de Cursos Finalizados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);

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
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListagemCursosView().setVisible(true);
                dispose();  
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout());

        btnPanel.add(btnVoltar);

        panel.add(btnPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }
}
