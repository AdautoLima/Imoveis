package br.com.administracao.model;

//import java.beans.Transient;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "imovel")
@Entity
public class Imovel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Endereco endereco;
	private Proprietario proprietario;
	private byte quarto;
	private byte suite;
	private byte mictorio;
	private byte garagem;
	private char areaServico;
	private char sobrado;
	private String observacoes;
	private Usuario usuarioCadastro;
	private Calendar dataCadastro;
	private Usuario usuarioAlteracao;
	private Calendar dataAlteracao;
	private Boolean inativo = false;
	

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//Isso significa que ao realizarmos um “SELECT * FROM Imoveis” teremos todos os campos retornados, 
	//mas os campos com a propriedade FetchType.LAZY (Endereço) estarão nulos, mesmo que eles existam no banco.
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "endereco_id", nullable = false)
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "proprietario_id", nullable = false)
	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	@Column(nullable = false)
	public byte getQuarto() {
		return quarto;
	}

	public void setQuarto(byte quarto) {
		this.quarto = quarto;
	}

	@Column(nullable = false)
	public byte getSuite() {
		return suite;
	}

	public void setSuite(byte suite) {
		this.suite = suite;
	}

	@Column(nullable = false)
	public byte getMictorio() {
		return mictorio;
	}

	public void setMictorio(byte mictorio) {
		this.mictorio = mictorio;
	}

	@Column(nullable = false)
	public byte getGaragem() {
		return garagem;
	}

	public void setGaragem(byte garagem) {
		this.garagem = garagem;
	}
		
	@Column(length = 1, nullable = false)
	public char getAreaServico() {
		return areaServico;
	}

	public void setAreaServico(char areaServico) {
		this.areaServico = areaServico;
	}
	
	@Column(length = 1, nullable = false)
	public char getSobrado() {
		return sobrado;
	}

	public void setSobrado(char sobrado) {
		this.sobrado = sobrado;
	}
	
	
	@Transient
	public String getAreaServicoDescricao() {
		String areaServicoDescricao = null;
		switch (areaServico) {
			case 'S': areaServicoDescricao = "Sim";
			break;
			case 'N': areaServicoDescricao = "Não";
			break;
		}
		return areaServicoDescricao;
	}

		
	@Transient
	public String getSobradoDescricao() {
		String sobradoDescricao = null;
		switch (sobrado) {
			case 'S': sobradoDescricao = "Sim";
			break;
			case 'N': sobradoDescricao = "Não";
			break;
		}
		return sobradoDescricao;
	}
	
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
		
	public Boolean isInativo() {
		return inativo;
	}

	public void setInativo(Boolean inativo) {
		this.inativo = inativo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_alteracao")
	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}
	
	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	@ManyToOne
	@JoinColumn(name = "usuarioCadastro_id")
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	@ManyToOne
	@JoinColumn(name = "usuarioAlteracao_id")
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imovel other = (Imovel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
