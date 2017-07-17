
public interface Constantes {
		enum Token {  EOF, AP, FP, ACH, FCH, PTVIR, DOISPT, PONTO, IF, WHILE, DO, FOR, SWITCH, CASE, OPERADOR, OPUNITARIO, VAR, ATRIBUICAO, INT, REAL, MAIS, MENOS };
	 
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
				ATRIBUICAO = '=',
				DOISPT 	= ':';
	 
		String   NOME_DEFAULT_ARQUIVO_ENTRADA = "entrada.txt";
}
