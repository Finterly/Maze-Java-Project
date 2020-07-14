
public class Cell {

	private int visit = 0;
	private Wall northwall;
	private Wall southwall;
	private Wall eastwall;
	private Wall westwall;
	private Content content;

	public Cell() {
	}

	public Cell(Wall north, Wall south, Wall east, Wall west, Content content) {
		this.northwall = north;
		this.southwall = south;
		this.eastwall = east;
		this.westwall = west;
		this.content = content;
	}

	public int getVisit() {
		return visit;
	}

	public void incrVisit() {
		this.visit += 1;
	}
	
	public Wall getNorthwall() {
		return northwall;
	}

	public void setNorthwall(Wall northwall) {
		this.northwall = northwall;
	}

	public Wall getSouthwall() {
		return southwall;
	}

	public void setSouthwall(Wall southwall) {
		this.southwall = southwall;
	}

	public Wall getEastwall() {
		return eastwall;
	}

	public void setEastwall(Wall eastwall) {
		this.eastwall = eastwall;
	}

	public Wall getWestwall() {
		return westwall;
	}

	public void setWestwall(Wall westwall) {
		this.westwall = westwall;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}


}