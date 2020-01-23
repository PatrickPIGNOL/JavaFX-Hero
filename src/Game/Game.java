package Game;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Game 
{
	private double aMouseX;
	private double aMouseY;
	
	private double aTileWidth;
	private double aTileHeight;
	private String aFileName;
	private Image aImage;
	//private Map aMap;
	private List<List<Integer>> aMap;
	
	public Game(String pFileName, double pTileWidth, double pTileHeight, List<List<Integer>> pMap)
	{
		this.aFileName = pFileName;
		this.aTileWidth = pTileWidth;
		this.aTileHeight = pTileHeight;
		this.aMap = pMap;
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
	
	public void mLoad()
	{
		System.out.println("Game:Chargement des textures...");
		this.aImage = new Image(this.aFileName);
		System.out.println("Game:Chargement des textures terminé...");
	}
	
	public void mDraw(GraphicsContext pGraphicsContext)
	{
		this.mOnDraw(pGraphicsContext);
	}
	
	private void mOnDraw(GraphicsContext pGraphicsContext)
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
			String vTileType = "";
			switch(this.aMap.get(vYIndex).get(vXIndex))
			{
				case 1:
				{
					vTileType = "Cold Lava";
				}break;
				case 10:
				case 11:
				case 12:
				{
					vTileType = "Grass";
				}break;	
				case 13:
				case 14:
				case 15:
				{
					vTileType = "Sand";
				}break;				
				case 19:
				case 20:
				case 21:
				{
					vTileType = "Water";
				}break;
				case 37:
				{
					vTileType = "Lava";
				}break;
				case 55:
				case 58:
				case 61:
				case 68:
				case 142:
				{
					vTileType = "Tree";
				}break;
				case 129:
				case 169:
				{
					vTileType = "Rocks";
				}break;
				
			}
			this.mDrawText(pGraphicsContext, 10.0, 20.0, vFont, "Tile(ID): " + vTileType + "(" + this.aMap.get(vYIndex).get(vXIndex) + ")", 0.0, Color.RED, Color.BLACK);
		}
		else
		{
			this.mDrawText(pGraphicsContext, 10.0, 20.0, vFont, "Out...", 0.0, Color.RED, Color.BLACK);
		}
	}
	
	private void mDrawText(GraphicsContext pGraphicsContext, double pX, double pY, Font pFont, String pText, double pLineWidth, Paint pFillColor, Paint pStrokeColor)
	{
		pGraphicsContext.setFill(pFillColor);
		pGraphicsContext.setFont(pFont);
		pGraphicsContext.fillText(pText, pX, pY);
		if(pLineWidth > 0.0)
		{
			pGraphicsContext.setStroke(pStrokeColor);
			pGraphicsContext.setLineWidth(pLineWidth);
		    pGraphicsContext.strokeText(pText, pX, pY);
		}
	}
}
