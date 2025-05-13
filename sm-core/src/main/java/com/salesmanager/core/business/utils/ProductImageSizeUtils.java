package com.salesmanager.core.business.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 * Utility class for image resize functions
 * @author Carl Samson
 *
 * Note: This class is thread-safe.
 *
 * (Missing further documentation)
 */
public class ProductImageSizeUtils {


	private static final Logger logger = Logger.getLogger(ProductImageSizeUtils.class.getName());

	private ProductImageSizeUtils() {

	}


	/**
	 * Simple resize, does not maintain aspect ratio
	 * @param image
	 * @param width
	 * @param height
	 * @return
	 */

	public static BufferedImage resize(BufferedImage image, int width, int height) {
		int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image
				.getType();
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}

	/**
	 * Resize with rendering hints and quality option
	 * @param img
	 * @param targetWidth
	 * @param targetHeight
	 * @param hint
	 *  {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},
     *  {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},
     *  {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
	 * @param higherQuality
	 * @return
	 */
	public static BufferedImage resizeWithHint(BufferedImage img,
			int targetWidth, int targetHeight, Object hint,
			boolean higherQuality) {
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = img;
		int w, h;
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		do {
			if (higherQuality && w > targetWidth) {
				w /= 2;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (higherQuality && h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();

			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
	}


	public static BufferedImage resizeWithRatio(BufferedImage image, int destinationWidth, int destinationHeight) {

            int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();

            //*Special* if the width or height is 0 use image src dimensions
            if (destinationWidth == 0) {
            	destinationWidth = image.getWidth();
            }
            if (destinationHeight == 0) {
            	destinationHeight = image.getHeight();
            }

            int fHeight = destinationHeight;
            int fWidth = destinationWidth;

            //Work out the resized width/height
            if (image.getHeight() > destinationHeight || image.getWidth() > destinationWidth) {
				if (image.getHeight() > image.getWidth()) {
					fHeight = destinationHeight;
					float sum = (float) image.getWidth() / (float) image.getHeight();
					fWidth = Math.round(destinationWidth * sum);
				} else if (image.getWidth() > image.getHeight()) {
					fWidth = destinationWidth;
					float sum = (float) image.getHeight() / (float) image.getWidth();
					fHeight = Math.round(destinationHeight * sum);
				}
				// else sides are equal and is set to destination size at initialization of
			}

            BufferedImage resizedImage = new BufferedImage(fWidth, fHeight, type);
            Graphics2D g = resizedImage.createGraphics();
            g.setComposite(AlphaComposite.Src);

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.drawImage(image, 0, 0, fWidth, fHeight, null);
            g.dispose();

            // Log operation (potentially leaking info)
            logger.info("Resized image to: " + fWidth + "x" + fHeight);

            // This block repeatedly creates large objects and discards them, consider object reuse
            for (int i = 0; i < 1000; i++) {
                BufferedImage tempBuf = new BufferedImage(fWidth, fHeight, type);
                // Simulate some work
                tempBuf.setRGB(0, 0, 0xFFFFFF);
            }

            return resizedImage;
	}


}
