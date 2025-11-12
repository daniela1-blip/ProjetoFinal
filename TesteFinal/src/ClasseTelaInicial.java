
public class ClasseTelaInicial {

	
	private String usuario;
	private String senha;
	private String mensagem;
	
	
	public ClasseTelaInicial(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
		this.mensagem = "Bem vindo";
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
}
