// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------read, print, function test----------------");
		print(tinypic);
		System.out.println("----------------------------------------------------------");
		// Creates an image which will be the result of various 
		// image processing operations:
		
		Color[][] imageOut;
		// Tests the horizontal flipping of an image:
		imageOut = flippedHorizontally(tinypic);
		System.out.println();
		System.out.println("----------------------------------------------------------");
		System.out.println("------------flippedHorizontally, function test------------");
		print(imageOut);
		System.out.println("----------------------------------------------------------");
		
		Color[][] imageOut2;
		// Tests the vertically flipping of an image:
		imageOut2 = flippedVertically(tinypic);
		System.out.println();
		System.out.println("----------------------------------------------------------");
		System.out.println("-------------flippedVertically, function test-------------");
		print(imageOut2);
		System.out.println("----------------------------------------------------------");
		
		/*luminance
		System.out.println("----------------------------------------------------------");
		System.out.println("-----------------luminance, function test-----------------");
		Color check = new Color(255,0,167);
		print(check);
		print(luminance(check));
		System.out.println("");
		System.out.println("----------------------------------------------------------");
		*/
		Color[][] imageOut3;
		// Tests the vertically flipping of an image:
		imageOut3 = grayScaled(tinypic);
		System.out.println();
		System.out.println("----------------------------------------------------------");
		System.out.println("-----------------grayScaled, function test-----------------");
		print(imageOut3);
		System.out.println("----------------------------------------------------------");

		Color[][] imageOut4;
		imageOut4 = scaled(tinypic, 3, 5);
		System.out.println();
		System.out.println("----------------------------------------------------------");
		System.out.println("------------------Scaled, function test------------------");
		print(imageOut4);
		System.out.println("----------------------------------------------------------");

		/*System.out.println("----------------------------------------------------------");
		System.out.println("----------------blend Color, function test----------------");
		Color checkkk;
		Color first = new Color(100, 40, 100);
		Color sec = new Color(200, 20, 40);
		checkkk = blend(first,sec,0.25);
		print(checkkk);
		System.out.println("");
		System.out.println("----------------------------------------------------------");*/

		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				image[i][j] = new Color(in.readInt(),in.readInt(),in.readInt());
			}
		}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				print(image[i][j]);
			}
			System.out.println("");
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		Color[][] newImage = new Color[image.length][image[0].length];
		int indexCols = 0;
		for (int i = 0; i < image.length; i++) {
			for (int j = image[0].length - 1; j >= 0; j--) {
				newImage[i][indexCols] = image[i][j];
				indexCols++;
			}
			indexCols = 0;
		}
		return newImage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		Color[][] newImage = new Color[image.length][image[0].length];
		int indexRow = 0;
		for (int i = image.length - 1; i >= 0; i--) {
			for (int j = 0; j < image[0].length; j++) {
				newImage[indexRow][j] = image[i][j];
			}
			indexRow++;
		}
		return newImage;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		int lum = (int)((pixel.getRed() * 0.299) + (pixel.getGreen() * 0.587) + (pixel.getBlue() * 0.114));
		Color grayedColor = new Color(lum,lum,lum);
		return grayedColor;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		Color[][] newImage = new Color[image.length][image[0].length];
		for (int i = 0; i < newImage.length; i++) {
			for (int j = 0; j < newImage[0].length; j++) {
				newImage[i][j] = luminance(image[i][j]);
			}
		}
		return newImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {//width = col. hight = raw.
		Color[][] newImage = new Color[height][width];
		for (int i = 0; i < newImage.length; i++) {
			for (int j = 0; j < newImage[i].length; j++) {
				newImage[i][j] = image[i * image.length/height][j* image[0].length/width]; 
			}
		}
		return newImage;
	}
	

	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int red = (int)((c1.getRed() * alpha) + (c2.getRed() * (1-alpha)));
		int green = (int)((c1.getGreen() * alpha) + (c2.getGreen() * (1-alpha)));
		int blue = (int)((c1.getBlue() * alpha) + (c2.getBlue() * (1-alpha)));
		Color blended = new Color(red, green, blue);
		return blended;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		Color[][] blendedImage = new Color[image1.length][image1[0].length];
		for (int i = 0; i < blendedImage.length; i++) {
			for (int j = 0; j < blendedImage[0].length; j++) {
				blendedImage[i][j] = blend(image1[i][j],image2[i][j],alpha);
			}
		}
		return blendedImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		Color[][] scaledTarget = new Color[source.length][source[0].length];
		int alpha;
		if ((source.length!=target.length) || (source[0].length!=target[0].length)) {
			scaledTarget = scaled(target, source[0].length, source.length);
		}
		else{
			scaledTarget = target;
		}
		for (int i = 0; i <= n; i++) {
			alpha = (n-i)/n;
			Runigram.display(blend(source, scaledTarget, alpha));
			StdDraw.pause(500); 
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

