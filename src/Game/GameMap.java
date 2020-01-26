package Game;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
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
	private List<List<Double>> aFog;
	private double aMouseX;
	private double aMouseY;
	
	public GameMap(String pFileName, double pTileWidth, double pTileHeight, List<List<Integer>> pMap)
	{
		this.aImage = new Image(pFileName);
		this.aTileWidth = pTileWidth;
		this.aTileHeight = pTileHeight;
		this.aMap = pMap;
		this.aFog = new ArrayList<List<Double>>();
		for(List<Integer> vLine : this.aMap)
		{
			List<Double> vFogLine = new ArrayList<Double>();
			for(Integer vInteger : vLine)
			{
				vFogLine.add(1.0);
			}
			this.aFog.add(vFogLine);			
		}
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
	
	public int mWidth()
	{
		return this.aMap.get(0).size();
	}
	
	public int mHeight()
	{
		return this.aMap.size();
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
			{
				vResult = ETerrainType.Water;
			}break;
			case 21:
			{
				vResult = ETerrainType.Sea;
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
	
	public void mClearFog(int pX, int pY, double pDistance)
	{
		int vDistance = (int) pDistance;
		for(int vY = pY - vDistance; vY <= pY + vDistance; vY++)
		{
			for (int vX = pX - vDistance; vX <= pX + vDistance; vX++)
			{
				if
				(
					(vX > - 1) && (vX < this.aFog.get(0).size())
					&&
					(vY > - 1) && (vY < this.aFog.size())
				)
				{					
					this.aFog.get(vY).set(vX, 0.0);
				}
			}
		}
	}
	
	public void mClearFog2(int pX, int pY, double pDistance)
	{
		int vDistance = (int) pDistance;		
		for(int vYIndex = pY - vDistance; vYIndex <= pY + vDistance; vYIndex++)
		{
			for(int vXIndex = pX - vDistance; vXIndex <= pX + vDistance; vXIndex++)
			{
				if
				(
					(vXIndex > - 1) && (vXIndex < this.aFog.get(0).size())
					&&
					(vYIndex > - 1) && (vYIndex < this.aFog.size())
				)
				{
					Point2D vHero = new Point2D(pX,pY);
					Point2D vCell = new Point2D(vXIndex,vYIndex);
					double vDistanceCell = vHero.distance(vCell);					
					if(vDistanceCell < pDistance)
					{
						double vAlpha = vDistanceCell / pDistance;
						if(this.aFog.get(vYIndex).get(vXIndex) > vAlpha)
						{
							this.aFog.get(vYIndex).set(vXIndex, vAlpha);
						}
					}
				}
			}
		}		
	}
	
	public void mDraw(GraphicsContext pGraphicsContext)
	{
		double vX = this.aImage.getWidth() / this.aTileWidth;
		for(int vYIndex = 0; vYIndex < this.aMap.size(); vYIndex++)
		{
			for(int vXIndex = 0; vXIndex < this.aMap.get(0).size(); vXIndex++)
			{
				int vValue = this.aMap.get(vYIndex).get(vXIndex);
				double vXFrom = (int) (vValue % vX - 1);
				double vYFrom = (int) (vValue / vX);
				pGraphicsContext.drawImage(this.aImage, vXFrom * this.aTileWidth, vYFrom * this.aTileHeight, this.aTileWidth, this.aTileHeight, vXIndex * this.aTileWidth, vYIndex * this.aTileHeight, this.aTileWidth, this.aTileHeight);
				pGraphicsContext.setFill(Color.rgb(0,0,0,this.aFog.get(vYIndex).get(vXIndex)));				
				pGraphicsContext.fillRect(vXIndex * this.aTileWidth, vYIndex * this.aTileHeight, this.aTileWidth, this.aTileHeight);
				//pGraphicsContext.setStroke(Color.rgb(255,0,0,this.aFog.get(vYIndex).get(vXIndex)));
				//pGraphicsContext.strokeRect(vXIndex * this.aTileWidth, vYIndex * this.aTileHeight, this.aTileWidth, this.aTileHeight);
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
