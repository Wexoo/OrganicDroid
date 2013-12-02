/***
 * Copyright (C) 2011  naikon, wexoo
 * android@geekosphere.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.wexoo.organicdroid.data;

import java.util.ArrayList;
import java.util.Date;

/**
 * The Class AbstractProxy.
 * 
 * @author wexoo
 * @version Aug 26, 2011
 */
public abstract class AbstractProxy<T> {
	
	/** Saved search result from the latest query */
	private ArrayList<T> resultList = new ArrayList<T>();
	private ArrayList<T> favorites = new ArrayList<T>();
	
	/** Currently selected entity */
	private T selectedEntity;
	
	/**
	 * Get entities which match the searchString partly or exactly.
	 */
	public abstract ArrayList<T> getByKeyword(String searchString);
	
	public void setResultListByKeyword(final String searchString) {
		resultList = getByKeyword(searchString);
	}
	
	public abstract T getById(Integer id);
	
	public void setSelectedEntityById(Integer id) {
		selectedEntity = getById(id);
	}
	
	/**
	 * Get results by district id for map view.
	 */
	public abstract ArrayList<T> getByDistrict(Integer districtId);
	
	public void setByDistrict(final Integer districtId) {
		resultList = getByDistrict(districtId);
	}
	
	/**
	 * Get results, which are opened at given date
	 * 
	 * @param date opening date - required format -> "YYYY-MM-DD"
	 */
	public abstract ArrayList<T> getOpenByDate(Date date);
	
	/**
	 * @param date required format -> "YYYY-MM-DD"
	 */
	public void setOpenByDate(final Date date) {
		resultList = getOpenByDate(date);
	}
	
	/**
	 * Get entities with distance from current position
	 */
	public abstract ArrayList<T> getNearbyEntities(double latitude, double longitude);
	
	/**
	 * Set entities with distance from current position
	 */
	public void setNearbyEntities(final double latitude, final double longitude) {
		resultList = getNearbyEntities(latitude, longitude);
	}
	
	/**
	 * Get favorite entities of user
	 */
	public abstract ArrayList<T> fetchFavorites();
	
	public void setFavorites() {
		resultList = fetchFavorites();
	}
	
	/**
	 * @param date opening date - required format -> "YYYY-MM-DD"
	 * @param searchString location string
	 */
	public abstract ArrayList<T> getByDateAndLocation(Date date, String searchString);
	
	/**
	 * @see
	 */
	public void setByDateAndLocation(final Date date, final String searchString) {
		resultList = getByDateAndLocation(date, searchString);
	}
	
	public ArrayList<T> getResultList() {
		if (resultList == null) {
			resultList = new ArrayList<T>();
		}
		return resultList;
	}
	
	public ArrayList<T> getFavorites() {
		if (favorites == null) {
			favorites = new ArrayList<T>();
		}
		return favorites;
	}
	
	/**
	 * Convencience method to set resultList manually
	 */
	public void setResultList(final ArrayList<T> resultList) {
		this.resultList = resultList;
	}
	
	/**
	 * Convencience method to set favorites manually
	 */
	public void setFavorites(final ArrayList<T> favorites) {
		this.favorites = favorites;
	}
	
	/**
	 * Add passed entity to resultList
	 */
	public void addToResultList(final T newEntity) {
		getResultList().add(newEntity);
	}
	
	/**
	 * Remove passed entity from the resultList
	 */
	public void removeFromResultList(final T element) {
		if (!resultList.isEmpty() && resultList.contains(element)) {
			resultList.remove(element);
		}
	}
	
	/**
	 * Insert or update entity in favorites table
	 * 
	 * @return true, if successful
	 */
	public abstract boolean updateFavorite(T entity);
	
	public T getSelectedEntity() {
		return selectedEntity;
	}
	
	public void setSelectedEntity(T selectedEntity) {
		this.selectedEntity = selectedEntity;
	}
	
	public void clearResultList() {
		resultList.clear();
	}
	
	/**
	 * Release resources.
	 */
	public abstract void releaseResources();
}