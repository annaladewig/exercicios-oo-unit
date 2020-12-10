package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Colaborador {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private String matricula;
	private String cpf;
	private String nome;
	private Date dataNascimento;
	private boolean situacao;

	public Colaborador(String matricula, String cpf, String nome, Date dataNascimento) {
		this.matricula = matricula;
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.situacao = false;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public boolean validarCpf(String strCpf) {
		int iDigito1Aux = 0, iDigito2Aux = 0, iDigitoCPF;
		int iDigito1 = 0, iDigito2 = 0, iRestoDivisao = 0;
		String strDigitoVerificador, strDigitoResultado;

		if (!strCpf.substring(0, 1).equals("")) {
			try {
				strCpf = strCpf.replace('.', ' ');
				strCpf = strCpf.replace('-', ' ');
				strCpf = strCpf.replaceAll(" ", "");
				for (int iCont = 1; iCont < strCpf.length() - 1; iCont++) {
					iDigitoCPF = Integer.valueOf(strCpf.substring(iCont - 1, iCont)).intValue();
					iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
					iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
				}
				iRestoDivisao = (iDigito1Aux % 11);
				if (iRestoDivisao < 2) {
					iDigito1 = 0;
				} else {
					iDigito1 = 11 - iRestoDivisao;
				}
				iDigito2Aux += 2 * iDigito1;
				iRestoDivisao = (iDigito2Aux % 11);
				if (iRestoDivisao < 2) {
					iDigito2 = 0;
				} else {
					iDigito2 = 11 - iRestoDivisao;
				}
				strDigitoVerificador = strCpf.substring(strCpf.length() - 2, strCpf.length());
				strDigitoResultado = String.valueOf(iDigito1) + String.valueOf(iDigito2);
				return strDigitoVerificador.equals(strDigitoResultado);
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public void ativar() {
		situacao = true;
	}

	public void desativar() {
		situacao = false;
	}

	@Override
	public String toString() {
		String situacao;
		if (isSituacao()) {
			situacao = "Ativo";
		} else {
			situacao = "Inativo";
		}
		return " Matrícula: " + matricula 
					+ "\n CPF: " + cpf 
					+ "\n Nome: " + nome 
					+ "\n Data Nascimento: " + sdf.format(dataNascimento) 
					+ "\n Situação: " + situacao;
	}
}
