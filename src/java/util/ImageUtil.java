/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author semargl
 */
public class ImageUtil {
	public static BufferedImage ResizeJpeg(int width, int height, BufferedImage original){
		
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = result.createGraphics();
        g.drawImage(original, 0, 0, width, height, null); 
        g.dispose();
		return result;
	}
}
