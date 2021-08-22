/**
 *  RGBImage.java represents a 2D color of RGB pixels - image. 
 *
 * @author Yarin Hazan
 * @version 23/4/2021
 */

public class RGBImage
{

    //this array store the pixels of the image
    private RGBColor[][] _image;

    /**
     * Construct a new black RGBImage by number of rows and columns.
     * All pixels in the image should be black (red = green = blue = 0).
     * Raises an error if the given rows or cols are not positive.
     * @param rows The width of the new image.
     * @param cols The height of the new image.
     */
    public RGBImage(int rows, int cols)
    {
        _image = new RGBColor[rows][cols];
        for(int r=0; r < rows; r++){
            for(int c=0; c < cols; c++){
                _image[r][c] = new RGBColor();

            }        
        }

    }

    /**
     * Construct a new RGBImage identical to the given array of pixels.
     * Assumed that the array of pixels is not null and that the rows and columns in it are positive.
     * @param pixels The pixels of the new image.
     */
    public RGBImage(RGBColor[][] pixels)
    {
        _image=new RGBColor[pixels.length][pixels[0] .length];
        for(int r=0; r < pixels.length; r++){
            for(int c=0; c < pixels[0].length; c++){
                _image[r][c] = new RGBColor(pixels[r][c]);

            }        
        }

    }

    /**
     * Copy constructor Creates a new RGBImage identical to the given image
     * Assumed that the array of pixels is not null and that the rows and columns in it are positive.
     * @param other The image to copy.
     */
    public RGBImage(RGBImage other) 
    {
        _image = new RGBColor[other._image.length][other._image[0].length];

        for(int r=0; r < other._image.length; r++){
            for(int c=0; c < other._image[0].length; c++){
                _image[r][c] = new RGBColor(other._image[r][c]);

            }        
        }

    }

    /**
     * This method gets the height of the image in pixels.
     * @return The height of the image.
     */
    public int getHeight()
    {
        return _image.length; 
    }

    /**
     * This method gets the width of the image in pixels. 
     * @return The width of the image.
     */
    public int getWidth()
    {
        return _image[0].length;
    }

    /**
     * Gets the pixel at the given coordinates.
     * Returns a black RGBColor if the given coordinates are outside the image.
     * @param row The row of the pixel to get.
     * @param col The column of the pixel to get.
     * @return The pixel at the given coordinates.
     */
    public RGBColor getPixel (int row, int col)
    {
        if(row > getHeight() || col > getWidth() || row < 0|| col < 0)
            return new RGBColor();
        else
            return new RGBColor(_image[row][col]);
    }

    /**
     * Sets the pixel at the given coordinates.
     * Raises an error if the given coordinates are outside the image. 
     * @param row The row of the pixel to set.
     * @param col The column of the pixel to set.
     * @param pixel Contains the RGB values to set at the given coordinates.
     */
    public void setPixel (int row, int col, RGBColor pixel)
    {
        if(row<=getWidth() && col<= getWidth()) 
            _image[row][col]=new RGBColor(pixel);  

    }

    /**
     * Return true if the images are equal,have the same height and width,
     * and for each r and c in the legal ranges,
     * the Color in row i column c are equal.
     * @param other the other RGBImage to compare to
     * @return true if the images are equal
     */
    public boolean equals (RGBImage other)
    {
        if(other == null)
            return false;

        if(other._image.length!= this._image.length || other._image[0].length != this._image[0].length)
            return false;

        for(int r=0; r < other._image.length; r++)
            for(int c=0; c < other._image[r] .length; c++)
                if(!_image[r][c].equals(other._image[r][c]))
                    return false;   
        return true;
    }

    /**
     * Flips the image around the vertical axis.
     * The first column becomes the last column, The second becomes second to last, etc.
     */
    public void flipHorizontal ()
    {
        RGBImage newImage = new RGBImage(this._image); // make a new auxiliary image 
        for (int r = 0 ; r<getHeight() ; r++)
            for(int c = 0 ; c<getWidth() ; c++)
            {
                newImage._image[r][getWidth() -c-1]=_image[r][c]; //move the coordinates of columns in _image to the on the opposite side on newImage (auxiliary image )
            }
        _image = newImage._image; // move the change to the image

    }

    /**
     * Flips the image around the horizontal axis.
     * The first row becomes the last row, The second becomes second to last, etc.
     */

    public void flipVertical ()
    {
        RGBImage newImage = new RGBImage(this._image); // make a new auxiliary image 
        for (int r = 0 ; r<getHeight() ; r++)
            for(int c = 0 ; c<getWidth() ; c++)
            {
                newImage._image[getHeight() -r-1][c]=_image[r][c]; //move the coordinates of rows in _image to the on the opposite side on newImage (auxiliary image )
            }
        _image = newImage._image; // move the change to the image

    }

    /**
     * Method that inverts the color of all pixels in this image,
     * by replacing each RGB value with its complement to 255.
     * For example, RGB values of [0,1,2] would be changed to [255,254,253].
     */

    public void invertColors()
    {
        for(int r=0; r < _image.length; r++){
            for(int c=0; c < _image[0].length; c++){
                _image[r][c].invert(); // replacing each RGB value with its complement to 255

            }        
        }

    }

