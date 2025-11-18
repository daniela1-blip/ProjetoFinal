
public class AtributosMovimentacao {
	private String tipo; //receita ou despesa do usuario
	private String descricao; //coisas como conta de luz
	private double valor; //valores a pagar
	private String data; //datas a pagar as contas
	
	public AtributosMovimentacao (String tipo, String descricao, double valor, String data) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
	}

	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

		
}
