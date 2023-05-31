package entidades;

public class Sensor {
	private int index;
	private String coordenadas;
	private int umidade;
	private float ph;
	private float teorMOS;
	private String textura;

	public Sensor(int index, String coordenadas, int umidade, float ph, float teorMOS, String textura) {
		super();
		this.index = index;
		this.coordenadas = coordenadas;
		this.umidade = umidade;
		this.ph = ph;
		this.teorMOS = teorMOS;
		this.textura = textura;
	}
	
	public String converteTeorMOS(float teorMos) {
		String textura ="";
		// verifacar o teor e de acordo indicar a textura
		return  textura;
	}
	
	@Override
	public String toString() {
		return "Sensor " + index + ": Coordenadas=" + coordenadas + ", Umidade=" + umidade + ", Ph=" + ph
				+ ", Teor MOS=" + teorMOS + ", Textura=" + textura + "";
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public int getUmidade() {
		return umidade;
	}

	public void setUmidade(int umidade) {
		this.umidade = umidade;
	}

	public float getPh() {
		return ph;
	}

	public void setPh(float ph) {
		this.ph = ph;
	}

	public float getTeorMOS() {
		return teorMOS;
	}

	public void setTeorMOS(float teorMOS) {
		this.teorMOS = teorMOS;
	}

	public String getTextura() {
		return textura;
	}

	public void setTextura(String textura) {
		this.textura = textura;
	}
	
}
