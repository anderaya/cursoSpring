package curso.modelos;

public class Mensaje {
	private String mensaje;
	private String errores;
	private int code;
	
	
	
	public Mensaje() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mensaje(String mensaje, String errores, int code) {
		super();
		this.mensaje = mensaje;
		this.errores = errores;
		this.code = code;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getErrores() {
		return errores;
	}
	public void setErrores(String errores) {
		this.errores = errores;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	

}
