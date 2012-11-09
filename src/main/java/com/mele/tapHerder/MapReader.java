package com.mele.tapHerder;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.mele.games.utils.GameException;
import com.mele.games.utils.hexarray.HexArray;
import com.mele.games.utils.hexarray.HexPoint;
import com.mele.tapHerder.residents.BaseResident;
import com.mele.tapHerder.residents.Dog;
import com.mele.tapHerder.residents.Snail;
import com.mele.tapHerder.residents.Wolf;

/**
 * TODO: Create the reverse of this class - the MapWriter.  This would allow users to graphically
 * design maps and then save the ASCII result for use in future games.
 * 
 * @author Ayar
 *
 */
public class MapReader {
	protected static Logger log = Logger.getLogger(MapReader.class);
	protected HashMap<String, ETerrainType> symbolTerrain = new HashMap<String, ETerrainType>();
	protected HashMap<String, Class<?>> symbolResident = new HashMap<String, Class<?>>();
	
	public MapReader () {
		symbolTerrain.put("*", ETerrainType.FIRE);
		symbolTerrain.put(".", ETerrainType.FIELD);
		symbolTerrain.put(":", ETerrainType.PATH);
		symbolTerrain.put("~", ETerrainType.DEEP_WATER);
		symbolTerrain.put("8", ETerrainType.BOULDER);
		symbolTerrain.put("&", ETerrainType.STATUE);
		symbolTerrain.put("%", ETerrainType.DRY_BRUSH);
		symbolTerrain.put("T", ETerrainType.TREE);
		symbolTerrain.put("+", ETerrainType.FENCE);
		symbolTerrain.put("#", ETerrainType.LONG_GRASS);
		
		symbolResident.put("-", null);
		symbolResident.put("D", Dog.class);
		symbolResident.put("W", Wolf.class);
		symbolResident.put("S", Snail.class);
	}
	
	
	/**
	 * @param hexMap
	 * @param scanner
	 * @throws FileNotFoundException 
	 */
	protected void initializeHexMap(HexArray hexMap, File mapFile) throws FileNotFoundException {
		int columns = 0;
		double rows = 0;
		
		Scanner scanner = new Scanner(mapFile);
		
		// A bit unintuitive, but remember that in the ascii hex map the first
		// text row represents the first cell of each odd column, and the second
		// text row represents the first cell of each even column.  So to get the
		// total number of columns, you need to count the cells in both rows.
		// And since the HexArray is created by indicating the number of cells
		// in the first column, only every odd text row counts as a cell in the
		// first column (hence the addition of .5 per text row instead of 1).
		while (scanner.hasNext()) {
			rows = rows + .5;
			String output = scanner.nextLine();
			
			if (rows <= 1) {
				String[] cellTokens = output.trim().split("  ");
				columns = columns + cellTokens.length;
			}
		}
		
		hexMap.create(columns, (int)rows + 1);
	}
	
	/**
	 * Reads an ASCII file map and initializes the game board (including the underlying hex array) based on it.
	 *  
	 * @param game
	 * @param hexMap
	 * @param terrainMapName
	 */
	public void setMapTerrain(TapHerderGame game, HexArray hexMap, String terrainMapName) {
		URL mapResource = TapHerder.class.getClassLoader().getResource(terrainMapName);
		
		if (mapResource != null) {
			File mapFile = new File(mapResource.getFile());
	
			if (mapFile.canRead()) {
				try {
					int row = 0;
					
					initializeHexMap(hexMap, mapFile);
					
					Scanner fileScan = new Scanner(mapFile);

					while (fileScan.hasNext()) {
						row++;
						String output = fileScan.nextLine();
						String[] cellTokens = output.trim().split("  ");
	
						for (int j = 0; j < cellTokens.length; j++) {
							HexPoint point = new HexPoint();
							int x = (j * 2) + ((row%2) == 0 ? 2 : 1);
							int y = (row + 1) / 2;
							point.setCartesianPoint(x, y);
							TapHerderCell cell = (TapHerderCell) hexMap.getCell(point);
							if (cell != null) {
								log.debug(point + " " + cellTokens[j]);
								
								// TODO: Handle "special" symbols like one-way gates 
								cell.setType(symbolTerrain.get(cellTokens[j].substring(1,2)));
								try {
									Class<?> resClass = symbolResident.get(cellTokens[j].substring(0,1));
									if (resClass != null) {
										BaseResident resident = (BaseResident)resClass.newInstance();
										resident.setGame (game);
										cell.setResident(resident);
									}
								} catch (InstantiationException e) {
									log.error(GameException.fullExceptionInfo(e));
								} catch (IllegalAccessException e) {
									log.error(GameException.fullExceptionInfo(e));
								}
							} else {
								log.error("Attempted to configure hex point " + point + "as cartesian " + x + ", " + y + " with '" + cellTokens[j] + "'.  This does not exist on the map, using file coordinates " + row + ", " + j);
							}
						}
					}
	
				} catch (FileNotFoundException e) {
					throw new GameException("Map file does not exist: "
							+ mapFile.getAbsolutePath());
				}
	
			} else {
				throw new GameException("Cannot read mapFile: "
						+ mapFile.getAbsolutePath());
			}
		} else {
			throw new GameException("Cannot find map on the resource path: " + terrainMapName);
		}
	}
}
