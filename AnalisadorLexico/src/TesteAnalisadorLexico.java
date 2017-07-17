
public class TesteAnalisadorLexico {
	static public MyAnalisadorSintatico parser;
	public static void main(String[] args) {
		try {
			if(args.length != 1)
				throw new RuntimeException("esqueceu de escrever o nome do arquivo de entrada! \n" + "No Eclipse insira em: Run - Open Run Dialog - Arguments");
			parser = new MyAnalisadorSintatico(args[0]);
			parser.inicio();
			System.out.println("Análise realizada com sucesso no arquivo " + parser.nomeArquivoEntrada);
		}
		catch(ErroLexico e) {
			System.out.println("Erro léxico: "+e.toString());
		}
		catch(ErroSintatico e) {
			System.out.println("Erro sintático: "+e.toString());
		}
		catch(RuntimeException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		/*try {
			if(args.length != 1)
				throw new RuntimeException("esqueceu de escrever o nome do arquivo de entrada! \n" + "No Eclipse insira em: Run - Open Run Dialog - Arguments");
			scanner = new MyAnalisadorLexico(args[0]);
 
			// chama a máquina de Moore várias vezes até encontrar o fim de arquivo
			do {
				scanner.q0();
				System.out.println(scanner.tokenReconhecido);
			}
			while(scanner.tokenReconhecido != Constantes.Token.EOF);
			
			System.out.println("Análise lexica realizada com sucesso  no arquivo " + scanner.nomeArquivoEntrada);
		}
		catch(ErroLexico e) {
			System.out.println("Erro léxico: " + e.toString());
		}
		catch(RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		}*/
	}
}
