import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.ArrayList;

public class TelaListagem extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabela;
    private DefaultTableModel modelo;
    private JLabel labelExtrato; // label para mostrar o extrato total

    public TelaListagem() {
        setResizable(false);
        setTitle("Listagem de Movimentações");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 450); 

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        // Label do extrato
        labelExtrato = new JLabel("Extrato: R$ 0.00");
        labelExtrato.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(labelExtrato, BorderLayout.NORTH); // adiciona o extrato no topo da tela

        // Configuração do modelo da tabela (editável)
        modelo = new DefaultTableModel(// DefaultTableModel é uma classe do Swing que guarda os dados da JTable.
        		/*@Override public boolean isCellEditable = criar uma classe anonima (classe sem nome que só funciona nesse trecho) e colocando no lugar de DefaultTableModel
        		Isso permite controlar quais células podem ser editadas.
        		Sem isso, todas seriam editáveis ou teria que criar uma classe separada só pra isso.
        		o DefaultTableModel torna as células editaveis por padrão
        	 	 */
        		
        		//return true permite a edição de todas as células, mas o código fica flexível caso eu queira mudar colunas editáveis no futuro.
        		
                new Object[][] {}, // object superclasse, engloba todos os tipos primitivos, entao usar pra agilizar o processo quando precisa guardar muitas coisas de diferentes tipos
                new String[] { "Tipo", "Descrição", "Valor", "Data" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { // garante que posso editar as celuluas e mudar no arraylist pelo TableModelListener
                return true; // permite edição
            }
        };

        // Configuração da tabela
        tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Renderer para colorir células de acordo com o tipo
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() { // class é uma classe anonima pra sobrescrever getTableCellRendererComponent e assim mudar o jeito que a celula é desenhada
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //component é uma superclasse pra todos os componentes do swing

                String tipo = (String) table.getValueAt(row, 0); // pega a coluna "Tipo"
                // se tipo for Receita pinta de verde
                if ("Receita".equalsIgnoreCase(tipo)) {
                    c.setForeground(Color.GREEN.darker());
                } 
                // se tipo for Despesa pinta de vermelho
                else if ("Despesa".equalsIgnoreCase(tipo)) {
                    c.setForeground(Color.RED);
                } 
                // se não for nenhum, deixa preto
                else {
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });

        // Listener para atualização dos dados do ArrayList quando editados na tabela
        modelo.addTableModelListener(e -> { // 'e' parametro '->' pra diminuir o codigo e nao ter que colocar new TableModelListener() { public void tableChanged() {} }
        	
            int linha = e.getFirstRow();
            int coluna = e.getColumn();
            //getFirstRow() e getColumn() dizem qual célula foi alterada.

            if (linha < 0 || coluna < 0) return;

            Object novoValor = modelo.getValueAt(linha, coluna); // getValueAt() pega o novo valor digitado
            AtributosMovimentacao mov = ArmazenamentoMovimentacao.getMovimentacoes().get(linha); // pega objeto do array original

            // switch para definir qual campo do objeto atualizar
            switch (coluna) {
                case 0: mov.setTipo(novoValor.toString()); break; // atualiza tipo
                case 1: mov.setDescricao(novoValor.toString()); break; // atualiza descricao
                case 2:
                    try {
                        mov.setValor(Double.parseDouble(novoValor.toString())); // atualiza valor
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Valor inválido! Insira um número."); // alerta se não for numero
                        atualizarTabela(); // volta o valor antigo
                    }
                    break;
                case 3: mov.setData(novoValor.toString()); break; // atualiza data
            }

            atualizarExtrato(); // atualiza extrato sempre que altera algum valor
            tabela.repaint(); // repinta a tabela para atualizar cores
        });

        // Botão para excluir linha selecionada
        JButton btnExcluir = new JButton("Excluir Selecionado");
        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                ArmazenamentoMovimentacao.remover(linhaSelecionada);
                atualizarTabela(); // atualiza a tabela
                atualizarExtrato(); // atualiza o extrato
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
            }
        });

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnExcluir);
        contentPane.add(painelBotoes, BorderLayout.SOUTH);

        // Carrega os dados na abertura
        atualizarTabela();
        atualizarExtrato(); // calcula extrato inicial
    }

    // Método que lê o ArrayList e joga os valores na tabela
    private void atualizarTabela() {
        modelo.setRowCount(0); // limpa tudo
        ArrayList<AtributosMovimentacao> lista = ArmazenamentoMovimentacao.getMovimentacoes(); // pega lista original

        for (AtributosMovimentacao mov : lista) {
            modelo.addRow(new Object[] { // adiciona cada linha na tabela
                    mov.getTipo(),
                    mov.getDescricao(),
                    mov.getValor(),
                    mov.getData()
            });
        }
    }

    // Método que calcula e atualiza o extrato
    private void atualizarExtrato() {
        double total = 0;
        for (AtributosMovimentacao mov : ArmazenamentoMovimentacao.getMovimentacoes()) {
            if ("Receita".equalsIgnoreCase(mov.getTipo())) {
                total += mov.getValor(); // soma receitas
            } else if ("Despesa".equalsIgnoreCase(mov.getTipo())) {
                total -= mov.getValor(); // subtrai despesas
            }
        }
        labelExtrato.setText("Extrato: R$ " + String.format("%.2f", total)); // atualiza label do extrato
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaListagem frame = new TelaListagem();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                /*invokeLater → garante que tudo rode na EDT (thread da interface).
 				-> {} → código que será executado na fila da EDT.
 				Cria a tela (TelaListagem) e mostra com setVisible(true).
				Try/catch protege contra erros durante a criação da janela.
				thread da interface: thread sao linhas que rodam por tras dos panos, esse metodo faz com que as alterações que acontecem muito nessa tela aconteçam sempre no mesmo thread
				evitando erros e bugs.
				
*/
                
                
            }
        });
    }
}
