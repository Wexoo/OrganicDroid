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

package net.wexoo.organicdroid.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * AbstractBaseAdapter.java
 * 
 * @author wexoo
 */
public abstract class AbstractBaseAdapter<T> extends BaseAdapter {
	
	protected final Context context;
	protected List<T> entityList;
	
	public AbstractBaseAdapter(final Context context) {
		this.context = context;
		fetchListAndSave();
	}
	
	public AbstractBaseAdapter(final Context context, List<T> entityList) {
		this.context = context;
		this.entityList = entityList;
	}
	
	@Override
	public void notifyDataSetChanged() {
		fetchListAndSave();
		super.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return entityList.size();
	}
	
	@Override
	public T getItem(final int position) {
		return entityList.get(position);
	}
	
	@Override
	public long getItemId(final int position) {
		return position;
	}
	
	/**
	 * Actions on view base for each item in the adapter's list
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View convertView, final ViewGroup viewGroup) {
		return convertView;
	}
	
	protected void fetchListAndSave() {
		entityList = fetchList();
	}
	
	protected abstract List<T> fetchList();
}