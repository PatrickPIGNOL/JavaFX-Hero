package Game;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Game 
{	
	private Hero aHero;
	private GameMap aGameMap;
	
	public Game(List<List<Integer>> pMap, List<List<Integer>> pLayer)
	{
		this.aGameMap = new GameMap("tilesheet.png", 32.0, 32.0, pMap, pLayer);
		this.aHero = new Hero("characters.png", 32.0, 32.0, this.aGameMap);
	}
 
	public void mMouseMove(MouseEvent e)
	{
		this.mOnMouseMoved(e);
	}
	
	private void mOnMouseMoved(MouseEvent e)
	{
		this.aGameMap.mMouseMove(e);
	}
	
	public void mLoad()
	{
		
	}
	
	public void mDraw(GraphicsContext pGraphicsContext)
	{
		this.mOnDraw(pGraphicsContext);
	}
	
	private void mOnDraw(GraphicsContext pGraphicsContext)
	{
		this.aGameMap.mDraw(pGraphicsContext);
		this.aHero.mDraw(pGraphicsContext);		
	}
	
	public static void mDrawText(GraphicsContext pGraphicsContext, double pX, double pY, Font pFont, String pText, double pLineWidth, Paint pFillColor, Paint pStrokeColor)
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

	public void mKeyPress(KeyEvent e) 
	{
		this.mOnKeyPressed(e);
	}
	
	private void mOnKeyPressed(KeyEvent e)
	{
		this.aHero.mKeyPress(e);
	}

	public void mKeyRelease(KeyEvent e) 
	{
		this.aHero.mKeyRelease(e);
	}
}
