import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.Font;

public class TelaMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuPrincipal frame = new TelaMenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaMenuPrincipal() {
		setTitle("Tela Sistema 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// BOTÃO RESUMO
		JButton btResumo = new JButton("Resumo financeiro");
		btResumo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					double totalReceitas = 0;
					double totalDespesas = 0;

					System.out.println("Movimentações salvas: " + ArmazenamentoMovimentacao.getMovimentacoes().size());

					for (AtributosMovimentacao mov : ArmazenamentoMovimentacao.getMovimentacoes()) {

					    if (mov.getTipo().equalsIgnoreCase("Receita")) {
					        totalReceitas += mov.getValor();

					    } else if (mov.getTipo().equalsIgnoreCase("Despesa")) {
					        totalDespesas += mov.getValor();
					    }
					}

					double saldo = totalReceitas - totalDespesas;

					JOptionPane.showMessageDialog(null,
					    "Resumo Financeiro:\n\n" +
					    "Total de Receitas: R$ " + String.format("%.2f", totalReceitas) + "\n" +
					    "Total de Despesas: R$ " + String.format("%.2f", totalDespesas) + "\n" +
					    "Saldo Final: R$ " + String.format("%.2f", saldo));
				double saldo1 = totalReceitas - totalDespesas;
			}
		});
		btResumo.setBounds(32, 278, 178, 34);
		contentPane.add(btResumo);

		// BOTÃO CADASTRAR MOVIMENTAÇÃO
		JButton btCadastroMovimentacao = new JButton("Cadastrar movimentação");
		btCadastroMovimentacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMovimentacao tela = new TelaMovimentacao();
				tela.setVisible(true);
			}
		});
		btCadastroMovimentacao.setBounds(461, 278, 178, 34);
		contentPane.add(btCadastroMovimentacao);

		// BOTÃO LISTAR MOVIMENTAÇÕES
		JButton btListar = new JButton("Listar movimentações");
		btListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListagem telaListagem = new TelaListagem();
				telaListagem.setVisible(true);
			}
		});
		btListar.setBounds(249, 278, 178, 34);
		contentPane.add(btListar);

		// LABEL
		JLabel lbBemVindo = new JLabel("Seja bem vindo");
		lbBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbBemVindo.setBounds(249, 57, 128, 40);
		contentPane.add(lbBemVindo);
		setResizable(false);
	}
}
