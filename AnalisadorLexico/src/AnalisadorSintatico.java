
public class AnalisadorSintatico extends Analisador implements Constantes {

	protected MyAnalisadorLexico scanner;
	
	public AnalisadorSintatico(String _nomeArquivoEntrada) {
		this.scanner = new MyAnalisadorLexico(_nomeArquivoEntrada);
		this.leProxToken();	// l� o primeiro token e o coloca no campo tokenReconhecido
	}
	public AnalisadorSintatico() {
		super();
	}
	// executa 1 vez a m�quina de Moore
		public void leProxToken() {
			this.scanner.q0();
		}

		// verifica se o pr�ximo token � t
		// avan�a o ponteiro para o pr�ximo token
		public void reconhece(Token t) {
			if(t == this.scanner.tokenReconhecido) 
				this.leProxToken();
			else
				throw new ErroSintatico(this.scanner.tokenReconhecido, t);
		}

		// verifica se o pr�ximo token � t
	 // N O avan�a o ponteiro de leitura
		public boolean proxTokenIs(Token t) {
			if(t == this.scanner.tokenReconhecido) 
				return true;
			else
				return false;
		}
	}
