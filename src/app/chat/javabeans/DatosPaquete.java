package app.chat.javabeans;

public class DatosPaquete {

	private String nickname;
	private String ip;
	private String texto;
	
	public DatosPaquete() {}
	
	public DatosPaquete(String nickname, String ip, String texto) {
		this.nickname = nickname;
		this.ip = ip;
		this.texto = texto;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
	
}
