/**
 * 
 * @author MATEUS FONTOURA
 *
 */
public class MyAnalisadorSintatico extends AnalisadorSintatico {
	private boolean _atrib;
	
	/**
	 * @param _nomeArquivoEntrada Nome do arquivo utilizado para fazer a analise lexica/sintatica.
	 */
	public MyAnalisadorSintatico(String _nomeArquivoEntrada) {
		super(_nomeArquivoEntrada);
	}
	
	/**
	 * M�todo qual � iniciado o programa.
	 */
	public void inicio() {
		listaComandos(); 
		reconhece(Token.EOF);
	}
	
	/**
	 * Respons�vel por gerenciar para onde sera dirigido o fluxo do programa de acordo com os tokens de entrada,
	 * sendo eles (WHILE, DO, FOR, SWITCH, IF, VAR, AP, OPUNITARIO, INT e REAL), ele pode executar apenas um comando ou uma
	 * lista de comandos.
	 * @throws ErroSintatico � lan�ado um objeto avisando caso nenhum dos tokens seja encontrado.
	 */
	public void listaComandos() {
		if(proxTokenIs(Token.EOF) || proxTokenIs(Token.FCH));
		else if(proxTokenIs(Token.WHILE) || proxTokenIs(Token.DO) || proxTokenIs(Token.FOR) || proxTokenIs(Token.SWITCH) || proxTokenIs(Token.IF) || proxTokenIs(Token.VAR) || proxTokenIs(Token.AP) || proxTokenIs(Token.OPUNITARIO) || proxTokenIs(Token.INT) || proxTokenIs(Token.REAL)) {
			comando();
			listaComandos();
		}
		else {
			Token[] tokensEsperados = {Token.WHILE, Token.DO, Token.FOR, Token.SWITCH, Token.IF, Token.VAR, Token.INT, Token.REAL, Token.AP, Token.EOF};
			throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
		}
	}
	
