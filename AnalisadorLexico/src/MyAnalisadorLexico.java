import java.io.IOException;
 
@SuppressWarnings("unused")
public class MyAnalisadorLexico extends AnalisadorLexico {
	private boolean qtd;
	public MyAnalisadorLexico(String _nomeArquivoEntrada) {
		super(_nomeArquivoEntrada);
	}
	
	public void q0() {
		this.qtd=false;
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
				throw new ErroLexico(this.proxCaractere, DIGITOS);
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
			throw new ErroLexico(this.proxCaractere, DIGITOS+LETRAS+OPERADORES+ATRIBUICAO+AP+FP+ACH+FCH+PTVIR+PONTO+DOISPT);
		}
	}
	
	public void q3() {
		this.tokenReconhecido = Token.PTVIR;
	}
	
	public void q4() {
		this.tokenReconhecido = Token.AP;
	}
	
	public void q5() {
		this.tokenReconhecido = Token.FP;
	}
	
	public void q6() {
		this.tokenReconhecido = Token.ACH;
	}
	
	public void q7() {
		this.tokenReconhecido = Token.FCH;
	}
	
	public void q8() {
		this.tokenReconhecido = Token.DOISPT;
	}
	
	public void q9() {
		this.tokenReconhecido = Token.ATRIBUICAO;
		if(this.proxCaractere == ATRIBUICAO){
			leProxCaractere();
			this.tokenReconhecido = Token.OPERADORBIN;
		}
	}
	
	public void q10() {
		this.tokenReconhecido = Token.VAR;
		if(this.proxCaractere == 'f'){
			leProxCaractere();
			q11();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q11() {
		this.tokenReconhecido = Token.IF;
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q12() {
		this.tokenReconhecido = Token.VAR;
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
		this.tokenReconhecido = Token.VAR;
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
		this.tokenReconhecido = Token.VAR;
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
		this.tokenReconhecido = Token.VAR;
		if(this.proxCaractere == 'e'){
			leProxCaractere();
			q16();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q16() {
		this.tokenReconhecido = Token.WHILE;
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q17() {
		this.tokenReconhecido = Token.VAR;
		if(this.proxCaractere == 'o'){
			leProxCaractere();
			q18();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q18() {
		this.tokenReconhecido = Token.DO;
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q19() {
		this.tokenReconhecido = Token.VAR;
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
		this.tokenReconhecido = Token.VAR;
		if(this.proxCaractere == 'r'){
			leProxCaractere();
			q21();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q21() {
		this.tokenReconhecido = Token.FOR;
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q22() {
		this.tokenReconhecido = Token.VAR;
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
		this.tokenReconhecido = Token.VAR;
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
		this.tokenReconhecido = Token.VAR;
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
		this.tokenReconhecido = Token.VAR;
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
		this.tokenReconhecido = Token.VAR;
		if(this.proxCaractere == 'h'){
			leProxCaractere();
			q27();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q27() {
		this.tokenReconhecido = Token.SWITCH;
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q28() {
		this.tokenReconhecido = Token.VAR;
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
		this.tokenReconhecido = Token.VAR;
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
		this.tokenReconhecido = Token.VAR;
		if(this.proxCaractere == 'e'){
			leProxCaractere();
			q31();
		}
		else if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q31() {
		this.tokenReconhecido = Token.CASE;
		if(this.proxCaractereIs(LETRAS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q32() {
		this.tokenReconhecido = Token.VAR;
		if(this.proxCaractereIs(LETRAS+DIGITOS)){
			leProxCaractere();
			q32();
		}
	}
	
	public void q35() {
		this.tokenReconhecido = Token.EOF;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void palavra(){
		this.tokenReconhecido = Token.VAR;
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
	
	public void numero(){
		this.tokenReconhecido = Token.INT;
		if(this.proxCaractere == PONTO){
			leProxCaractere();
			numeroReal();
		}else if(this.proxCaractereIs(DIGITOS)){
			leProxCaractere();
			numero();
		}
	}
	
	public void numeroReal(){
		this.tokenReconhecido = Token.REAL;
		if(this.proxCaractereIs(DIGITOS)) {
			leProxCaractere();
			numeroReal();
		}
	}
	
	public void operadores(){
		this.tokenReconhecido = Token.OPERADORBIN;
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
		else if(this.proxCaractereIs(OPERADORES)){
			leProxCaractere();
			operadorBinario();
		}
	}
	
	public void operadorMais(){
		if(this.proxCaractere == '+'){
			this.tokenReconhecido = Token.OPERADORUNIT;
			leProxCaractere();
		}
		else if(this.proxCaractere == ATRIBUICAO){
			leProxCaractere();
		}
	}
	
	public void operadorMenos(){
		if(this.proxCaractere == '-'){
			leProxCaractere();
			this.tokenReconhecido = Token.OPERADORUNIT;
		}
		else if(this.proxCaractere == ATRIBUICAO){
			leProxCaractere();
		}
	}
	
	public void operadorExclamacao(){
		this.tokenReconhecido = Token.OPERADORUNIT;
		if(this.proxCaractere == ATRIBUICAO){
			leProxCaractere();
			operadorBinario();
		}
	}
	
	public void operadorBinario(){
		this.tokenReconhecido = Token.OPERADORBIN;
		if(this.proxCaractere == ATRIBUICAO)
			leProxCaractere();
		else if(this.proxCaractereIs("&|<>") && !qtd){
			qtd=true;
			leProxCaractere();
			operadorBinario();
		}
	}
}
