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

import li.beryllium.wicket.fomantic.FomanticConstants;
import li.beryllium.wicket.fomantic.resource.FomanticHeaderContributor;
import lombok.val;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import javax.annotation.Nonnull;
import java.util.StringJoiner;

/**
 * Loader
 * <p/>
 * A loader alerts a user to wait for an activity to complete. The loader component is always displayed inline with content
 *
 * @see <a href="https://fomantic-ui.com/elements/loader.html#inline">Fomantic UI Loader</a>
 */
public class Loader extends Panel {

    private final LoaderOptions options;

    /**
     * Constructor
     *
     * @param id Wicket ID
     */
    public Loader(@Nonnull String id) {
        this(id, LoaderOptions.builder().build());
    }

    /**
     * Constructor
     *
     * @param id      Wicket ID
     * @param options Options for the loader
     */
    public Loader(@Nonnull String id, @Nonnull LoaderOptions options) {
        super(id);
        this.options = options;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("text", options.getContentModel()));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        val cssClasses = new StringJoiner(" ")
                .add(FomanticConstants.BASE_CSS_CLASS)
                .add("active")
                .add("inline")
                .add("loader");

        if (options.getSize() != null) {
            cssClasses.add(options.getSize().getSizeName(false));
        }

        if (options.getContentModel() != null) {
            cssClasses.add("text");
        }

        if (options.isCentered()) {
            cssClasses.add("centered");
        }

        if (options.isInverted()) {
            cssClasses.add("inverted");
        }

        // Add loader speed class if type is not LoaderSpeed.DEFAULT
        if (!LoaderSpeed.DEFAULT.equals(options.getSpeed())) {
            cssClasses.add(options.getSpeed().name().toLowerCase());
        }

        tag.put("class", cssClasses.toString());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        FomanticHeaderContributor.get()
                .contributeComponent(response, "loader", false, true);
    }
}