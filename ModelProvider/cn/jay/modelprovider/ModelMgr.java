package cn.jay.modelprovider;

import java.util.HashMap;

import modelinterface.ModelInterface;

public class ModelMgr {
	public static final HashMap<String, ModelInterface> MODELS = new HashMap<>();
	
	public static final boolean addModel(ModelInterface model) {
		if(hasModel(model)) {
			new Exception("Duplicated model : " + model.getModelName()).printStackTrace();
		}
		return MODELS.put(model.getModelName(), model) != null;
	}
	
	public static final ModelInterface removeModel(String modelName) {
		return MODELS.remove(modelName);
	}
	
	public static final ModelInterface getIOModel(String modelName) {
		return MODELS.get(modelName);
	}
	
	public static final boolean hasModel(String name) {
		return MODELS.containsKey(name);
	}
	
	public static final boolean hasModel(ModelInterface model) {
		return MODELS.containsKey(model.getModelName()) || MODELS.containsValue(model);
	}
	
}
