package Game;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameMap 
{
	private Image aImage;
	private double aTileHeight;
	private double aTileWidth;
	private List<List<Integer>> aMap;
	private List<List<Integer>> aLayer;
	private double aMouseX;
	private double aMouseY;
	
	public GameMap(String pFileName, double pTileWidth, double pTileHeight, List<List<Integer>> pMap,  List<List<Integer>> pLayer)
	{
		this.aImage = new Image(pFileName);
		this.aTileWidth = pTileWidth;
		this.aTileHeight = pTileHeight;
		this.aMap = pMap;
		this.aLayer = pLayer;
	}
	
	public void mLoad()
	{
		
	}
	
	public void mMouseMove(MouseEvent e)
	{
		this.mOnMouseMoved(e);
	}
	
	private void mOnMouseMoved(MouseEvent e)
	{
		this.aMouseX = e.getX();
		this.aMouseY = e.getY();
	}
	
	public double mTileWidth()
	{ 
		return this.aTileWidth;
	}
	
	public double mTileHeight()
	{
		return this.aTileHeight;
	}
	
	public ETerrainType mTerrainType(int pX, int pY)
	{
		ETerrainType vResult = ETerrainType.Grass;
		switch(this.aMap.get(pY).get(pX))
		{
			case 1:
			{
				vResult = ETerrainType.ColdLava;
			}break;
			case 10:
			case 11:
			case 12:
			{
				vResult = ETerrainType.Grass;
			}break;
			case 13:
			case 14:
			case 15:
			{
				vResult = ETerrainType.Sand;
			}break;				
			case 19:
			case 20:
			case 21:
			{
				vResult = ETerrainType.Water;
			}break;
			case 37:
			{
				vResult = ETerrainType.Lava;
			}break;
			case 55:
			case 58:
			case 61:
			case 68:
			case 142:
			{
				vResult = ETerrainType.Tree;
			}break;
			case 129:
			case 169:
			{
				vResult = ETerrainType.Rocks;
			}break;	
		}
		return vResult;
	}
	
	public void mDraw(GraphicsContext pGraphicsContext)
	{
		double vX = this.aImage.getWidth() / this.aTileWidth;
		for(int vYIndex = 0; vYIndex < this.aMap.size(); vYIndex++)
		{
			for(int vXIndex = 0; vXIndex < this.aMap.get(0).size(); vXIndex++)
			{
				int vValue = this.aMap.get(vYIndex).get(vXIndex);
				int vLayer = this.aLayer.get(vYIndex).get(vXIndex);
				double vXFrom = (int) (vValue % vX - 1);
				double vYFrom = (int) (vValue / vX);
				double vXLayer = (int) (vValue % vX - 1);
				double vYLayer = (int) (vValue / vX);
				pGraphicsContext.drawImage(this.aImage, vXFrom * this.aTileWidth, vYFrom * this.aTileHeight, this.aTileWidth, this.aTileHeight, vXIndex * this.aTileWidth, vYIndex * this.aTileHeight, this.aTileWidth, this.aTileHeight);
				pGraphicsContext.drawImage(this.aImage, vXLayer * this.aTileWidth, vYLayer * this.aTileHeight, this.aTileWidth, this.aTileHeight, vXIndex * this.aTileWidth, vYIndex * this.aTileHeight, this.aTileWidth, this.aTileHeight);
			}
		}
		
		int vXIndex = (int) (this.aMouseX / this.aTileWidth);
		int vYIndex = (int) (this.aMouseY / this.aTileHeight);
		Font vFont = Font.font( "Times New Roman", FontWeight.BOLD, 14 );
		if
		(
			(vXIndex >= 0)
			&&
			(vYIndex >= 0)
			&&
			(vXIndex < this.aMap.get(0).size())
			&&
			(vYIndex < this.aMap.size())
		)
		{
			Game.mDrawText(pGraphicsContext, 10.0, 20.0, vFont, "Tile(ID): " + this.mTerrainType(vXIndex, vYIndex).mLabel() + "(" + this.aMap.get(vYIndex).get(vXIndex) + ")", 0.0, Color.RED, Color.BLACK);
		}
		else
		{
			Game.mDrawText(pGraphicsContext, 10.0, 20.0, vFont, "Out...", 0.0, Color.RED, Color.BLACK);
		}
	}
}
