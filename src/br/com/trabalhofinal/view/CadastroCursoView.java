package br.com.trabalhofinal.view;

/**
 *
 * @author Gustavo
 */

import br.com.trabalhofinal.controller.CursoController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastroCursoView extends JFrame {
    private JTextField tituloField;
    private JTextField descricaoField;
    private JTextField qtd_horasField;
    private JTextField data_inicioField;
    private JTextField data_fimField;
    private JTextField nome_empresaField;
    private CursoController cc;

    public CadastroCursoView() {
        cc = new CursoController();
        setTitle("Cadastro de Curso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel tituloLabel = new JLabel("Titulo:");
        tituloField = new JTextField();
        JLabel descricaoLabel = new JLabel("Descricao:");
        descricaoField = new JTextField();
        JLabel qtd_horasLabel = new JLabel("Quantidade de horas:");
        qtd_horasField = new JTextField();
        JLabel data_inicioLabel = new JLabel("Data inicio:");
        data_inicioField = new JTextField();
        JLabel data_fimLabel = new JLabel("Data fim:");
        data_fimField = new JTextField();
        JLabel nome_empresaLabel = new JLabel("Nome Empresa:");
        nome_empresaField = new JTextField();

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCurso();
            }
        });
        
        JButton ListagemButton = new JButton("Listagem");
        ListagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListagemCursosView().setVisible(true);
                dispose();      
            }
        });

        panel.add(tituloLabel);
        panel.add(tituloField);
        panel.add(descricaoLabel);
        panel.add(descricaoField);
        panel.add(qtd_horasLabel);
        panel.add(qtd_horasField);
        panel.add(data_inicioLabel);
        panel.add(data_inicioField);
        panel.add(data_fimLabel);
        panel.add(data_fimField);
        panel.add(nome_empresaLabel);
        panel.add(nome_empresaField);
        panel.add(new JLabel());
        panel.add(cadastrarButton);
        panel.add(new JLabel());
        panel.add(ListagemButton);

        getContentPane().add(panel);
    }

    private void cadastrarCurso() {
        if("".equals(tituloField.getText()) ||
                "".equals(descricaoField.getText()) ||
                "".equals(qtd_horasField.getText()) ||
                "".equals(data_inicioField.getText()) ||
                "".equals(data_fimField.getText()) ||
                "".equals(nome_empresaField.getText())
        ) {
            JOptionPane.showMessageDialog(this, "Nenhum dado pode estar em branco!");
        } else {
            String titulo = tituloField.getText();
            String descricao = descricaoField.getText();
            int qtd_horas = Integer.parseInt(qtd_horasField.getText());
            String data_inicio = data_inicioField.getText();
            String data_fim = data_fimField.getText();
            String nome_empresa = nome_empresaField.getText();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ldi = LocalDate.parse(data_inicio, dtf);
            LocalDate ldf = LocalDate.parse(data_fim, dtf);

            cc.getCurso().setTitulo(titulo);    
            cc.getCurso().setDescricao(descricao);
            cc.getCurso().setQtd_horas(qtd_horas);
            cc.getCurso().setData_inicio(ldi);
            cc.getCurso().setData_fim(ldf);
            cc.getCurso().setNome_empresa(nome_empresa);

            cc.cadastrarCurso();
            JOptionPane.showMessageDialog(this, "Curso cadastrado com sucesso!");

            tituloField.setText("");
            descricaoField.setText("");
            qtd_horasField.setText("");
            data_inicioField.setText("");
            data_fimField.setText("");
            nome_empresaField.setText("");
        }   
    }
}
