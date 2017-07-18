import java.io.IOException;
 
@SuppressWarnings("unused")
public class MyAnalisadorLexico extends AnalisadorLexico {
	/**
	 * @param _nomeArquivoEntrada Nome do arquivo de entrada que será analisado.
	 */
	public MyAnalisadorLexico(String _nomeArquivoEntrada) {
		super(_nomeArquivoEntrada);
	}
	
	/**
	 * @param entrada A entrada montada em um StringBuffer no qual será analisado.
	 */
	public MyAnalisadorLexico(StringBuffer entrada){
		super(entrada);
	}
	
	/**
	 * Responsável por detectar qual o caracter que foi lido e de acordo isso chamar o método correto, caso o caracter lido
	 * nao seja um dos possivels da gramatica é lançado um erro.
	 * @throws ErroLexico Erro lançado para avisar que foi lido um caracter não esperado.
	 */
	public void q0() {
		setTokenLenght(0);
		if(this.proxCaractereIs(VAZIOS)) {  
			leProxCaractere();
			q0();
		}
		else if(this.proxCaractere == PTVIR) {
			leProxCaractere();
			q3();
		}
		else if(this.proxCaractere == AP) {
			leProxCaractere();
			q4();
		}
		else if(this.proxCaractere == FP) {
			leProxCaractere();
			q5();
		}
		else if(this.proxCaractere == ACH) {
			leProxCaractere();
			q6();
		}
		else if(this.proxCaractere == FCH) {
			leProxCaractere();
			q7();
		}
		else if(this.proxCaractere == PONTO) {
			leProxCaractere();
			if(this.proxCaractereIs(DIGITOS))
				numeroReal();
			else
				throw new ErroLexico(this.proxCaractere, DIGITOS, this.getLinha(), this.getColuna());
		}
		else if(this.proxCaractere == DOISPT) {
			leProxCaractere();
			q8();
		}
		else if(this.proxCaractere == ATRIBUICAO) {
			leProxCaractere();
			q9();
		}
		else if(this.proxCaractereIs(LETRAS)) {
			palavra();
		}
		else if(this.proxCaractereIs(DIGITOS)) {
			leProxCaractere();
			numero();
		}
		else if(this.proxCaractereIs(OPERADORES+"+-!")) {
			operadores();
		}
		else if(this.proxCaractere == EOF) {
			leProxCaractere();
			q35();
		}
		else {
			throw new ErroLexico(this.proxCaractere, DIGITOS+LETRAS+OPERADORES+ATRIBUICAO+AP+FP+ACH+FCH+PTVIR+PONTO+DOISPT, this.getLinha(), this.getColuna());
		}
	}
	
	/**
	 * Responsável por detectar que o token lido foi um ';'.
	 */
	public void q3() {
		setToken(Token.PTVIR);
		setTokenLenght();
	}
	
	/**
	 * Responsável por detectar que o token lido foi um '('.
	 */
	public void q4() {
		setToken(Token.AP);
		setTokenLenght();
	}
	
	/**
	 * Responsável por detectar que o token lido foi um ')'.
	 */
	public void q5() {
		setToken(Token.FP);
		setTokenLenght();
	}
	
	/**
	 * Responsável por detectar que o token lido foi um '{'.
	 */
	public void q6() {
		setToken(Token.ACH);
		setTokenLenght();
	}
	
	/**
	 * Responsável por detectar que o token lido foi um '}'.
	 */
	public void q7() {
		setToken(Token.FCH);
		setTokenLenght();
	}
	
	/**
	 * Responsável por detectar que o token lido foi um ':'.
	 */
	public void q8() {
		setToken(Token.DOISPT);
		setTokenLenght();
	}
	
	/**
	 * Responsável por detectar que o token lido foi um '='.
	 */
	public void q9() {
		setToken(Token.ATRIBUICAO);
		setTokenLenght();
		if(this.proxCaractere == ATRIBUICAO){
			setToken(Token.OPERADOR);
			setTokenLenght();
			leProxCaractere();
		}
	}
	
