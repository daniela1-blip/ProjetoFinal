

import java.util.ArrayList;	
	
	
	
public class ArmazenamentoMovimentacao{
	private static ArrayList<AtributosMovimentacao> movimentacoes1 = new ArrayList<>(); //um array
	
	
	public static void adicionar (AtributosMovimentacao m) { //serve pra poder guardar valores dentro do array movimentacoes1
		movimentacoes1.add(m);		
	}
	
	public static ArrayList<AtributosMovimentacao>getMovimentacoes(){ //serve como ponte pra poder usar os valores do array em varias classes
		return movimentacoes1;
	}
	
	public static void remover (int valor) { // metodo pra poder apagar valores do array
		movimentacoes1.remove(valor);
	}
	
	
	
	

}
	
	
	
	
	
	
	
	
	
	
	
	
	

