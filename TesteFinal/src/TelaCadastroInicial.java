import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JTextField tfSenha;

	
	
	private ClasseTelaInicial usuario;
	private String user = "usuario123";
	private String senha = "1234";
	private String msg;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroInicial frame = new TelaCadastroInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroInicial() {
		setTitle("Tela inicial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 820, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(278, 254, 218, 31);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfSenha = new JTextField();
		tfSenha.setBounds(278, 323, 218, 31);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);
		
		JLabel lbUsuario = new JLabel("Usuário");
		lbUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbUsuario.setBounds(146, 255, 168, 14);
		contentPane.add(lbUsuario);
		
		JLabel lbSenha = new JLabel("Senha");
		lbSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSenha.setBounds(146, 324, 153, 14);
		contentPane.add(lbSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				usuario = new ClasseTelaInicial(tfUsuario.getText(), tfSenha.getText());
				
				if (usuario.getUsuario().equals(user) &&
						usuario.getSenha().equals(senha)) {
					JOptionPane.showMessageDialog(null, "Acesso Permitido"+ " " +usuario.getMensagem());
					TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal();
					telaMenuPrincipal.setVisible(true);
					
					
				}		
						else {
							JOptionPane.showMessageDialog(null, "Acesso Negado");
						}
						
							
			}
		});
		btnEntrar.setBounds(326, 386, 126, 41);
		contentPane.add(btnEntrar);
		
		JLabel lbSistema = new JLabel("Entre no sistema");
		lbSistema.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbSistema.setBounds(302, 92, 303, 31);
		contentPane.add(lbSistema);
		setResizable(false);

	}
}
