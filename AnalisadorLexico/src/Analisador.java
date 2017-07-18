
public abstract class Analisador implements Constantes {
	protected String nomeArquivoEntrada;
	protected StringBuffer entrada = new StringBuffer();
	
	public Analisador(String _nomeArquivoEntrada) {
		this.nomeArquivoEntrada = _nomeArquivoEntrada;
	}
	
	public Analisador(StringBuffer entrada){
		this.entrada = entrada;
	}
	
	public Analisador() {
		this.nomeArquivoEntrada = NOME_DEFAULT_ARQUIVO_ENTRADA;
	}
}