	public void q10() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'f'){
			leProxCaractere();
			q11();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	/**
	 * Responsável por detectar que o token lido foi IF.
	 */
	public void q11() {
		setToken(Token.IF);
		setTokenLenght();
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q12() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'h'){
			leProxCaractere();
			q13();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q13() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'i'){
			leProxCaractere();
			q14();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q14() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'l'){
			leProxCaractere();
			q15();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q15() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'e'){
			leProxCaractere();
			q16();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	/**
	 * Responsável por detectar que o token lido foi WHILE.
	 */
	public void q16() {
		setToken(Token.WHILE);
		setTokenLenght();
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q17() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'o'){
			leProxCaractere();
			q18();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	/**
	 * Responsável por detectar que o token lido foi DO.
	 */
	public void q18() {
		setToken(Token.DO);
		setTokenLenght();
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q19() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'o'){
			leProxCaractere();
			q20();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q20() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'r'){
			leProxCaractere();
			q21();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	/**
	 * Responsável por detectar que o token lido foi FOR.
	 */
	public void q21() {
		setToken(Token.FOR);
		setTokenLenght();
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q22() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'w'){
			leProxCaractere();
			q23();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q23() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'i'){
			leProxCaractere();
			q24();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q24() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 't'){
			leProxCaractere();
			q25();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q25() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'c'){
			leProxCaractere();
			q26();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q26() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'h'){
			leProxCaractere();
			q27();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	/**
	 * Responsável por detectar que o token lido foi SWITCH.
	 */
	public void q27() {
		setToken(Token.SWITCH);
		setTokenLenght();
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q28() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'a'){
			leProxCaractere();
			q29();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q29() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 's'){
			leProxCaractere();
			q30();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q30() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'e'){
			leProxCaractere();
			q31();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	/**
	 * Responsável por detectar que o token lido foi CASE.
	 */
	public void q31() {
		setToken(Token.CASE);
		setTokenLenght();
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q32() {
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractereIs(LETRAS+DIGITOS)){
			leProxCaractere();
			q32();
		}
	}
	
	/**
	 * Responsável por detectar que o token lido foi EOF.
	 */
	public void q35() {
		setToken(Token.EOF);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void palavra(){
		setToken(Token.VAR);
		setTokenLenght();
		if(this.proxCaractere == 'i'){
			leProxCaractere();
			q10();
		}
		else if(this.proxCaractere == 'w'){
			leProxCaractere();
			q12();
		}
		else if(this.proxCaractere == 'd'){
			leProxCaractere();
			q17();
		}
		else if(this.proxCaractere == 'f'){
			leProxCaractere();
			q19();
		}
		else if(this.proxCaractere == 'c'){
			leProxCaractere();
			q28();
		}
		else if(this.proxCaractere == 's'){
			leProxCaractere();
			q22();
		}
		else if(this.proxCaractereIs(LETRAS+DIGITOS)){
			leProxCaractere();
			q32();
		}
	}
	
	/**
	 * Responsável por detectar que o token lido foi INT.
	 */
	public void numero(){
		setToken(Token.INT);
		setTokenLenght();
		if(this.proxCaractere == PONTO){
			leProxCaractere();
			numeroReal();
		}else if(this.proxCaractereIs(DIGITOS)){
			leProxCaractere();
			numero();
		}
	}
	
	/**
	 * Responsável por detectar que o token lido foi REAL.
	 */
	public void numeroReal(){
		setToken(Token.REAL);
		setTokenLenght();
		if(this.proxCaractereIs(DIGITOS)) {
			leProxCaractere();
			numeroReal();
		}
	}
	
	///////////////////////// OPERADORES
	/**
	 * Responsável por examinar qual operador esta sendo lido e de acordo com isso encaminhar para o metodo para melhor ser
	 * tratado.
	 */
	public void operadores(){
		if(this.proxCaractere == '+'){
			leProxCaractere();
			operadorMais();
		}
		else if(this.proxCaractere == '-'){
			leProxCaractere();
			operadorMenos();
		}
		else if(this.proxCaractere == '!'){
			leProxCaractere();
			operadorExclamacao();
		}
		else if(this.proxCaractereIs("*/%^")){
			leProxCaractere();
			conjuntoAtribuicao();
		}
		else if(this.proxCaractere == '&'){
			leProxCaractere();
			operadorRelacionalE();
		}
		else if(this.proxCaractere == '|'){
			leProxCaractere();
			operadorRelacionalOU();
		}
		else if(this.proxCaractere == '<'){
			leProxCaractere();
			operadorRelacionalMenor();
		}
		else if(this.proxCaractere == '>'){
			leProxCaractere();
			operadorRelacionalMaior();
		}
	}
	
	/**
	 * Responsável por avaliar quando dado uma entrada igual '+' de acordo com o proximo caracter lido sera classificado como um
	 * token MAIS (caso o proximo caracter for algo diferente de '+' ou '='), OPUNITARIO (caso o proximo caracter for '+')
	 * ou ATRIBUICAO (caso o proximo caracter for '=').
	 */
	public void operadorMais(){
		setToken(Token.MAIS);
		setTokenLenght();
		if(this.proxCaractere == '+'){
			setToken(Token.OPUNITARIO);
			setTokenLenght();
			leProxCaractere();
		}
		else if(this.proxCaractere == ATRIBUICAO){
			setToken(Token.ATRIBUICAO);
			setTokenLenght();
			leProxCaractere();
		}
	}
	
	/**
	 * Responsável por avaliar quando dado uma entrada igual '-' de acordo com o proximo caracter lido sera classificado como um
	 * token MENOS (caso o proximo caracter for algo diferente de '-' ou '='), OPUNITARIO (caso o proximo caracter for '-')
	 * ou ATRIBUICAO (caso o proximo caracter for '=').
	 */
	public void operadorMenos(){
		setToken(Token.MENOS);
		setTokenLenght();
		if(this.proxCaractere == '-'){
			setToken(Token.OPUNITARIO);
			setTokenLenght();
			leProxCaractere();			
		}
		else if(this.proxCaractere == ATRIBUICAO){
			setToken(Token.ATRIBUICAO);
			setTokenLenght();
			leProxCaractere();
		}
	}
	
	/**
	 * Responsável por avaliar quando dado uma entrada igual '!' de acordo com o proximo caracter lido sera classificado como um
	 * token OPUNITARIO (caso o proximo caracter for algo diferente de '=') ou OPERADOR (caso o proximo caracter for '=').
	 */
	public void operadorExclamacao(){
		setToken(Token.OPUNITARIO);
		setTokenLenght();
		if(this.proxCaractere == ATRIBUICAO){
			setToken(Token.OPERADOR);
			setTokenLenght();
			leProxCaractere();
		}
	}
	
	/**
	 * Responsável por avaliar que dado uma entrada igual (*, /, %, ^) de acordo com o proximo caracter lido sera classificado como um
	 * token OPERADOR (caso o proximo caracter for algo diferente de '=') ou ATRIBUICAO (caso o proximo caracter for '=').
	 */
	public void conjuntoAtribuicao(){
		setToken(Token.OPERADOR);
		setTokenLenght();
		if(this.proxCaractere == ATRIBUICAO){
			setToken(Token.ATRIBUICAO);
			setTokenLenght();
			leProxCaractere();
		}
	}
	
	/**
	 * Reponsavel por avaliar os operadores relacionais &, && e &=.
	 */
	public void operadorRelacionalE(){
		setToken(Token.OPERADOR);
		setTokenLenght();
		if(this.proxCaractereIs("&=")){
			setTokenLenght();
			leProxCaractere();
		}
	}
	
	/**
	 * Reponsavel por avaliar os operadores relacionais |, || e |=.
	 */
	public void operadorRelacionalOU(){
		setToken(Token.OPERADOR);
		setTokenLenght();
		if(this.proxCaractereIs("|=")){
			setTokenLenght();
			leProxCaractere();
		}
	}
	
	/**
	 * Reponsavel por avaliar os operadores relacionais <, <=, << e <<=.
	 */
	public void operadorRelacionalMenor(){
		setToken(Token.OPERADOR);
		setTokenLenght();
		if(this.proxCaractere == ATRIBUICAO){
			setToken(Token.OPERADOR);
			setTokenLenght();
			leProxCaractere();
		}else if(this.proxCaractere == '<'){
			setTokenLenght();
			leProxCaractere();
			if(this.proxCaractere == ATRIBUICAO){
				setTokenLenght();
				leProxCaractere();
			}
		}
	}
	
	/**
	 * Reponsavel por avaliar os operadores relacionais >, >=, >> e >>=.
	 */
	public void operadorRelacionalMaior(){
		setToken(Token.OPERADOR);
		setTokenLenght();
		if(this.proxCaractere == ATRIBUICAO){
			setToken(Token.OPERADOR);
			leProxCaractere();
		}else if(this.proxCaractere == '>'){
			setTokenLenght();
			leProxCaractere();
			if(this.proxCaractere == ATRIBUICAO){
				setTokenLenght();
				leProxCaractere();
			}
		}
	}
}
