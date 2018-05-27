package puzzle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class ImageCtrl {
	private String picName;
	public ImageCtrl(String picName) {
		this.picName = picName;
	}
	
	public void deletePic() {
		File file = new File("image/" + picName + "/");
		if (file.isDirectory()) {
			String[] fileList = file.list();
			int fileNum = fileList.length;
            if (fileNum > 0) {
                for (int i = 0; i < fileNum; i++) {
                    File f = new File("image/" + picName + "/" +fileList[i]);
                    if(f.isFile()) {
                        f.delete();
                    }
                }
            }
		}
	}
	
	//ÇÐ¸îÍ¼Æ¬
	public void cutPic(int size, int row, int col) {
		File file = new File("image/" + picName + ".png");
		try {
			FileInputStream fileInput = new FileInputStream(file);
            BufferedImage bufImage = ImageIO.read(fileInput);
            fileInput.close();
            int picSize = (bufImage.getHeight() < bufImage.getWidth()) ? bufImage.getHeight() : bufImage.getWidth();
            size = picSize/size;
            
            for (int r = 0; r < row; r++) {
            	for (int c = 0; c < col; c++) {
            		int num = r * col + c + 1;
            		String cutPicPath = "image/" + picName + "/" + String.valueOf(num) + ".png";
            		File dir = new File("image/" + picName + "/");
            		if (!dir.exists()) {
            			dir.mkdirs();
            		}
            		BufferedImage smallImage = bufImage.getSubimage(c * size, r * size, size, size);
                    FileOutputStream fileOutput = new FileOutputStream(cutPicPath);
                    ImageIO.write(smallImage, "png", fileOutput);
                    fileOutput.close();
            		
            	}
            }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
