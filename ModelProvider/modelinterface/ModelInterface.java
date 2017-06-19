package modelinterface;

public interface ModelInterface {
	public void deploy();
	public void undeploy();

	public void setModelName(String name);
	public String getModelName();
	
	public boolean isRunning();
}
