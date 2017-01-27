/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.imageio.ImageIO;

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
	
	public static void SaveBufferedImage(BufferedImage img, String path) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "jpg", baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		File upload = new File(path);
		upload.getParentFile().mkdirs();
		Files.copy(is, upload.toPath());
		is.close();
		baos.close();
	}
}
