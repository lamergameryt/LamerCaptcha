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

package com.lamergameryt.lamercaptcha.backgrounds;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public final class FlatColorBackgroundProducer implements BackgroundProducer {
    private final Color _color;

    public FlatColorBackgroundProducer() {
        this(Color.GRAY);
    }

    public FlatColorBackgroundProducer(Color color) {
        _color = color;
    }

    @Override
    public BufferedImage addBackground(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        return this.getBackground(width, height);
    }

    @Override
    public BufferedImage getBackground(int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = img.createGraphics();
        graphics.setPaint(_color);
        graphics.fill(new Rectangle2D.Double(0, 0, width, height));
        graphics.drawImage(img, 0, 0, null);
        graphics.dispose();

        return img;
    }
}
