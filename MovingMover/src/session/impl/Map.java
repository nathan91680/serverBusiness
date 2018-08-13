package session.impl;

import java.util.ArrayList;

public class Map {
	
	private int width;
	private int height;
	private ArrayList<ArrayList<Cell>> cells;
	
	
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the cells
	 */
	public ArrayList<ArrayList<Cell>> getCells() {
		return cells;
	}

	/**
	 * @param cells the cells to set
	 */
	public void setCells(ArrayList<ArrayList<Cell>> cells) {
		this.cells = cells;
	}

	public Map(int lWidth, int lHeight){
		this.width = lWidth;
		this.height = lHeight;
		this.cells = new ArrayList<ArrayList<Cell>>();
		for(int i = 0;i<lHeight;i++) {
			ArrayList<Cell> lineCell = new ArrayList<Cell>();
			for(int j = 0; j < lWidth;j++) {
				Cell cell = new Cell(j,i);
				cell.setMap(this);
				lineCell.add(cell);
			}
			cells.add(lineCell);
		}
	}
	
	public Cell getCell(int x,int y) {
		
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return null;
		}
		
		return cells.get(y).get(x);
	}
}
