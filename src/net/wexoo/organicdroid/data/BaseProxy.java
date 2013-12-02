package net.wexoo.organicdroid.data;

import java.util.ArrayList;
import java.util.Date;

public abstract class BaseProxy<T> extends AbstractProxy<T> {
	
	@Override
	public ArrayList<T> getByKeyword(String searchString) {
		return null;
	}
	
	@Override
	public T getById(Integer id) {
		return null;
	}
	
	@Override
	public ArrayList<T> getByDistrict(Integer districtId) {
		return null;
	}
	
	@Override
	public ArrayList<T> getOpenByDate(Date date) {
		return null;
	}
	
	@Override
	public ArrayList<T> getNearbyEntities(double latitude, double longitude) {
		return null;
	}
	
	@Override
	public ArrayList<T> fetchFavorites() {
		return null;
	}
	
	@Override
	public ArrayList<T> getByDateAndLocation(Date date, String searchString) {
		return null;
	}
	
	@Override
	public boolean updateFavorite(T entity) {
		return false;
	}
	
	@Override
	public void releaseResources() {
	}
	
	protected abstract Class<T> getEntityClass();
}