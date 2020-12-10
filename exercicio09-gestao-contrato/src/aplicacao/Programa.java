package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import entidades.Colaborador;
import entidades.Contrato;
import entidades.ContratoAssalariado;
import entidades.ContratoComissionado;
import entidades.ContratoHorista;
import entidades.VendaComissionada;

public class Programa {
	static Scanner sc = new Scanner(System.in);
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	static List<Colaborador> listaColaboradores = new ArrayList<>();
	static List<Contrato> listaContratos = new ArrayList<>();
	static List<VendaComissionada> listaVendas = new ArrayList<>();

	public static void main(String[] args) throws ParseException {

		// Repositório de Colaboradores
		Colaborador colaborador1 = new Colaborador("001", "111.222.333-44", "Victor", sdf.parse("05/06/1982"));
		Colaborador colaborador2 = new Colaborador("002", "111.222.333-55", "Maria", sdf.parse("15/07/1990"));
		Colaborador colaborador3 = new Colaborador("003", "111.222.333-66", "Joana", sdf.parse("27/02/1974"));
		Colaborador colaborador4 = new Colaborador("004", "111.222.333-77", "Alex", sdf.parse("01/12/1987"));
		Colaborador colaborador5 = new Colaborador("005", "111.222.333-88", "Bruna", sdf.parse("08/01/1995"));
		listaColaboradores.add(colaborador1);
		listaColaboradores.add(colaborador2);
		listaColaboradores.add(colaborador3);
		listaColaboradores.add(colaborador4);
		listaColaboradores.add(colaborador5);

		// Repositório de Contratos
		listaContratos.add(new ContratoAssalariado(sdf.parse("05/06/2020"), colaborador1, 1000f, 5.0f, 5.0f));
		listaContratos.add(new ContratoComissionado(sdf.parse("01/01/2020"), colaborador4, 10.0f, 2000.00f));
		listaContratos.add(new ContratoHorista(sdf.parse("15/01/2020"), colaborador1, 20, 100f));
		listaContratos.add(new ContratoAssalariado(sdf.parse("01/07/2020"), colaborador2, 2000f, 10.0f, 10.0f));
		listaContratos.add(new ContratoComissionado(sdf.parse("15/05/2020"), colaborador4, 20.0f, 1000.00f));
		listaContratos.add(new ContratoHorista(sdf.parse("01/04/2020"), colaborador2, 30, 100f));

		boolean sair = false;
		do {
			menu();
			System.out.print("Digite a opção desejada do menu: ");
			int n = sc.nextInt();

			switch (n) {
			case 1:
				inserirColaborador();
				break;
			case 2:
				registrarContrato();
				break;
			case 3:
				consultarContrato();
				break;
			case 4:
				encerrarContrato();
				break;
			case 5:
				listarColaboradoresAtivos();
				break;
			case 6:
				consultarContratosColaborador();
				break;
			case 7:
				lancarVendaComissionada();
				break;
			case 8:
				emitirFolhaPagamento();
				break;
			case 0:
				sair = true;
				System.out.println("Saindo...\n");
				break;
			}
		} while (sair == false);

	}

	public static void menu() {
		System.out.println("\n------GESTÃO DE CONTRATOS------\n");
		System.out.println(" 1 - Inserir Colaborador");
		System.out.println(" 2 - Registrar Contrato");
		System.out.println(" 3 - Consultar Contrato");
		System.out.println(" 4 - Encerrar Contrato");
		System.out.println(" 5 - Listar Colaboradores Ativos");
		System.out.println(" 6 - Consultar Contratos do Colaborador");
		System.out.println(" 7 - Lançar Venda Comissionada");
		System.out.println(" 8 - Emitir Folha de Pagamento");
		System.out.println(" 0 - Sair\n");
	}

