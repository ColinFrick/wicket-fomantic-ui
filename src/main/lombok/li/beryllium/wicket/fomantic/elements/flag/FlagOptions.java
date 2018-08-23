/*
 * KYBERNA AG
 *
 * Created fco 8/22/18 2:18 PM
 */
package li.beryllium.wicket.fomantic.elements.flag;

import lombok.Builder;
import lombok.Data;
import org.apache.wicket.model.IDetachable;

/**
 * FlagOptions
 * <p/>
 * Contains all available options for the {@link Flag} component
 */
@Data
@Builder
public class FlagOptions implements IDetachable {
    
    private static final long serialVersionUID = 6323414772270407815L;

    @Override
    public void detach() {
        // Noop
    }
}
