
@SuppressWarnings("serial")
public class ErroLexico extends RuntimeException {
	private char caractereEncontrado;
	private String caracteresEsperados;
	private int _linha, _coluna;
	
	public ErroLexico(char _caracterEncontrado, String _caracteresEsperados, int _linha, int _coluna) {
		this.caractereEncontrado = _caracterEncontrado;
		this.caracteresEsperados = _caracteresEsperados;
		this._linha = _linha;
		this._coluna = _coluna;
	}
	
	public String toString() {
		return "Caractere encontrado: "+ ((char)this.caractereEncontrado) + " (Linha: " + this._linha + ", Coluna: " + this._coluna + ")"+
		       "\nEra(m) esperado(s): "+this.caracteresEsperados;
	}
}