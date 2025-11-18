import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TelaMovimentacao extends JFrame {

    private JPanel contentPane;
    private JTextField tfValor;
    private JTextField tfDescricao;
    private JTextField tfData;
    private JComboBox<String> cbTipo;

    // Formata a data
    //final é pra não ser mais possível modificar o metodo depois
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
        cbTipo.setModel(new DefaultComboBoxModel<>(new String[]{"Receita", "Despesa"})); // TIPOS VÁLIDOS
        cbTipo.setBounds(150, 170, 120, 22);
        contentPane.add(cbTipo);

   

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(150, 230, 100, 25);
        contentPane.add(btnSalvar);

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarMovimentacao(); // Chama função separada
            }
        });
    }

 
    //                MÉTODO PRINCIPAL DE SALVAR
    
    
    
    
    
    
    

    private void salvarMovimentacao() {
        try {
            // extrai texto. 'getSelectedItem()' transforma em objeto, por isso o metodo 'toString'.
            String tipo = cbTipo.getSelectedItem().toString(); // garante que será String
            
            // criação de Objeto para guardar no ArrayList
            String descricao = tfDescricao.getText();

            // VALIDAÇÃO DE VALOR NUMÉRICO. parseDouble converte String em double
            double valor = Double.parseDouble(tfValor.getText().replace(",", "."));

            // VALIDAÇÃO DE DATA REAL LocalDate verifica a data com base no calendario ISO
            LocalDate data = validarData(tfData.getText().trim());

            // Criando o objeto final para armazenamento
            AtributosMovimentacao mov = new AtributosMovimentacao(
                    tipo,
                    descricao,
                    valor,
                    data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            );

            ArmazenamentoMovimentacao.adicionar(mov);

            JOptionPane.showMessageDialog(null, "Movimentação salva!");

            limparCampos(); // limpa os campos após salvar

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Valor inválido! Digite apenas números.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(null, "Data inválida! Use o formato dd/MM/yyyy e uma data existente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        }
    }

   
    //                    MÉTODO DE VALIDAR DATA
   
    // Aqui no LocalDate verifica se as datas estão certas automaticamente:
    // EX:
    //formato errado (ex: 12/aa/2025)
    // datas impossíveis (ex: 32/12/2025, 29/02/2025)
    private LocalDate validarData(String texto) throws DateTimeParseException {
        return LocalDate.parse(texto, formatter);
    }

    
    //                   LIMPAR CAMPOS APÓS SALVAR
 
    private void limparCampos() {
        tfValor.setText("");
        tfDescricao.setText("");
        tfData.setText("");
        cbTipo.setSelectedIndex(0); // volta para "Receita"
    }
    


    
    
}
