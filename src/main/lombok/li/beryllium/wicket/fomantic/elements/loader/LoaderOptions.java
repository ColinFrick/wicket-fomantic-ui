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
package li.beryllium.wicket.fomantic.elements.loader;

import li.beryllium.wicket.fomantic.FomanticSize;
import lombok.Builder;
import lombok.Data;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Optional;

/**
 * LoaderOptions
 * <p/>
 * Contains all available options for the {@link Loader} components / behaviors
 */
@Data
@Builder
public class LoaderOptions implements IDetachable {

    private static final long serialVersionUID = 1L;

    /**
     * Loaders can appear inline centered with content
     */
    private boolean centered;

    /**
     * Loaders can have their colors inverted.
     */
    private boolean inverted;

    /**
     * Loaders can appear slow, normal or fast
     */
    @SuppressWarnings("UnusedAssignment")
    @Builder.Default
    @Nonnull
    private LoaderSpeed speed = LoaderSpeed.DEFAULT;

    /**
     * Loaders can have different sizes
     */
    private FomanticSize size;

    /**
     * A loader can contain text
     */
    private IModel<Serializable> contentModel;

    @Override
    public void detach() {
        Optional.ofNullable(contentModel)
                .ifPresent(IModel::detach);
    }
}