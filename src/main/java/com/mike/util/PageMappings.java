package com.mike.util;

public final class PageMappings
{
	public static final String HOME = "/";
	public static final String ERROR = "error";
	public static final String LOGOUT = "logout";


	// == ITEMS ==
	public static final String ITEMS_LIST = "items_list";
	public static final String ADD_ITEM = "add_item";
	public static final String EDIT_ITEM = "edit_item";
	public static final String DELETE_ITEM = "delete_item";
	public static final String REDIRECT_ITEMS_LIST = "redirect:/" + ITEMS_LIST;
	public static final String DELETE_ITEM_PARAM = "delete_item_param";
	public static final String ADD_ITEM_PARAM = "add_item_param";
	public static final String ADD_PARAM_TO_ITEM = "add_param_to_item";


	// == PARAMETERS ==
	public static final String PARAMETERS_LIST = "parameters_list";
	public static final String ADD_PARAMETER = "add_parameter";
	public static final String EDIT_PARAMETER = "edit_parameter";
	public static final String DELETE_PARAMETER = "delete_parameter";
	public static final String REDIRECT_PARAMETERS_LIST = "redirect:/" + PARAMETERS_LIST;


	// == STATUES ==
	public static final String STATUSES_LIST = "statuses_list";
	public static final String ADD_STATUS = "add_status";
	public static final String EDIT_STATUS = "edit_status";
	public static final String DELETE_STATUS = "delete_status";
	public static final String REDIRECT_STATUSES_LIST = "redirect:/" + STATUSES_LIST;


	// == CATEGORIES ==
	public static final String CATEGORIES_LIST = "categories_list";
	public static final String ADD_CATEGORY = "add_category";
	public static final String EDIT_CATEGORY = "edit_category";
	public static final String DELETE_CATEGORY = "delete_category";
	public static final String REDIRECT_CATEGORIES_LIST = "redirect:/" + CATEGORIES_LIST;
}
