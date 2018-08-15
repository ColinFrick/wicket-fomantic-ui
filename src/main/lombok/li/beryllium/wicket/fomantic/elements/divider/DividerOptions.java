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
package li.beryllium.wicket.fomantic.elements.divider;

import lombok.Builder;
import lombok.Data;
import org.apache.wicket.model.IDetachable;

import javax.annotation.Nonnull;

/**
 * DividerOptions
 *
 * Contains all available options for the {@link Divider} component
 */
@Data
@Builder
public class DividerOptions implements IDetachable {

    private static final long serialVersionUID = 8244619090981096017L;

    /**
     * Type of the Divider
     *
     * @see DividerType
     */
    @SuppressWarnings("UnusedAssignment")
    @Builder.Default
    @Nonnull
    private DividerType type = DividerType.DEFAULT;

    /**
     * Dividers can have their colors inverted.
     */
    private boolean inverted;

    /**
     * A divider can be fitted, without any space above or below it.
     */
    private boolean fitted;

    /**
     * A hidden divider divides content without creating a dividing line
     */
    private boolean hidden;

    /**
     * A divider can provide greater margins to divide sections of content
     */
    private boolean section;

    /**
     * A divider can clear the contents above it
     */
    private boolean clearing;

    public void detach() {
        // Noop
    }
    
}
