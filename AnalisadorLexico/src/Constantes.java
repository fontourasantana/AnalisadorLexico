
public interface Constantes {
		enum Token {  EOF, AP, FP, ACH, FCH, PTVIR, DOISPT, PONTO, IF, WHILE, DO, FOR, SWITCH, CASE, OPERADORBIN, OPERADORUNIT, VAR, ATRIBUICAO, INT, REAL, POSITIVO, NEGATIVO }; //FALTA INT, REAL, OP_BIN E OP_UN
	 
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
				POSITIVO = '+',
				NEGATIVO = '-',
				DOISPT 	= ':';
	 
		String   NOME_DEFAULT_ARQUIVO_ENTRADA = "entrada.txt";
}