	public static void inserirColaborador() throws ParseException {
		System.out.println("\n------INSERIR COLABORADOR------\n");
		boolean apenasNumeros = false;
		String matricula = null;
		do {
			try {
				System.out.print("Matrícula: ");
				matricula = sc.next();
				int matriculaInt = Integer.parseInt(matricula);
				apenasNumeros = true;
			} catch (Exception e) {
				System.out.println("\nA matrícula deve conter apenas números. Digite novamente.\n");
			}			
		} while(apenasNumeros == false);
		
		System.out.print("CPF: ");
		String cpf = sc.next();
		sc.nextLine();
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		
		Date dataNascimento;
		do {
			System.out.print("Data de Nascimento (dd/mm/aaaa): ");
			dataNascimento = sdf.parse(sc.next());
			if (validarMaioridade(dataNascimento) == false) {
				System.out.println("\nO colaborador deve ser maior de 18 anos. Digite novamente a data de nascimento.\n");
			} else {
				break;
			}
		} while (true);
		
		Colaborador colaborador = new Colaborador(matricula, cpf, nome, dataNascimento);	
		if (colaborador.validarCpf(cpf)) {
			listaColaboradores.add(colaborador);
			System.out.println("\nColaborador(a): " + nome +", adicionado(a) com sucesso.\nMatrícula: " + matricula + "\n");				
		} else {
			colaborador = null;
			System.out.println("\nCPF informado inválido. Não foi possível adicionar o colaborador.\n");				
		}
	}

	public static void registrarContrato() throws ParseException {
		System.out.println("\n------REGISTRAR CONTRATO------\n");
		Date dataInicio;
		do {
			System.out.print("Data Inicial: ");
			dataInicio = sdf.parse(sc.next());
			if (checarSeDataPosteriorOuIgualHoje(dataInicio)) {
				break;
			} else {
				System.out.println("\nA data de início não pode ser anterior à data de hoje. Digite novamente.\n");
			}
		} while (true);

		Colaborador colaborador;
		do {
			colaborador = procurarColaboradorMat();
			if (checarSituacaoColaborador(colaborador)) {
				System.out.println("\nO colaborador deve ter a sua situação inativa. Digite um outro número de matrícula.\n");
			} else {
				break;
			}
		} while (true);

		String tipoContrato;
		do {
			System.out.print("Tipo de Contrato (Assalariado / Comissionado / Horista): ");
			tipoContrato = sc.next();
			if (tipoContrato.equalsIgnoreCase("Assalariado") || tipoContrato.equalsIgnoreCase("Comissionado")
					|| tipoContrato.equalsIgnoreCase("Horista")) {
				break;
			} else {
				System.out.println("\nO contrato deve ser um desses tipos: Assalariado, Comissionado ou Horista. Digite novamente.\n");
			}
		} while (true);

		Contrato contrato = null;
		int numContrato;

		if (tipoContrato.equalsIgnoreCase("Assalariado")) {
			System.out.print("Salário Mensal: R$ ");
			float salarioMensal = sc.nextFloat();
			System.out.print("Percentual Insalubridade: ");
			float percInsalubridade = sc.nextFloat();
			System.out.print("Percentual Periculosidade: ");
			float percPericulosidade = sc.nextFloat();
			contrato = new ContratoAssalariado(dataInicio, colaborador, salarioMensal, percInsalubridade, percPericulosidade);
		}
		
		if (tipoContrato.equalsIgnoreCase("Comissionado")) {
			System.out.print("Percentual Comissao: ");
			float percComissao = sc.nextFloat();
			System.out.print("Ajuda de Custo: R$ ");
			float ajudaCusto = sc.nextFloat();
			contrato = new ContratoComissionado(dataInicio, colaborador, percComissao, ajudaCusto);
		}

		if (tipoContrato.equalsIgnoreCase("Horista")) {
			System.out.print("Horas no mês: ");
			int horaMes = sc.nextInt();
			System.out.print("Valor da Hora: R$ ");
			float valorHora = sc.nextFloat();
			contrato = new ContratoHorista(dataInicio, colaborador, horaMes, valorHora);
		}
		
		if (contrato != null) {
			listaContratos.add(contrato);
			numContrato = contrato.getId();
			System.out.println("\nContrato adicionado com sucesso. \nRegistrado pelo nº " + numContrato + "\n");
		} else {
			System.out.println("\nERRO: Não foi possível adicionar o contrato.\n");
		}
	}

	public static void consultarContrato() {
		System.out.println("\n------CONSULTAR CONTRATO------\n");
		Contrato contrato = procurarContrato();
		System.out.println("\n---\n" + contrato);
		informarTipoContrato(contrato);
		System.out.println(" - Colaborador -\n" + contrato.getColaborador().toString());
	}

