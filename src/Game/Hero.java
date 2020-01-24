package Game;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Hero
{
	private Image aImage;
	private double aX;
	private double aY;
	private double aTileWidth;
	private double aTileHeight;
	private boolean aMoving;
	private GameMap aGameMap;
	private EDirection aDirection;
	private EHeroColor aColor;
		
	public Hero(String pFileName, double pTileWidth, double pTileHeight, GameMap pGameMap)
	{
		this.aImage = new Image(pFileName);
		this.aTileWidth = pTileWidth;
		this.aTileHeight = pTileHeight;
		this.aDirection = EDirection.Down;
		this.aColor = EHeroColor.Green;
		this.aMoving = false;
		this.aGameMap = pGameMap; 
	}
	
	public double mX()
	{
		return this.aX;
	}
	
	public void mX(double pX)
	{
		this.aX = pX;
	}
	
	public double mY()
	{
		return this.aY;
	}
	
	public void mY(double pY)
	{
		this.aY = pY;
	}
	
	public EDirection mDirection()
	{
		return this.aDirection;
	}
	
	public EHeroColor mColor()
	{
		return this.aColor;
	}
	
	public void mColor(EHeroColor pColor)
	{
		this.aColor = pColor;
	}
	
	public void mDirection(EDirection pDirection)
	{
		this.aDirection = pDirection;
	}
	
	public void mUpdate(double pDeltaTime)
	{
		if(this.aMoving)
		{
			switch(this.aDirection)
			{
				case Up:
				{
					if(this.aY > 0)
					{
						this.aY--;
					}
				}break;
				case Right:
				{
					if(this.aX < this.aGameMap.mWidth())
					{
						
					}
				}break;
				case Down:
				{
					this.aDirection = EDirection.Down;
				}
				case Left:
				{
					this.aDirection = EDirection.Left;
				}
			}
		}
	}
	
	public void mDraw(GraphicsContext pGraphicsContext)
	{
		double vHorisontalTiles = this.aImage.getWidth() / this.aTileWidth;
		double vXOffset = (this.aColor.ordinal() * 3) % vHorisontalTiles * this.aTileWidth;
		double vYOffset = (int)(((this.aColor.ordinal() * 3) / vHorisontalTiles) + this.aDirection.ordinal()) * aTileHeight;
		pGraphicsContext.drawImage(this.aImage, vXOffset, vYOffset, this.aTileWidth, this.aTileHeight, this.aX * this.aTileWidth, this.aY * this.aTileHeight, this.aTileWidth, this.aTileHeight);
	}

	public void mKeyPress(KeyEvent e) 
	{
		switch(e.getCode())
		{
			case UP:
			{
				this.aDirection = EDirection.Up;
				this.aMoving = true;
			}break;
			case RIGHT:
			{
				this.aDirection = EDirection.Right;
				this.aMoving = true;
			}break;
			case DOWN:
			{
				this.aDirection = EDirection.Down;
				this.aMoving = true;
			}
			case LEFT:
			{
				this.aDirection = EDirection.Left;
				this.aMoving = true;
			}
		}
	}

	public void mKeyRelease(KeyEvent e) 
	{
		this.aMoving = false;
	}
}