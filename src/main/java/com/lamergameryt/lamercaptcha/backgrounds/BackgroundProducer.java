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

import java.awt.image.BufferedImage;

/**
 * Produces a background for the captcha.
 *
 * @author Harsh Patil
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
public interface BackgroundProducer {
    /**
     * Add the background to the given image.
     *
     * @param image The image onto which the background will be rendered.
     * @return The image with the background rendered.
     */
    @SuppressWarnings("unused")
    BufferedImage addBackground(BufferedImage image);

    BufferedImage getBackground(int width, int height);
}
