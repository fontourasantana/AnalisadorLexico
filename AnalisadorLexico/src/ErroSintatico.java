
@SuppressWarnings("serial")
public class ErroSintatico extends RuntimeException implements Constantes {
	private Token tokenEncontrado;
	private Token[] tokensEsperados;
	private int _linha, _coluna, _tokenSize;

	public ErroSintatico(Token _tokenEncontrado, Token[] _tokensEsperados, int _linha, int _coluna, int _tokenSize) {
		this.tokenEncontrado = _tokenEncontrado;
		this.tokensEsperados = _tokensEsperados;
		this._linha = _linha;
		this._coluna = _coluna;
		this._tokenSize = _tokenSize;
	}
	public ErroSintatico(Token _tokenEncontrado, Token _tokenEsperado, int _linha, int _coluna, int _tokenSize) {
		this.tokenEncontrado = _tokenEncontrado;
		this.tokensEsperados = new Token[1];
		tokensEsperados[0] = _tokenEsperado;
		this._linha = _linha;
		this._coluna = _coluna;
		this._tokenSize = _tokenSize;
	}
	public String toString() {
		String listaDeTokensEsperados = "";
		for(int i=0; i<this.tokensEsperados.length; i++)
			listaDeTokensEsperados += this.tokensEsperados[i] + " ";
		return "Token encontrado: "+this.tokenEncontrado+" (Linha: " + this._linha + ", Coluna: " + (this._coluna-_tokenSize) + ")"+
		       "\nEra(m) esperado(s): "+listaDeTokensEsperados;
	}
}
