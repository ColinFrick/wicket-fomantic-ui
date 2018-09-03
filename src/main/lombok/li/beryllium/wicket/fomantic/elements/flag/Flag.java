/*
 * KYBERNA AG
 *
 * Created fco 8/22/18 2:18 PM
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
