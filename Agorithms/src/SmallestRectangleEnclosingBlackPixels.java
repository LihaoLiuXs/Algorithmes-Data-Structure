
public class SmallestRectangleEnclosingBlackPixels {

	public static void main(String[] args) { //Lihao
		//An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
		//The black pixels are connected, i.e., there is only one black region. 
		//Pixels are connected horizontally and vertically. 
		//Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
		char[][] image = {{'0','0','1','0'},{'0','1','1','0'},{'0','1','0','0'}};
		System.out.println(minArea(image, 0, 2)); 
	}
    public static int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) {
            return 0;
        }
        int leftEdge = leftBound(image, 0, y, true);
        int rightEdge = rightBound(image, y, image[0].length - 1, true);
        int upperEdge = leftBound(image, 0, x, false);
        int lowerEdge = rightBound(image, x, image.length - 1, false);
        //System.out.println(leftEdge + " " + rightEdge + " "+ upperEdge + " " + lowerEdge);
        return (rightEdge - leftEdge + 1) * (lowerEdge - upperEdge + 1); 
    }
    
    private static int leftBound(char[][] image, int left, int right, boolean horizantal) {
        while (left <= right) {
            int mid = (right - left)/2 + left; 
            if (hasBlack(image, mid, horizantal)) right = mid - 1;
            else left = mid + 1;
        }
        return left; 
    }
    private static int rightBound(char[][] image, int left, int right, boolean horizantal) {
        while (left <= right) {
            int mid = (right - left)/2 + left;
            if (hasBlack(image, mid, horizantal)) left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }
    private static boolean hasBlack(char[][] image, int index, boolean horizantal) {
        if (horizantal) {
            for (int i = 0; i < image.length; i++) {
                if (image[i][index] == '1') return true; 
            }
        }
        else {
            for (int i = 0; i < image[0].length; i++) {
                if (image[index][i] == '1') return true; 
            }
        }
        return false; 
    }
}