	public static void encerrarContrato() throws ParseException {
		System.out.println("\n------ENCERRAR CONTRATO------\n");
		Contrato contrato = procurarContrato();
		if (checarSituacaoContrato(contrato)) {
			do {
				System.out.print("Data do encerramento: ");
				Date dataEncerramento = sdf.parse(sc.next());
				if (checarSeDataAnteriorHoje(dataEncerramento)) {
					contrato.encerrarContrato();
					contrato.setDataEncerramento(dataEncerramento);
					contrato.getColaborador().desativar();
					System.out.println("\nContrato nº " + contrato.getId() + " encerrado com sucesso.\n");
					break;
				} else {
					System.out.println("\nA data de encerramento não pode ser posterior à data atual. Digite novamente.\n");
				}
			} while (true);
		} else {
			System.out.println("\nERRO: O contrato informado já encontra-se encerrado.\n");
		}
	}

	public static void listarColaboradoresAtivos() {
		int contador = 0;
		System.out.println("\n------LISTAR COLABORADORES ATIVOS------");
		for (Colaborador colaborador : listaColaboradores) {
			if (checarSituacaoColaborador(colaborador)) {
				System.out.println("\n - - -");
				System.out.println(colaborador);
			} else {
				contador++;
			}
		}
		if (contador == listaColaboradores.size()) {
			System.out.println("\nNão existem colaboradores com a situação ativa.\n");
		}
	}

	public static void consultarContratosColaborador() {
		System.out.println("\n------CONSULTAR CONTRATOS DO COLABORADOR------\n");

		System.out.println("Como você deseja fazer a consulta?" 
							+ "\n 1 - Pela matrícula" 
							+ "\n 2 - Pelo CPF");
		int escolhido;
		do {
			System.out.print("Digite uma das opções: ");
			escolhido = sc.nextInt();
			if (escolhido == 1 || escolhido == 2) {
				break;
			} else {
				System.out.println("\nOpção inválida. Digite 1 para matrícula ou 2 para CPF.\n");
			}
		} while (true);

		Colaborador colaborador = null;
		if (escolhido == 1) {
			colaborador = procurarColaboradorMat();
		}
		if (escolhido == 2) {
			colaborador = procurarColaboradorCpf();
		}
		System.out.println("\n - Colaborador -");
		System.out.println(colaborador);
		listarContratosColaborador(colaborador);
	}

	public static void lancarVendaComissionada() {
		System.out.println("\n------LANÇAR VENDA COMISSIONADA------\n");
		Contrato contrato = procurarContrato();
		if (checarSituacaoContrato(contrato)) {	
			if (contrato instanceof ContratoComissionado) {
				System.out.print("Mês: ");
				int mes = sc.nextInt();
				System.out.print("Ano:");
				int ano = sc.nextInt();
				System.out.println("Valor total das vendas: R$ ");
				float valor = sc.nextFloat();
				
				listaVendas.add(new VendaComissionada(mes, ano, valor,(ContratoComissionado)contrato));
				
				System.out.println("\nLançamento realizado com sucesso.\n");
			} else {
				System.out.println("\nNão é possível realizar lançamentos. O contrato informado não é um contrato comissionado.\n");
			}
		} else {
			System.out.println("\nERRO: O contrato informado já encontra-se encerrado.\n");
		}
		
	}

	public static void emitirFolhaPagamento() throws ParseException {
		System.out.println("\n------EMITIR FOLHA DE PAGAMENTOS------\n");
		System.out.print("Mês: ");
		int mes = sc.nextInt();
		System.out.print("Ano:");
		int ano = sc.nextInt();
		System.out.println("\n >>> FOLHA DE PAGAMENTOS | " + mes + "/" + ano + " <<<");
		
		for (Contrato contrato : listaContratos) {
			if (checarSituacaoContrato(contrato)) {
					System.out.println("\n - - -");
					System.out.println(" Contrato nº " + contrato.getId());
					informarTipoContrato(contrato);
					System.out.println("\n - Colaborador -");
					System.out.println(" Matrícula: " + contrato.getColaborador().getMatricula());
					System.out.println(" Nome: " + contrato.getColaborador().getNome());
				}
				
				if (contrato instanceof ContratoAssalariado) {
					System.out.println(" Salário: R$ " + ((ContratoAssalariado)contrato).calcVencimento());
				} else if (contrato instanceof ContratoComissionado) {
					float valorFaturamento = 0f;
					for (VendaComissionada venda : listaVendas) {
						if(venda.getContrComissionado() == contrato && venda.getMes() == mes && venda.getAno() == ano) {
							valorFaturamento += venda.getValor();
						}
					}
					System.out.println(" Salário: R$ " + ((ContratoComissionado)contrato).calcVencimento(valorFaturamento));						
				} else {
					System.out.println(" Salário: R$ " + ((ContratoHorista)contrato).calcVencimento());
				}
		}
	}


