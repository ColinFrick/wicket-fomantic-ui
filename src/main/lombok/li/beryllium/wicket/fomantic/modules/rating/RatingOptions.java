/*
 * The MIT License
 * Copyright (c) 2018 Colin Frick
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package li.beryllium.wicket.fomantic.modules.rating;

import li.beryllium.wicket.fomantic.FomanticSize;
import lombok.Builder;
import lombok.Data;
import org.apache.wicket.model.IDetachable;

import javax.annotation.Nonnull;

/**
 * RatingOptions
 * <p/>
 * Contains all available options for the {@link Rating} component
 */
@Data
@Builder
public class RatingOptions implements IDetachable {

    private static final long serialVersionUID = 5913043054855375783L;

    /**
     * The maximum rating
     */
    @SuppressWarnings("UnusedAssignment")
    @Builder.Default
    private int maxRating = 4;

    /**
     * Type of the rating display
     */
    @SuppressWarnings("UnusedAssignment")
    @Builder.Default
    @Nonnull
    private RatingType type = RatingType.DEFAULT;

    /**
     * When clearable is set to true you can clear the rating by clicking on the current start rating.
     */
    private boolean clearable;

    /**
     * Size of the rating
     */
    private FomanticSize size;

    @Override
    public void detach() {
        // Noop
    }
}