import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaListagem extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaListagem() {
        setTitle("Listagem de Movimentações");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        // 🔹 Configuração da tabela
        modelo = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Tipo", "Descrição", "Valor", "Data" }
        );
        tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 🔹 Botão para atualizar os dados
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarTabela();
            }
        });

        // 🔹 Botão para excluir linha
        JButton btnExcluir = new JButton("Excluir Selecionado");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();
                if (linhaSelecionada >= 0) {
                    ArmazenamentoMovimentacao.remover(linhaSelecionada);
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
                }
            }
        });

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        contentPane.add(painelBotoes, BorderLayout.SOUTH);

        // 🔹 Carrega a tabela na abertura
        atualizarTabela();
    }

    // 🧩 Método que lê o ArrayList e joga os valores na tabela
    private void atualizarTabela() {
        modelo.setRowCount(0); // limpa tudo
        ArrayList<AtributosMovimentacao> lista = ArmazenamentoMovimentacao.getMovimentacoes();

        for (AtributosMovimentacao mov : lista) {
            modelo.addRow(new Object[] {
                mov.getTipo(),
                mov.getDescricao(),
                mov.getValor(),
                mov.getData()
            });
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaListagem frame = new TelaListagem();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}