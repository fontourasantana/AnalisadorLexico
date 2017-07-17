
public class MyAnalisadorSintatico extends AnalisadorSintatico {
	
	private boolean flag;
	
	public MyAnalisadorSintatico(String _nomeArquivoEntrada) {
		super(_nomeArquivoEntrada);
	}
	public void inicio() {
		listaComandos(); 
		reconhece(Token.EOF);
	}
	public void listaComandos() {
		if(proxTokenIs(Token.EOF) || proxTokenIs(Token.FCH))
			;
		else if(proxTokenIs(Token.WHILE) || proxTokenIs(Token.DO) || proxTokenIs(Token.FOR) || proxTokenIs(Token.SWITCH) || proxTokenIs(Token.IF) || proxTokenIs(Token.AP) || proxTokenIs(Token.OPERADORUNIT) || proxTokenIs(Token.VAR) || proxTokenIs(Token.INT) || proxTokenIs(Token.REAL)) {
			comando();
			listaComandos();
		}
		else {
			Token[] tokensEsperados = {Token.WHILE, Token.DO, Token.FOR, Token.SWITCH, Token.IF, Token.VAR, Token.INT, Token.REAL, Token.AP, Token.EOF};
			throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
		}
	}
	
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
		else if(proxTokenIs(Token.VAR)){
			expressao();
			reconhece(Token.PTVIR);
			flag=false;
		}else
			reconhece(Token.PTVIR);
	}
	
	public void bloco(){
		if(proxTokenIs(Token.ACH)){
			reconhece(Token.ACH);
			listaComandos();
			reconhece(Token.FCH);
		}
		else
			comando();
		
	}
	
	public void expressao(){
		if(proxTokenIs(Token.AP)){
			reconhece(Token.AP);
			expressao();
			reconhece(Token.FP);
		}else if(proxTokenIs(Token.INT) || proxTokenIs(Token.REAL) || proxTokenIs(Token.POSITIVO) || proxTokenIs(Token.NEGATIVO)){
			numero();
			R2();
		}
		else if(proxTokenIs(Token.VAR)){
			reconhece(Token.VAR);
			atribuicaoSemVAR();
			R1();
		}
		else if(proxTokenIs(Token.OPERADORUNIT)){
			reconhece(Token.OPERADORUNIT);
			reconhece(Token.VAR);
			R2();
		}else{
			Token[] tokensEsperados = {Token.INT, Token.REAL, Token.VAR};
			throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
		}
	}
	
	public void R1(){
		if(proxTokenIs(Token.OPERADORUNIT)){
			reconhece(Token.OPERADORUNIT);
		}
		R2();
	}
	
	public void R2(){
		if(proxTokenIs(Token.OPERADORBIN) || proxTokenIs(Token.POSITIVO) || proxTokenIs(Token.NEGATIVO)){
			leProxToken();
			expressao();
		}
	}
	
	public void numero(){
		sinal();
		tipo();
	}
	
	public void sinal(){
		if(proxTokenIs(Token.POSITIVO))
			leProxToken();
		else if(proxTokenIs(Token.NEGATIVO))
			leProxToken();
	}
	
	public void tipo(){
		if(proxTokenIs(Token.INT))
			reconhece(Token.INT);
		else if(proxTokenIs(Token.REAL))
			reconhece(Token.REAL);
		else {
			Token[] tokensEsperados = {Token.INT, Token.REAL};
			throw new ErroSintatico(this.scanner.tokenReconhecido,tokensEsperados);
		}
	}
	
	/////////////////////// BLOCO TRATANDO WHILE
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
	public void _for(){
		reconhece(Token.FOR);
		reconhece(Token.AP);
		atribuicao();
		reconhece(Token.PTVIR);
		expressao();
		reconhece(Token.PTVIR);
		atribuicao();
		reconhece(Token.FP);
		bloco();
	}
	
	/////////////////////// BLOCO TRATANDO FOR
	public void _switch(){
		reconhece(Token.SWITCH);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		reconhece(Token.ACH);
		_case();
		reconhece(Token.FCH);
	}
	
	public void _case(){
		reconhece(Token.CASE);
		expressao();
		reconhece(Token.DOISPT);
		bloco();
		R3();
	}
	
	public void R3(){
		if(proxTokenIs(Token.CASE))
			_case();
	}
	
	/////////////////////// BLOCO TRATANDO FOR
	public void _if(){
		reconhece(Token.IF);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		bloco();
	}
	
	/////////////////////// BLOCO TRATANDO ATRIBUICAO
	public void atribuicaoSemVAR(){ // UTILIZADO EM EXPRESSAO PRA QUANDO A UM ATRIBUICAO
		if(proxTokenIs(Token.ATRIBUICAO) && !flag){
			reconhece(Token.ATRIBUICAO);
			this.flag=true;
			expressao();
		}else if(proxTokenIs(Token.ATRIBUICAO)){
			Token[] tokensEsperados = {Token.OPERADORBIN, Token.OPERADORUNIT, Token.PTVIR};
			throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados);
		}
	}
	
	public void atribuicao(){
		if(proxTokenIs(Token.VAR)){
			reconhece(Token.VAR);
			reconhece(Token.ATRIBUICAO);
			expressao();
		}else {
			Token[] tokensEsperados = {Token.VAR};
			throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados);
		}
	}
	/*
	 * public void inicio() {
		corpo(); 
		reconhece(Token.EOF);
	}
	public void corpo() {
		if(proxTokenIs(Token.IDENT)) {
			comandoAtribuicao();
			reconhece(Token.PTOVIRG);
			corpo();
		}
		else if(proxTokenIs(Token.EOF))
			;
		else {
			Token[] tokensEsperados = {Token.IDENT,Token.EOF};
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
	}*/
}
