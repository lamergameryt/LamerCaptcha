package com.lamergameryt.lamercaptchatest;

import com.lamergameryt.lamercaptcha.Captcha;
import com.lamergameryt.lamercaptcha.noise.StraightLineNoiseProducer;
import com.lamergameryt.lamercaptcha.text.producer.DefaultTextProducer;
import com.lamergameryt.lamercaptcha.text.renderer.DefaultWordRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class CaptchaTest {
    public static void main(String[] args) throws IOException {
        Captcha captcha = new Captcha.Builder(150, 50)
                .addBackground()
                .addBorder()
                .addText(new DefaultTextProducer(6), new DefaultWordRenderer(Collections.singletonList(Color.BLACK)))
                .addNoise()
                .addNoise(new StraightLineNoiseProducer(Color.BLACK, 3))
                .build();

        ImageIO.write(captcha.getImage(), "PNG", new File("captcha.png"));
        System.out.println("The captcha text is: " + captcha.getAnswer());
    }
}