	public static boolean validarMaioridade(Date dataNascimento) throws ParseException {
		Calendar dataNasc = new GregorianCalendar();
		dataNasc.setTime(dataNascimento);
		Calendar hoje = Calendar.getInstance();
		int idade = hoje.get(Calendar.YEAR) - dataNasc.get(Calendar.YEAR);
		dataNasc.add(Calendar.YEAR, idade);
		if (hoje.before(dataNasc)){
			idade--;
		}
		if (idade >= 18) {
			return true;
		} else {
			return false;
		}
	}

	public static Colaborador procurarColaboradorMat() {
		int contador = 0;
		do {
			System.out.print("Matricula do Colaborador: ");
			String matricula = sc.next();

			for (Colaborador colaborador : listaColaboradores) {
				if (colaborador.getMatricula().equalsIgnoreCase(matricula)) {
					return colaborador;
				} else {
					contador++;
				}
			}
			if (contador == listaColaboradores.size()) {
				System.out.println("\nMatrícula Inválida. Digite novamente a matrícula.\n");
			}
		} while (true);
	}

	public static Colaborador procurarColaboradorCpf() {
			int contador = 0;
			do {
				System.out.print("CPF: ");
				String cpf = sc.next();
				for (Colaborador colaborador : listaColaboradores) {
					if (colaborador.getCpf().equalsIgnoreCase(cpf)) {
						return colaborador;
					} else {
						contador++;
					}
				}
				if (contador == listaColaboradores.size()) {
					System.out.println("\n CPF Inválido. Digite novamente o CPF.\n");
				}
			} while (true);
	}

	public static boolean checarSituacaoColaborador(Colaborador colaborador) {
		if (colaborador.isSituacao()) {
			return true;
		} else {
			return false;
		}
	}

	public static Contrato procurarContrato() {
		int contador = 0;
		do {
			System.out.print("Número do contrato: ");
			int numContrato = sc.nextInt();

			for (Contrato contrato : listaContratos) {
				if (contrato.getId() == numContrato) {
					return contrato;
				} else {
					contador++;
				}
			}
			if (contador == listaContratos.size()) {
				System.out.println("\nNúmero de contrato inválido. Digite novamente.\n");
			}
		} while (true);
	}

	public static boolean checarSituacaoContrato(Contrato contrato) {
		if (contrato.isAtivo()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checarSeDataPosteriorOuIgualHoje(Date dataInicial) {
		Calendar data = new GregorianCalendar();
		data.setTime(dataInicial);
		Calendar hoje = Calendar.getInstance();
		boolean igual = hoje.get(Calendar.DATE) == data.get(Calendar.DATE);
		if (data.after(hoje) || igual == true){
			return true;
		} else {
			return false;
		}
	}

	public static boolean checarSeDataAnteriorHoje(Date data) {
		if (data.before(new Date())) {
			return true;
		} else {
			return false;
		}
	}
	

	public static void informarTipoContrato(Contrato contrato) {
		if (contrato instanceof ContratoAssalariado) {
			System.out.println(" Tipo de Contrato: Contrato Assalariado");
		}
		if (contrato instanceof ContratoComissionado) {
			System.out.println(" Tipo de Contrato: Contrato Comissionado");
		}
		if (contrato instanceof ContratoHorista) {
			System.out.println(" Tipo de Contrato: Contrato Horista");
		}
	}
	
	public static void listarContratosColaborador(Colaborador colaborador) {
		int contador = 0;
		for (Contrato contrato : listaContratos) {
			if (contrato.getColaborador() == colaborador) {
				System.out.println("\n - - -");
				System.out.println(contrato);
				informarTipoContrato(contrato);
			} else {
				contador++;
			}
		}
		if (contador == listaContratos.size()) {
			System.out.println("\nColaborador não possui contratos.\n");
		}
	}

}
