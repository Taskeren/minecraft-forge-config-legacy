/**
 * This software is provided under the terms of the Minecraft Forge Public
 * License v1.0.
 */

package net.minecraftforge.common.config;

import java.util.regex.Pattern;

/**
 * This class bridges the gap between the FML config GUI classes and the Forge Configuration classes.
 */
@SuppressWarnings("unchecked")
public class ConfigElement<T> {
	private Property prop;
	private Property.Type type;
	private boolean isProperty;
	private ConfigCategory ctgy;
	private boolean categoriesFirst = true;

	public ConfigElement(ConfigCategory ctgy) {
		this.ctgy = ctgy;
		isProperty = false;
	}

	public ConfigElement(Property prop) {
		this.prop = prop;
		this.type = prop.getType();
		this.isProperty = true;
	}

	public ConfigElement<T> listCategoriesFirst(boolean categoriesFirst) {
		this.categoriesFirst = categoriesFirst;
		return this;
	}

	public String getName() {
		return isProperty ? prop.getName() : ctgy.getName();
	}

	public boolean isProperty() {
		return isProperty;
	}

	public String getQualifiedName() {
		return isProperty ? prop.getName() : ctgy.getQualifiedName();
	}

	public boolean isList() {
		return isProperty && prop.isList();
	}

	public boolean isListLengthFixed() {
		return isProperty && prop.isListLengthFixed();
	}

	public int getMaxListLength() {
		return isProperty ? prop.getMaxListLength() : -1;
	}

	public String getComment() {
		return isProperty ? prop.comment : ctgy.getComment();
	}

	public boolean isDefault() {
		return !isProperty || prop.isDefault();
	}

	public void setToDefault() {
		if(isProperty)
			prop.setToDefault();
	}

	public boolean requiresWorldRestart() {
		return isProperty ? prop.requiresWorldRestart() : ctgy.requiresWorldRestart();
	}

	public boolean showInGui() {
		return isProperty ? prop.showInGui() : ctgy.showInGui();
	}

	public boolean requiresMcRestart() {
		return isProperty ? prop.requiresMcRestart() : ctgy.requiresMcRestart();
	}

	public String[] getValidValues() {
		return isProperty ? prop.getValidValues() : null;
	}

	public String getLanguageKey() {
		return isProperty ? prop.getLanguageKey() : ctgy.getLanguageKey();
	}

	public Object getDefault() {
		return isProperty ? (T) prop.getDefault() : null;
	}

	public Object[] getDefaults() {
		if(isProperty) {
			String[] aVal = prop.getDefaults();
			if(type == Property.Type.BOOLEAN) {
				Boolean[] ba = new Boolean[aVal.length];
				for(int i = 0; i < aVal.length; i++)
					ba[i] = Boolean.valueOf(aVal[i]);
				return ba;
			} else if(type == Property.Type.DOUBLE) {
				Double[] da = new Double[aVal.length];
				for(int i = 0; i < aVal.length; i++)
					da[i] = Double.valueOf(aVal[i]);
				return da;
			} else if(type == Property.Type.INTEGER) {
				Integer[] ia = new Integer[aVal.length];
				for(int i = 0; i < aVal.length; i++)
					ia[i] = Integer.valueOf(aVal[i]);
				return ia;
			} else
				return aVal;
		}
		return null;
	}

	public Pattern getValidationPattern() {
		return isProperty ? prop.getValidationPattern() : null;
	}

	public Object get() {
		return isProperty ? (T) prop.getString() : null;
	}

	public Object[] getList() {
		if(isProperty) {
			String[] aVal = prop.getStringList();
			if(type == Property.Type.BOOLEAN) {
				Boolean[] ba = new Boolean[aVal.length];
				for(int i = 0; i < aVal.length; i++)
					ba[i] = Boolean.valueOf(aVal[i]);
				return ba;
			} else if(type == Property.Type.DOUBLE) {
				Double[] da = new Double[aVal.length];
				for(int i = 0; i < aVal.length; i++)
					da[i] = Double.valueOf(aVal[i]);
				return da;
			} else if(type == Property.Type.INTEGER) {
				Integer[] ia = new Integer[aVal.length];
				for(int i = 0; i < aVal.length; i++)
					ia[i] = Integer.valueOf(aVal[i]);
				return ia;
			} else
				return aVal;
		}
		return null;
	}

	public void set(T value) {
		if(isProperty) {
			if(type == Property.Type.BOOLEAN)
				prop.set(Boolean.parseBoolean(value.toString()));
			else if(type == Property.Type.DOUBLE)
				prop.set(Double.parseDouble(value.toString()));
			else if(type == Property.Type.INTEGER)
				prop.set(Integer.parseInt(value.toString()));
			else
				prop.set(value.toString());
		}
	}

	public void set(T[] aVal) {
		if(isProperty) {
			if(type == Property.Type.BOOLEAN) {
				boolean[] ba = new boolean[aVal.length];
				for(int i = 0; i < aVal.length; i++)
					ba[i] = Boolean.valueOf(aVal[i].toString());
				prop.set(ba);
			} else if(type == Property.Type.DOUBLE) {
				double[] da = new double[aVal.length];
				for(int i = 0; i < aVal.length; i++)
					da[i] = Double.valueOf(aVal[i].toString());
				prop.set(da);
			} else if(type == Property.Type.INTEGER) {
				int[] ia = new int[aVal.length];
				for(int i = 0; i < aVal.length; i++)
					ia[i] = Integer.valueOf(aVal[i].toString());
				prop.set(ia);
			} else {
				String[] is = new String[aVal.length];
				for(int i = 0; i < aVal.length; i++)
					is[i] = aVal[i].toString();
				prop.set(is);
			}
		}
	}

	public T getMinValue() {
		return isProperty ? (T) prop.getMinValue() : null;
	}

	public T getMaxValue() {
		return isProperty ? (T) prop.getMaxValue() : null;
	}
}