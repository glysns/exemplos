package digytal.java.infra.exception.config;

public enum Erro {
	LOGIN_EXISTENTE("001","Já existe um usuario cadastrado com o login %s"),
	SEM_PERMISSAO("002","Usuário sem permissão, path: %s"),
	CAMPO_OBRIGATORIO("003","Campo %s é Obrigatório"),
	REGISTRO_NAO_LOZALIZADO("004","%s não localizado com o id.: %s"),
	
	;
	
	private String codigo;
	private String mensagem;
	
	private Erro(String codigo, String mensagem) {
		this.codigo=codigo;
		this.mensagem=mensagem;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getMensagem() {
		return mensagem;
	}
	
}
