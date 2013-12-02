/***
 * Copyright (C) 2013 wexoo
 * p.weixlbaumer@gmail.com
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
package net.wexoo.organicdroid.slidemenu;

import java.util.List;

import net.wexoo.organicdroid.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Adapter which fills the ListView of our SlidingMenu
 * 
 * @author wexoo
 */
public class BaseSlideListAdapter extends BaseAdapter {
	
	private List<MenuItemBean> menuItems;
	private static LayoutInflater inflater = null;
	private Integer keyOfFirstDrawable;
	
	public BaseSlideListAdapter(Activity context, List<MenuItemBean> menuItems) {
		this(context, menuItems, null);
	}
	
	public BaseSlideListAdapter(Activity context, List<MenuItemBean> menuItems, Integer keyOfFirstDrawable) {
		this.menuItems = menuItems;
		this.keyOfFirstDrawable = keyOfFirstDrawable;
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return menuItems.size();
	}
	
	@Override
	public Object getItem(int pos) {
		return pos;
	}
	
	@Override
	public long getItemId(int pos) {
		return pos;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.slide_menu_item, null);
		}
		
		MenuItemBean menuItem = menuItems.get(position);
		
		if (keyOfFirstDrawable != null && menuItem.getImageDrawable() == keyOfFirstDrawable) {
			TextView menuTitle = (TextView) convertView.findViewById(R.id.tv_slide_menu_title);
			menuTitle.setVisibility(View.VISIBLE);
		}
		
		Button menuItemIcon = (Button) convertView.findViewById(R.id.b_slide_menu_item_icon);
		menuItemIcon.setCompoundDrawablesWithIntrinsicBounds(menuItem.getImageDrawable(), 0, 0, 0);
		menuItemIcon.setText(menuItem.getTitle());
		menuItemIcon.setTag(menuItem.getTagId());
		
		return convertView;
	}
	
	public BaseSlideListAdapter setKeyOfFirstDrawable(Integer keyOfFirstDrawable) {
		this.keyOfFirstDrawable = keyOfFirstDrawable;
		return this;
	}
	
}