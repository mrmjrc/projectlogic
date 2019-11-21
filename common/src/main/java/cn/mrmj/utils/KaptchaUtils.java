package cn.mrmj.utils;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Properties;

/**
 * create by: mrmj
 * description: 图片验证码工具
 * create time: 2019/11/21 14:21
 */
public class KaptchaUtils {

	public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";
	private static Properties props = new Properties();
	private static Producer kaptchaProducer = null;

	static {
		ImageIO.setUseCache(false);
		// 设置宽和高。
		props.put(Constants.KAPTCHA_IMAGE_WIDTH, "200");
		props.put(Constants.KAPTCHA_IMAGE_HEIGHT, "60");
		// kaptcha.border：是否显示边框。
		props.put(Constants.KAPTCHA_BORDER, "no");
		// kaptcha.textproducer.font.color：字体颜色
		props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "pink");
		// kaptcha.textproducer.char.space：字符间距
		props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "5");
		// 设置字体。
		props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "40");
		// this.props.put(Constants.KAPTCHA_NOISE_COLOR, "");
		// 更多的属性设置可以在com.google.code.kaptcha.Constants类中找到。

		Config config = new Config(props);
		kaptchaProducer = config.getProducerImpl();
	}

	/**
	 * create by: mrmj
	 * description: 随机生产验证码
	 * create time: 2019/11/21 14:23
	 */
	public static String createText() throws Exception {
		return kaptchaProducer.createText();
	}

	/**
	 * create by: mrmj
	 * description: 生产图片
	 * create time: 2019/11/21 14:23
	 */
	public static BufferedImage createImage(String text) throws Exception {
		return kaptchaProducer.createImage(text);
	}

	public static void main(String[] args) throws Exception {
		String text = createText();
		System.out.println(text);
		BufferedImage bi = createImage(text);
		ImageIO.write(bi, CAPTCHA_IMAGE_FORMAT, new File("E://1.jpeg"));
	}

}
