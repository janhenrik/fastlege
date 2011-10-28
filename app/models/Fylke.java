package models;

public enum Fylke {

	AUSTAGDER("Aust-Agder", "09"), BUSKERUD("Buskerud", "06"), FINMARK(
			"Finnmark", "20"), HEDMARK("Hedmark", "04"), HORDALAND("Hordaland",
			"12"), MOREOGROMSDAL("M�re Og Romsdal", "15"), NORDTRONDELAG(
			"Nord-Tr�ndelag", "17"), NORDLAND("Nordland", "18"), OPPLAND(
			"Oppland", "05"), OSLO("Oslo", "03"), ROGALAND("Rogaland", "11"), SOGNOGFJORDANE(
			"Sogn Og Fjordane", "14"), SORTRONDELAG("S�r-Tr�ndelag", "16"), TELEMARK(
			"Telemark", "08"), TROMS("Troms", "19"), VESTAGDER("Vest-Agder",
			"10"), VESTFOLD("Vestfold", "07"), OSTFOLD("�stfold", "01");

	private final String navn;
	private final String nummer;

	private Fylke(String navn, String nummer) {
		this.navn = navn;
		this.nummer = nummer;
	}

	public String getNavn() {
		return navn;
	}

	public String getNummer() {
		return nummer;
	}
}
