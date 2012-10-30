package com.mele.tapHerder.residents;

import com.mele.games.utils.hexarray.EHexVector;
import com.mele.tapHerder.TapHerderCell;

/**
 * Basic methods any resident must support.
 * 
 * @author Ayar
 *
 */
public interface IResident {
	public void kill();
	
	public void react(TapHerderCell homeCell, EHexVector vector); 
}
