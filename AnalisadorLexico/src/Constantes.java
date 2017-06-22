
public interface Constantes {
		 
		//enum Token { NUM, ATRIB, IDENT, EOF, PTOVIRG, VAZIO, OP_BIN };
		enum Token {  EOF, AP, FP, ACH, FCH, PTVIR, DOISPT, PONTO, IF, WHILE, DO, FOR, SWITCH, CASE, OPERADORBIN, OPERADORUNIT, VAR, ATRIBUICAO, INT, REAL }; //FALTA INT, REAL, OP_BIN E OP_UN
	 
		String 	DIGITOS	= "0123456789",
					LETRAS	= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
					OPERADORES = "*/%^&|<>",
					VAZIOS	= " \r\n\t";
	 
		char	EOF	= 0,
				AP = '(',
				FP = ')',
				ACH = '{',
				FCH = '}',
				PTVIR	= ';',
				PONTO = '.',
				ATRIBUICAO  	= '=',
				DOISPT 	= ':';
	 
		String   NOME_DEFAULT_ARQUIVO_ENTRADA = "entrada.txt";
}
