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

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Sets the list view for the SlideMenu
 * 
 * @author wexoo
 */
@SuppressLint("ViewConstructor")
public class BaseSlideMenuView extends RelativeLayout {
	
	protected List<MenuItemBean> menuItems = new ArrayList<MenuItemBean>();;
	
	public BaseSlideMenuView(final Activity context) {
		super(context);
		
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		
		setLayoutParams(rlp);
		
		ListView listView = new ListView(context);
		
		// Defining the layout parameters of the ListView
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		
		// Setting the parameters on the TextView
		listView.setLayoutParams(lp);
		
		// Adding the TextView to the RelativeLayout as a child
		addView(listView);
		
		listView.setAdapter(new BaseSlideListAdapter(context, buildMenuItemList(), getFirstDrawableKey()));
	}
	
	/**
	 * Override to define menu items of sliding menu
	 */
	protected List<MenuItemBean> buildMenuItemList() {
		// addMenuItemToList(R.drawable.home_btn_today, R.string.title_today, R.integer.title_today);
		return menuItems;
	}
	
	protected void addMenuItemToList(int imageDrawable, int title, int tagId) {
		menuItems.add(new MenuItemBean(imageDrawable, title, getResources().getInteger(tagId)));
	}
	
	protected Integer getFirstDrawableKey() {
		return null;
	}
}
