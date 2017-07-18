import java.io.FileReader;
import java.io.IOException;
 
public class AnalisadorLexico extends Analisador {
	protected char proxCaractere;  // caractere dispon�vel no cabe�ote de leitura
	private int linha = 1;  // linha atual do arquivo fonte
	private int coluna = 0; // coluna atual do arquivo fonte
	private int tokenLenght = 0; // sera utilizado para mapear o erro sintatico posteriormente
	protected StringBuffer entrada = new StringBuffer(); // armazena o conte�do do arquivo
	protected int posicao = 0; // posi��o do caractere a ser lido na entrada  
	protected Token tokenReconhecido, lastToken; // �ltimo token lido
	
	// transfere o arquivo para o buffer �entrada�
	public AnalisadorLexico(StringBuffer entrada){
		super(entrada);
		this.entrada = entrada;
		leProxCaractere();
	}
	
	public AnalisadorLexico(String _nomeArquivoEntrada) {
		super(_nomeArquivoEntrada);
		try {
			FileReader file = new FileReader(_nomeArquivoEntrada);
			int c;
			while((c = file.read()) != -1) {
				this.entrada.append((char)c);
			}
			file.close();
			leProxCaractere();
		}
		catch (IOException e) {
			throw new RuntimeException("Erro de leitura no arquivo " + _nomeArquivoEntrada);
		}
	}
	
	// l� o pr�ximo caractere do buffer. Se fim, retorna EOF
	// avan�a o ponteiro de leitura 1 posi��o
	public void setToken(Token t){
		this.lastToken = tokenReconhecido;
		this.tokenReconhecido = t;
	}
	
	public void leProxCaractere() {
		try {
			if(this.proxCaractere == '\n'){
				this.linha++;
				this.coluna=0;
			}
			this.proxCaractere = this.entrada.charAt(this.posicao++);
			this.coluna++;
		}
		catch(IndexOutOfBoundsException e) {
			this.proxCaractere = EOF;
		}
	}
	
	public void setTokenLenght(){
		this.tokenLenght++;
	}
	
	public void setTokenLenght(int valor){
		this.tokenLenght=valor;
	}
	
	public int getTokenLenght(){
		return this.tokenLenght;
	}
	
	public int getLinha(){
		return this.linha;
	}
	
	public int getColuna(){
		return this.coluna;
	}
	
	// verifica se o pr�ximo caractere � um dos que est�o em �s�
   // N O avan�a o ponteiro de leitura
	public boolean proxCaractereIs(String s) {
		if (s.indexOf(this.proxCaractere) != -1)
			return true;
		else
			return false;
	}

}
