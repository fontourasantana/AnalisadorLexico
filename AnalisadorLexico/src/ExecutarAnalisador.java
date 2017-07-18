import java.awt.Color;

public class ExecutarAnalisador {
	private static MyAnalisadorSintatico parser;
	private static Interface gui;
	
	public static void main(String[] args) {
		gui = new Interface();
		gui.setVisible(true);
	}

	/**
	 * Responsavel por pegar o StringBuffer passado pela interface para ser analisado.
	 * @param entrada Consiste no arquivo ou codigo digitado pelo usuario no qual será analisado.
	 */
	public static void analisar(StringBuffer entrada) {
		try {
			parser = new MyAnalisadorSintatico(entrada);
			parser.inicio();
			gui.setStatus("ANALISE REALIZADA COM SUCESSO.", Color.GREEN);
		}
		catch(ErroLexico e) {
			gui.setStatus("(ERRO LÉXICO)", Color.RED);
			gui.emitirAlerta("Erro Lexico", e.toString());
		}
		catch(ErroSintatico e) {
			gui.setStatus("(ERRO SINTÁTICO)", Color.RED);
			gui.emitirAlerta("Erro Sintatico", e.toString());
		}
		catch(RuntimeException e) {
			gui.emitirAlerta("Erro", e.toString());
		}
	}
}
