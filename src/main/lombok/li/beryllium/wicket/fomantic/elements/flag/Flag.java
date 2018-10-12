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
package li.beryllium.wicket.fomantic.elements.flag;

import lombok.val;
import org.apache.wicket.IGenericComponent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Flag
 * <p/>
 * A flag is used to represent a political state
 *
 * @see <a href="https://fomantic-ui.com/elements/flag.html">Fomantic UI Flag</a>
 */
public class Flag extends WebComponent implements IGenericComponent<FlagType, Flag> {

    private final FlagOptions options;

    /**
     * Constructor
     *
     * @param id        Wicket ID
     * @param flagModel Model with the flag that should be displayed
     */
    public Flag(@Nonnull String id, @Nonnull IModel<FlagType> flagModel) {
        this(id, flagModel, FlagOptions.builder().build());
    }

    /**
     * Constructor
     *
     * @param id        Wicket ID
     * @param flagModel Model with the flag that should be displayed
     * @param options   Flag options
     */
    public Flag(@Nonnull String id, @Nonnull IModel<FlagType> flagModel, @Nonnull FlagOptions options) {
        super(id, flagModel);
        this.options = options;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        val cssClasses = new StringJoiner(" ")
                .add(Optional.ofNullable(getModel())
                        .map(IModel::getObject)
                        .map(FlagType::getCssClass)
                        .orElse(""))
                .add("flag");

        tag.append("class", cssClasses.toString(), " ");
    }

    @Override
    protected void onDetach() {
        super.onDetach();

        options.detach();
    }
}
