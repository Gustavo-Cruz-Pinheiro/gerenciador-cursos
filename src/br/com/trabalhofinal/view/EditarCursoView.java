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

public class EditarCursoView extends JFrame {
    private int userId;
    private CursoController cc;
    private JTextField tituloField;
    private JTextField descricaoField;
    private JTextField qtd_horasField;
    private JTextField data_inicioField;
    private JTextField data_fimField;
    private JTextField nome_empresaField;
    private JButton btnSalvar;

    public EditarCursoView(int userId) {
        this.userId = userId;
        this.cc = new CursoController();

        setTitle("Editar Senha do Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
//        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        
        cc.buscarCursoPorId(this.userId);

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

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracaoUsuario();
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
        panel.add(btnSalvar);
        panel.add(new JLabel());
        panel.add(ListagemButton);

        getContentPane().add(panel);
    }

    private void salvarAlteracaoUsuario() {
            String titulo = tituloField.getText();
            String descricao = descricaoField.getText();
            if(!qtd_horasField.getText().equals("")) {
                int qtd_horas = Integer.parseInt(qtd_horasField.getText());
                if (qtd_horas > 0) {
                    cc.getCurso().setQtd_horas(qtd_horas);
                }
            } else {
                int qtd_horas = 0;
            }
            
            String data_inicio = data_inicioField.getText();
            String data_fim = data_fimField.getText();
            String nome_empresa = nome_empresaField.getText();DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate ldi = LocalDate.parse(data_inicio, dtf);
//            LocalDate ldf = LocalDate.parse(data_fim, dtf);

            cc.buscarCursoPorId(this.userId);
            
            if (!titulo.equals("")) {
                cc.getCurso().setTitulo(titulo);
            }
   
            if (!descricao.equals("")) {
                cc.getCurso().setDescricao(descricao);
            }

//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            if (!data_inicio.equals("")) {
                LocalDate ldi = LocalDate.parse(data_inicio, dtf);
                cc.getCurso().setData_inicio(ldi);
            }
            
            if (!data_fim.equals("")) {
                LocalDate ldf = LocalDate.parse(data_fim, dtf);
                cc.getCurso().setData_fim(ldf);
            }
            
            cc.alterarCurso();
            JOptionPane.showMessageDialog(this, "Curso atualizado com sucesso!");
            
            new ListagemCursosView().setVisible(true);

            dispose();
        }
}
