/*
 *  Copyright 2021 Harsh Patil (LamerGamerYT)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.lamergameryt.lamercaptcha;

import com.lamergameryt.lamercaptcha.backgrounds.TransparentBackgroundProducer;
import com.lamergameryt.lamercaptcha.gimpy.GimpyRenderer;
import com.lamergameryt.lamercaptcha.noise.NoiseProducer;
import com.lamergameryt.lamercaptcha.text.renderer.DefaultWordRenderer;
import com.lamergameryt.lamercaptcha.text.renderer.WordRenderer;
import com.lamergameryt.lamercaptcha.backgrounds.BackgroundProducer;
import com.lamergameryt.lamercaptcha.text.producer.TextProducer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Simple CAPTCHA bean intended to be used by Spring.
 *
 * @author Harsh Patil
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
@SuppressWarnings("unused")
public class CaptchaBean {
    private BackgroundProducer _bgProd = new TransparentBackgroundProducer();
    private TextProducer _txtProd;
    private NoiseProducer _noiseProd;
    private GimpyRenderer _gimpy;
    private boolean _addBorder = false;

    private String _answer = "";
    private BufferedImage _img;

    public CaptchaBean(int width, int height) {
        _img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    @SuppressWarnings({"DuplicatedCode", "SuspiciousNameCombination"})
    public CaptchaBean build() {
        // Add and render the text
        if (_txtProd != null) {
            _answer += _txtProd.getText();
            WordRenderer wr = new DefaultWordRenderer();
            wr.render(_answer, _img);
        }

        if (_noiseProd != null) {
            _noiseProd.makeNoise(_img);
        }

        if (_gimpy != null) {
            _gimpy.gimp(_img);
        }

        BufferedImage _bg = _bgProd.getBackground(_img.getWidth(), _img.getHeight());

        // Paint the main image over the background
        Graphics2D g = _bg.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g.drawImage(_img, null, null);

        // Add the border, if necessary
        if (_addBorder) {
            int width = _img.getWidth();
            int height = _img.getHeight();

            g.setColor(Color.BLACK);
            g.drawLine(0, 0, 0, width);
            g.drawLine(0, 0, width, 0);
            g.drawLine(0, height - 1, width, height - 1);
            g.drawLine(width - 1, height - 1, width - 1, 0);
        }
        _img = _bg;
        g.dispose();

        return this;
    }

    public boolean isCorrect(String answer) {
        return answer.equals(_answer);
    }

    public BufferedImage getImage() {
        return _img;
    }

    public BackgroundProducer getBgProd() {
        return _bgProd;
    }

    public void setBgProd(BackgroundProducer bgProd) {
        _bgProd = bgProd;
    }

    public TextProducer getTxtProd() {
        return _txtProd;
    }

    public void setTxtProd(TextProducer txtProd) {
        _txtProd = txtProd;
    }

    public NoiseProducer getNoiseProd() {
        return _noiseProd;
    }

    public void setNoiseProd(NoiseProducer noiseProd) {
        _noiseProd = noiseProd;
    }

    public GimpyRenderer getGimpy() {
        return _gimpy;
    }

    public void setGimpy(GimpyRenderer gimpy) {
        _gimpy = gimpy;
    }

    public boolean isAddBorder() {
        return _addBorder;
    }

    public void setAddBorder(boolean addBorder) {
        _addBorder = addBorder;
    }

    public String getAnswer() {
        return _answer;
    }

    public void setAnswer(String answer) {
        _answer = answer;
    }
}