	/**
	 * Respons�vel por avaliar de acordo com token lido ele executa o m�todo correto para saber se a estrutura��o
	 * esta adequada de acordo com o token lido.
	 */
	public void comando(){
		if(proxTokenIs(Token.WHILE))
			_while();
		else if(proxTokenIs(Token.DO))
			dowhile();
		else if(proxTokenIs(Token.FOR))
			_for();
		else if(proxTokenIs(Token.SWITCH))
			_switch();
		else if(proxTokenIs(Token.IF))
			_if();
		else if(proxTokenIs(Token.VAR) || proxTokenIs(Token.AP) || proxTokenIs(Token.INT) || proxTokenIs(Token.REAL)){
			expressao();
			if(proxTokenIs(Token.PTVIR) || proxTokenIs(Token.OPERADOR))
				leProxToken();
			else {
				Token[] tokensEsperados = {Token.OPERADOR, Token.PTVIR};
				throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados);
			}
			_atrib=false;
		}else{
			reconhece(Token.PTVIR);
		}
	}
	
	/**
	 * Respons�vel por detectar se o bloco da estrutura possui '{' (CHAVES) ou n�o.
	 */
	public void bloco(){
		if(proxTokenIs(Token.ACH)){
			reconhece(Token.ACH);
			listaComandos();
			reconhece(Token.FCH);
		}
		else
			comando();
		
	}
	
	/**
	 * Respons�vel pela parte fundamental da analise sintatica, � nele que � avaliado todas as express�es de entrada
	 * e avalia se esta tudo de acordo com a gramatica desenvolvida.
	 */
	public void expressao(){
		if(proxTokenIs(Token.AP)){
			reconhece(Token.AP);
			expressao();
			reconhece(Token.FP);
		}
		else if(proxTokenIs(Token.INT) || proxTokenIs(Token.REAL)){
			numero();
		}
		else if(proxTokenIs(Token.VAR)){
			expressaoVAR();
		}
		else if(proxTokenIs(Token.MAIS) || proxTokenIs(Token.MENOS)){
				leProxToken();
				if(proxTokenIs(Token.VAR)){
				expressaoVAR();
				}
				else if(proxTokenIs(Token.INT) || proxTokenIs(Token.REAL))
					numero();
				else {
					Token[] tokensEsperados = {Token.INT, Token.REAL, Token.VAR};
					throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
				}
				R2();
		}
		else if(proxTokenIs(Token.OPUNITARIO)){
			reconhece(Token.OPUNITARIO);
			reconhece(Token.VAR);
			R2();
		} else {
			Token[] tokensEsperados = {Token.INT, Token.REAL, Token.VAR};
			throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
		}
	}
	
	/**
	 * Respons�vel por avaliar todas as express�es envolvendo variaveis.\n
	 * Trabalha em conjunto com os metodos R1() e R2().
	 */
	public void expressaoVAR(){
		reconhece(Token.VAR);
		R1();
		R2();
	}
	
	/**
	 * Respons�vel por detectar se � operador unitario na express�o ou alguma atribui��o, isso � necessario pelo fato
	 * de que o unico m�todo que chama essa fun��o � o expressaoVAR().
	 */
	public void R1(){
		if(proxTokenIs(Token.OPUNITARIO)){
			reconhece(Token.OPUNITARIO);
		}
		else if(proxTokenIs(Token.ATRIBUICAO)){
			if(!_atrib){
				reconhece(Token.ATRIBUICAO);
				_atrib=true;
				expressao();
			}else {
				Token[] tokensEsperados = {Token.OPERADOR, Token.PTVIR};
				throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados);
			}
		}
	}
	
	/**
	 * Respons�vel por avaliar se a express�o possui uma opera��o ou n�o, com isso fazendo a recurs�o para gerar todas
	 * as possibilidades permitidas pela Gramatica.
	 */
	public void R2(){
		if(proxTokenIs(Token.OPERADOR) || proxTokenIs(Token.MAIS) || proxTokenIs(Token.MENOS)){
			leProxToken();
			expressao();
			R2();
		}
	}
	
	/**
	 * Respons�vel por identificar se o numero � um INT ou REAL, caso n�o for nenhum dos dois � lan�ado um erro.
	 * @throws ErroSintatico Envia para a main do analisador um objeto identificando que houve um erro durante a analise.
	 */
	public void numero(){
		if(proxTokenIs(Token.INT))
			reconhece(Token.INT);
		else if(proxTokenIs(Token.REAL))
			reconhece(Token.REAL);
		else {
			Token[] tokensEsperados = {Token.INT, Token.REAL};
			throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
		}
		R2();
	}
	
	/////////////////////// BLOCO TRATANDO WHILE
	/**
	 * Respons�vel por avaliar se o bloco da estrutura WHILE est� correta.
	 */
	public void _while(){
		reconhece(Token.WHILE);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		if(proxTokenIs(Token.PTVIR)){
			reconhece(Token.PTVIR);
		}else
			bloco();
	}
	
	/////////////////////// BLOCO TRATANDO DOWHILE
	/**
	 * Respons�vel por avaliar se o bloco da estrutura DOWHILE est� correta.
	 */
	public void dowhile(){
		reconhece(Token.DO);
		bloco();
		reconhece(Token.WHILE);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		reconhece(Token.PTVIR);
	}
	
	/////////////////////// BLOCO TRATANDO FOR
	/**
	 * Respons�vel por avaliar se o bloco da estrutura FOR est� correta.
	 */
	public void _for(){
		reconhece(Token.FOR);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.PTVIR);
		_atrib=false;
		expressao();
		reconhece(Token.PTVIR);
		_atrib=false;
		expressao();
		_atrib=false;
		reconhece(Token.FP);
		bloco();
	}
	
	/////////////////////// BLOCO TRATANDO SWITCH
	/**
	 * Respons�vel por avaliar se o bloco da estrutura SWITCH est� correta.
	 */
	public void _switch(){
		reconhece(Token.SWITCH);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		reconhece(Token.ACH);
		_case();
		reconhece(Token.FCH);
	}
	
	/**
	 * Utilizado em conjunto com metodo _switch(), e � responsavel por avaliar se o bloco da estrutura CASE est� correta.
	 */
	public void _case(){
		reconhece(Token.CASE);
		expressao();
		reconhece(Token.DOISPT);
		bloco();
		R3();
	}
	
	/**
	 * Chamado apenas pelo metodo _case(), ele � responsavel para detectar se � mais estruturas de CASES na entrada.
	 */
	public void R3(){
		if(proxTokenIs(Token.CASE))
			_case();
	}
	
	/////////////////////// BLOCO TRATANDO IF
	/**
	 * Respons�vel por avaliar se o bloco da estrutura IF est� correta.
	 */
	public void _if(){
		reconhece(Token.IF);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		bloco();
	}
}
