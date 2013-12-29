import java.awt.Color;

///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Main Class File:  PortablePixelMapper.java
//File:             Shape.java
//Semester:         CS302 Fall 2013
//
//Author:           Minh Bui
//CS Login:         minh
//Lecturer's Name:  Shreed Hardika
//Lab Section:      301
//
//PAIR PROGRAMMERS COMPLETE THIS SECTION
//Pair Partner:     null
//CS Login:         null
//Lecturer's Name:  null
//Lab Section:      null
//
//STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
//Credits:          null
////////////////////////////80 columns wide //////////////////////////////////

/**
 * The class Circle is responsible to handle the drawing of a filled circle.
 * 
 * @author Minh Bui
 * 
 */
public class Circle extends Shape {
	double radiusP = 0;

	public Circle(Color color, double xPerc, double yPerc, double r) {
		super(color, xPerc, yPerc);
		radiusP = r;
	}

	public void drawTo(PPM PPM) {
		// Must scale x,y to final PPM dimensions
		int c = (int) (xRatio * PPM.getWidth()); // get x-coord
		int r = (int) (yRatio * PPM.getHeight()); // get y-coord
		int radius = (int) (radiusP * PPM.getWidth()); // Radius of R.
		
		// Error of each pixel when a curve cross a pixel.
		int error = 1 - radius;
		int errorY = 1;
		int errorX = -2 * radius;

		// Increment variables
		int x = radius, y = 0;

		// Check to see if the boundary of the circle is outside the image
		// resolution.
		int upR = r + radius;
		int dwR = r - radius;
		int ltR = c + radius;
		int rtR = c - radius;
		if (upR >= PPM.getHeight()) {
			upR = PPM.getHeight() - 1;
		}
		if (dwR < 0) {
			dwR = 0;
		}
		if (ltR >= PPM.getWidth()) {
			ltR = PPM.getWidth() - 1;
		}
		if (rtR < 0) {
			rtR = 0;
		}

		// Draw the four points which are divided by 90 degree.
		PPM.setPixel(upR, c, color);
		PPM.setPixel(dwR, c, color);
		LinearFiller.filler(PPM, c, dwR, c, upR, color);
		PPM.setPixel(r, ltR, color);
		PPM.setPixel(r, rtR, color);
		LinearFiller.filler(PPM, rtR, r, ltR, r, color);

		while (y < x) {
			if (error > 0) {
				x--;
				errorX += 2;
				error += errorX;
			}
			y++;
			errorY += 2;
			error += errorY;

			// Check for pixels that go out of the image dimension.
			int increment1 = r + y;
			int increment2 = r - y;
			int increment3 = c + x;
			int increment4 = c - x;
			int increment5 = r + x;
			int increment6 = r - x;
			int increment7 = c + y;
			int increment8 = c - y;
			if (increment1 >= PPM.getHeight()) {
				increment1 = PPM.getHeight() - 1;
			}
			if (increment2 < 0) {
				increment2 = 0;
			}
			if (increment3 >= PPM.getWidth()) {
				increment3 = PPM.getWidth() - 1;
			}
			if (increment4 < 0) {
				increment4 = 0;
			}
			if (increment5 >= PPM.getHeight()) {
				increment5 = PPM.getHeight() - 1;
			}
			if (increment6 < 0) {
				increment6 = 0;
			}
			if (increment7 >= PPM.getWidth()) {
				increment7 = PPM.getWidth() - 1;
			}
			if (increment8 < 0) {
				increment8 = 0;
			}

			// Draw each 8 points on the boundary of the circle based on the
			// errors. Because the circle is highly symmetry, Each point on the
			// circle is symmetric to another point through the center of the
			// circle. Use LinearFiller class to fill up the gap between two
			// points to make a filled circle.

			PPM.setPixel(increment1, increment3, color);
			PPM.setPixel(increment1, increment4, color);
			LinearFiller.filler(PPM, increment4, increment1, increment3,
					increment1, color);
			PPM.setPixel(increment2, increment3, color);
			PPM.setPixel(increment2, increment4, color);
			LinearFiller.filler(PPM, increment4, increment2, increment3,
					increment2, color);
			PPM.setPixel(increment5, increment7, color);
			PPM.setPixel(increment5, increment8, color);
			LinearFiller.filler(PPM, increment8, increment5, increment7,
					increment5, color);
			PPM.setPixel(increment6, increment7, color);
			PPM.setPixel(increment6, increment8, color);
			LinearFiller.filler(PPM, increment8, increment6, increment7,
					increment6, color);
		}
		
		
	}

}
