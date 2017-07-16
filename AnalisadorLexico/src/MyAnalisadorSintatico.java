
public class MyAnalisadorSintatico extends AnalisadorSintatico {
	
	public MyAnalisadorSintatico(String _nomeArquivoEntrada) {
		super(_nomeArquivoEntrada);
	}
	public void inicio() {
		corpo(); 
		reconhece(Token.EOF);
	}
	public void corpo() {
		if(proxTokenIs(Token.VAR)) {
			comandoAtribuicao();
			reconhece(Token.PTVIR);
			corpo();
		}
		else if(proxTokenIs(Token.EOF))
			;
		else {
			Token[] tokensEsperados = {Token.VAR,Token.EOF};
			throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
		}
	}
	public void comandoAtribuicao() {
		reconhece(Token.VAR);
		reconhece(Token.ATRIBUICAO);
		exp();
	}
	public void exp() {
		if(proxTokenIs(Token.INT) || proxTokenIs(Token.REAL)) 
			leProxToken();
		else if(proxTokenIs(Token.VAR)) 
			leProxToken();
		else {
			Token[] tokensEsperados = {Token.INT, Token.REAL,Token.VAR};
			throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
		}
	}
}
