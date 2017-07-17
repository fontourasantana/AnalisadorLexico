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
	 * Método qual é iniciado o programa.
	 */
	public void inicio() {
		listaComandos(); 
		reconhece(Token.EOF);
	}
	
	/**
	 * Responsável por gerenciar para onde sera dirigido o fluxo do programa de acordo com os tokens de entrada,
	 * sendo eles (WHILE, DO, FOR, SWITCH, IF, VAR, AP, OPUNITARIO, INT e REAL), ele pode executar apenas um comando ou uma
	 * lista de comandos.
	 * @throws ErroSintatico É lançado um objeto avisando caso nenhum dos tokens seja encontrado.
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
	 * Responsável por avaliar de acordo com token lido ele executa o método correto para saber se a estruturação
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
	 * Responsável por detectar se o bloco da estrutura possui '{' (CHAVES) ou não.
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
	 * Responsável pela parte fundamental da analise sintatica, é nele que é avaliado todas as expressões de entrada
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
	 * Responsável por avaliar todas as expressões envolvendo variaveis.\n
	 * Trabalha em conjunto com os metodos R1() e R2().
	 */
	public void expressaoVAR(){
		reconhece(Token.VAR);
		R1();
		R2();
	}
	
	/**
	 * Responsável por detectar se à operador unitario na expressão ou alguma atribuição, isso é necessario pelo fato
	 * de que o unico método que chama essa função é o expressaoVAR().
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
	 * Responsável por avaliar se a expressão possui uma operação ou não, com isso fazendo a recursão para gerar todas
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
	 * Responsável por identificar se o numero é um INT ou REAL, caso não for nenhum dos dois é lançado um erro.
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
	 * Responsável por avaliar se o bloco da estrutura WHILE está correta.
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
	 * Responsável por avaliar se o bloco da estrutura DOWHILE está correta.
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
	 * Responsável por avaliar se o bloco da estrutura FOR está correta.
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
	 * Responsável por avaliar se o bloco da estrutura SWITCH está correta.
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
	 * Utilizado em conjunto com metodo _switch(), e é responsavel por avaliar se o bloco da estrutura CASE está correta.
	 */
	public void _case(){
		reconhece(Token.CASE);
		expressao();
		reconhece(Token.DOISPT);
		bloco();
		R3();
	}
	
	/**
	 * Chamado apenas pelo metodo _case(), ele é responsavel para detectar se à mais estruturas de CASES na entrada.
	 */
	public void R3(){
		if(proxTokenIs(Token.CASE))
			_case();
	}
	
	/////////////////////// BLOCO TRATANDO IF
	/**
	 * Responsável por avaliar se o bloco da estrutura IF está correta.
	 */
	public void _if(){
		reconhece(Token.IF);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		bloco();
	}
}
