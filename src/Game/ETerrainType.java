package Game;

public enum ETerrainType 
{
	ColdLava("Cold Lava"),
	Grass("Grass"),
	Sand("Sand"),
	Water("Water"),
	Lava("Lava"),
	Tree("Tree"),
	Rocks("Rocks"),
	Sea("Sea");
	
	private String aLabel;
	
	ETerrainType(String pLabel)
	{
		this.aLabel = pLabel;
	}
	
	public String mLabel()
	{
		return this.aLabel;
	}
}
