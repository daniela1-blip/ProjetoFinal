import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class TelaMovimentacao extends JFrame {

    private JPanel contentPane;
    private JTextField tfValor;
    private JTextField tfDescricao;
    private JTextField tfData;
    private JComboBox<String> cbTipo;

    public TelaMovimentacao() {
        setTitle("Tela Movimentação");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // CORRIGIDO
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbValor = new JLabel("Valor");
        lbValor.setBounds(50, 50, 80, 20);
        contentPane.add(lbValor);

        tfValor = new JTextField();
        tfValor.setBounds(150, 50, 120, 20);
        contentPane.add(tfValor);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(50, 90, 80, 20);
        contentPane.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(150, 90, 200, 20);
        contentPane.add(tfDescricao);

        JLabel lbData = new JLabel("Data");
        lbData.setBounds(50, 130, 80, 20);
        contentPane.add(lbData);

        tfData = new JTextField();
        tfData.setBounds(150, 130, 120, 20);
        contentPane.add(tfData);

        JLabel lbTipo = new JLabel("Tipo");
        lbTipo.setBounds(50, 170, 80, 20);
        contentPane.add(lbTipo);

        cbTipo = new JComboBox<>();
        cbTipo.setModel(new DefaultComboBoxModel<>(new String[] { "Receita", "Despesa" })); // PADRONIZADO
        cbTipo.setBounds(150, 170, 120, 22);
        contentPane.add(cbTipo);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(150, 230, 100, 25);
        contentPane.add(btnSalvar);

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String tipo = cbTipo.getSelectedItem().toString();
                    String descricao = tfDescricao.getText();
                    double valor = Double.parseDouble(tfValor.getText());
                    String data = tfData.getText();

                    AtributosMovimentacao mov = new AtributosMovimentacao(tipo, descricao, valor, data);
                    ArmazenamentoMovimentacao.adicionar(mov);

                    JOptionPane.showMessageDialog(null, "Movimentação salva!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar!");
                }
            }
        });
    }
}