    /**
     * rotate the image 90 degrees clockwise.
     * Note that this may change the dimensions of the image.
     */

    public void rotateClockwise()
    {
        RGBColor[][] newImage= new RGBColor[getWidth()][getHeight()];
        for(int r=0; r < getHeight(); r++){
            for(int c=0; c < getWidth(); c++){
                newImage[c][getHeight()-1-r] = _image[r][c]; // replacing the row to colum and colum to the coordinate to the number of rows -1 - r                      
            }        
        }
        _image=newImage;

    }

    /**
     * Rotates the image 90 degrees counter-clockwise.
     * Note that this may change the dimensions of the image.
     */

    public void rotateCounterClockwise()
    {
        RGBColor[][] newImage= new RGBColor[getWidth()][getHeight()];
        for(int r=0; r < getHeight(); r++){
            for(int c=0; c < getWidth(); c++){
                newImage[getWidth()-1-c][r] = new RGBColor(_image[r][c]); // replacing the row to the coordinate to the number of columns -1 - c and colum to r                            
            }        
        }
        _image=newImage;

    }

    /**
     * Shifts the image left or right, according to the given offset.
     * Column 0 is moved into column offset, column 1 is moved into column offset+1, etc.
     * offset may be negative (or 0).
     * Any column that is shifted in from outside the image should be all black.
     * @param offset Offset to shift the image by.
     */

    public void shiftCol (int offset)
    {        

        if(Math.abs(offset)>getWidth()) //Do nothing when offset bigger than number of columns
            return;

        RGBColor[][] newImage= new RGBColor[getHeight()][getWidth()]; // make a new auxiliary image 

        for(int r=0; r < getHeight(); r++){
            for(int c=0; c < getWidth(); c++){
                newImage[r][c] = new RGBColor(); //make a new black RGBImage to base                              
            }        
        }

        for(int r=0; r < getHeight(); r++){
            for(int c=0; c < getWidth(); c++){
                if(c+offset > -1 && c + offset<getWidth())
                    newImage[r][c+offset] = _image[r][c]; // move to the new image (base) the pixel to the coordinates that the same row and same colum+offset

            }        
        }
        _image=newImage;

    }

    /**
     * Shifts the image down or up, according to the given offset.
     * Row 0 is moved into row offset, row 1 is moved into row offset+1, etc.
     * offset may be negative (or 0).
     * Any row that is shifted in from outside the image should be all black.
     * @param offset Offset to shift the image by.
     */

    public void shiftRow (int offset)
    {

        if(Math.abs(offset)>_image.length)  //Do nothing when offset bigger than number of columns
            return;

        RGBColor[][] newImage= new RGBColor[getHeight()][getWidth()]; // make a new auxiliary image 

        for(int r=0; r < getHeight(); r++){
            for(int c=0; c < getWidth(); c++){
                newImage[r][c] = new RGBColor(); //make a new black RGBImage to base                              
            }        
        }

        for(int r=0; r < getHeight(); r++){
            for(int c=0; c < getWidth(); c++){
                if(r+offset > -1 && r + offset<getHeight())
                    newImage[r+offset][c] = _image[r][c]; // move to the new image (base) the pixel to the coordinates that the same row+offset and same colum

            }        
        }
        _image=newImage;

    }

    /**
     * Returns the grayscale representation of the image.
     * The grayscale representation of each pixel is calculated as defined in the API of RGBColor.
     * @return A 2d array of floats representing the image in grayscale values.
     */
    public double[][] toGrayscaleArray(){

        double[][] grayScale = new double[getHeight()][getWidth()]; // auxiliary image

        for(int i=0;i<getHeight();i++)
            for(int j=0;j<getWidth();j++)
                grayScale[i][j]= _image[i][j].convertToGrayscale(); // move the gray representation of each pixel to an auxiliary variable image
        return grayScale;
    }

    /**
     * This method returns a string of characters representing the image .
     * The format of the string should be: ( 0,3,3) (1,2,3) (1,1,2) (1,0,1)
     *                                     ( 1,3,4) (2,2,4) (2,1,3) (2,0,2)
     *                                     ( 2,3,5) (2,2,4) (2,1,3) (2,0,2)
     * Each line in the array is in a separate line with a single space between the pixels. at the end Line no space.
     * Each pixel is displayed in a triangular character string of separated integers In commas inside the parentheses round.
     * @return a String representation of the triangle
     */
    public String toString(){
        String sImage = "";;

        for(int i=0;i<getHeight();i++){
            for(int j=0;j<getWidth();j++){
                if(!(j==getWidth()-1))
                    sImage+= _image[i][j].toString()+" " ;
                else 
                    sImage+= _image[i][j].toString();
            }
            sImage+="\n" ;

        }

        return sImage;    

    }

    /**
     * returns a copy of the array of pixels in this image. 
     * @return An array of the pixels in this image.
     */
    public RGBColor[][] toRGBColorArray(){

        RGBColor[][] newImage = new RGBColor[getHeight()][getWidth()];

        for(int i=0;i<getHeight();i++)
            for(int j=0;j<getWidth();j++)
                newImage[i][j]=new RGBColor(_image[i][j]);

        return newImage;
    }

}

